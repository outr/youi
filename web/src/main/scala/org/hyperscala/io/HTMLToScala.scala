package org.hyperscala.io

import org.hyperscala.html._
import event.EventProperty
import org.jdom2.input.SAXBuilder
import java.io.StringReader
import io.Source
import org.htmlcleaner.{PrettyXmlSerializer, HtmlCleaner}
import org.hyperscala.{PropertyAttribute, Container}
import org.hyperscala.persistence.StyleSheetPersistence
import org.hyperscala.css.{StyleSheetAttribute, StyleSheet}
import org.hyperscala.javascript.JavaScriptString
import tag.{Link, Script, Text, Title}
import org.hyperscala.web.site.Webpage

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object HTMLToScala {
  lazy val template = Source.fromURL(getClass.getClassLoader.getResource("htmlpage.template")).mkString

  val builder = new SAXBuilder()

  def toScala(page: Webpage, packageName: String, className: String) = {
    val b = new ScalaBuffer(packageName, className, page)
    b.code
  }

  def toPage(source: Source) = {
    val page = new Webpage()
    page.html.read(toXML(source))
    page
  }

  def toHTML(source: Source) = {
    val xml = toXML(source)
    val root = HTMLTag.create(xml.getName)
    root.read(xml)
    root
  }

  def toXML(source: Source) = {
    val html = source.mkString
    val cleaner = new HtmlCleaner()
    val props = cleaner.getProperties
    val cleaned = cleaner.clean(html)
    val content = new PrettyXmlSerializer(props).getAsString(cleaned)
    builder.build(new StringReader(content)).getRootElement
  }
}

private class ScalaBuffer(packageName: String, className: String, page: Webpage) {
  var depth = 1
  val b = new StringBuilder

  writeAttributes(page.head, all = true, prefix = "head")
  page.head.contents.foreach(t => writeTag(t, "head"))
  writeAttributes(page.body, all = true, prefix = "body")
  page.body.contents.foreach(t => writeTag(t, "body"))

  val code = HTMLToScala.template.format(packageName, className, b.toString())

  def createWrappedString(s: String) = if (s.indexOf('\n') != -1) {
    "\"\"\"%s\"\"\"".format(s)
  } else {
    "\"%s\"".format(s.replaceAll("\"", "\\\\\""))
  }

  def writeTag(tag: HTMLTag, prefix: String = null): Unit = {
//    println("Tag: %s.%s".format(prefix, tag.xmlLabel))
    if (tag.render) {
      tag match {
        case title: Title => writeLine("title := \"%s\"".format(title.content()))
        case text: Text => writeLine("contents += %s".format(createWrappedString(text.content())))
        case _ => {
          val attributes = tag.xmlAttributes.collect {
            case a: PropertyAttribute[_] if (a.shouldRender && !a.isInstanceOf[EventProperty]) => "%s = %s".format(namify(tag, a.name()), valuify(a.name(), a()))
          }.mkString(", ")
          val constructor = attributes.nonEmpty match {
            case true => "(%s)".format(attributes)
            case false => ""
          }
          val style = StyleSheetPersistence.toString(tag.style(), classOf[StyleSheet])
          val children = tag match {
            case container: Container[_] if (container.contents.nonEmpty) => true
            case _ if (style.trim.nonEmpty) => true
            case _ => false
          }
          val opening = if (children) {
            " {"
          } else {
            ""
          }
          writeLine("contents += new %s%s%s".format(tag.getClass.getSimpleName, constructor, opening), prefix)
          if (children) {
            depth += 1
            writeAttributes(tag, all = false, prefix = null)
            tag.asInstanceOf[Container[_]].contents.foreach {
              case child: HTMLTag => writeTag(child)
              case child: JavaScriptString => if (child.content.trim.nonEmpty) {
                writeLine("contents += JavaScriptString(%s)".format(createWrappedString(child.content)))
              }
            }
            depth -= 1
            writeLine("}")
          }
        }
      }
    }
  }

  def writeAttributes(tag: HTMLTag, all: Boolean, prefix: String) = {
    // Write style data
    tag.style().properties.foreach {
      case a: StyleSheetAttribute[_] if (a.shouldRender) => {
        writeLine("style.%s := %s".format(a.name(), a.attributeValue), prefix)
      }
      case _ => // Ignore
    }
    // Write event data
    tag.xmlAttributes.foreach {
      case a: PropertyAttribute[_] with EventProperty if (a.shouldRender) => {
        writeLine("event.%s := %s".format(a.name().substring(2), valuify(a.name(), a())), prefix)
      }
      case a: PropertyAttribute[_] if (a.shouldRender && all) => {
        // Write out attributes that could be in constructor - used in <body>
        writeLine("%s := %s".format(namify(tag, a.name()), valuify(a.name(), a())), prefix)
      }
      case _ => // Ignore
    }
  }

  def writeLine(line: String = "", prefix: String = null) = {
    (0 until depth).foreach(i => b.append("  "))
    if (prefix != null) {
      write(prefix)
      write(".")
    }
    write(line)
    b.append('\r')
    b.append('\n')
  }

  def write(content: String) = {
    b.append(content)
  }

  def namify(tag: HTMLTag, name: String) = {
    if (name == "type") {
      tag match {
        case l: Link => "mimeType"
        case s: Script => "mimeType"
      }
    } else if (name == "class") {
      "clazz"
    } else {
      var nextCapital = false
      val b = new StringBuilder
      name.foreach {
        case '-' => nextCapital = true
        case c => if (nextCapital) {
          b.append(c.toUpper)
          nextCapital = false
        } else {
          b.append(c)
        }
      }
      b.toString()
    }
  }

  def valuify(name: String, v: Any) = v match {
    case s: String => "\"%s\"".format(s)
    case l: List[_] if (name == "class") => "List(%s)".format(l.mkString("\"", ", ", "\""))
    case js: JavaScriptString => "JavaScriptString(%s)".format(createWrappedString(js.content))
    case _ => throw new RuntimeException("Unsupported value: %s (%s)".format(v, name))
  }
}