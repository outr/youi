package org.hyperscala.io

import org.hyperscala.web.site.Webpage
import org.hyperscala.html.HTMLTag
import org.hyperscala.{PropertyAttribute, Container}
import org.hyperscala.html.tag.{Text, Title}
import org.hyperscala.css.StyleSheetAttribute
import org.hyperscala.javascript.{EventProperty, JavaScriptString}
import org.hyperscala.css.attributes.Length
import org.powerscala.enum.EnumEntry

import org.powerscala.reflect._

/**
 * @author Matt Hicks <matt@outr.com>
 */
abstract class ScalaBuffer {
  var depth = 1
  val b = new StringBuilder

  def createWrappedString(s: String) = if (s.indexOf('\n') != -1) {
    "\"\"\"%s\"\"\"".format(s)
  } else {
    "\"%s\"".format(s.replaceAll("\"", "\\\\\""))
  }

  def tagName(clazz: Class[_]): String = if (clazz.getName.startsWith("org.hyperscala.html.tag.") && !clazz.getName.contains("$")) {
    clazz.getSimpleName
  } else {
    tagName(clazz.getSuperclass)
  }

  def writeTag(tag: HTMLTag, prefix: String = null): Unit = {
    if (tag.render) {
      tag match {
        case title: Title => writeLine("title := \"%s\"".format(title.content()))
        case text: Text if text.content() != null && text.content().trim.length > 0 => writeLine("contents += %s".format(createWrappedString(text.content())), prefix)
        case _ => {
          val attributes = tag.xmlAttributes.collect {
            case a: PropertyAttribute[_] if a.shouldRender && !a.isInstanceOf[EventProperty] && !a.isInstanceOf[StyleSheetAttribute[_]] && a() != null => {
              "%s = %s".format(namify(tag, a), valuify(tag, a.name, a()))
            }
          }.mkString(", ")
          val constructor = attributes.nonEmpty match {
            case true => "(%s)".format(attributes)
            case false => ""
          }
          val style = tag.style.toString
          val children = tag match {
            case container: Container[_] if container.contents.nonEmpty => true
            case _ if style.trim.nonEmpty => true
            case _ => false
          }
          val opening = if (children) {
            " {"
          } else {
            ""
          }
          writeLine("contents += new tag.%s%s%s".format(tagName(tag.getClass), constructor, opening), prefix)
          if (children) {
            depth += 1
            writeAttributes(tag, all = false, prefix = null)
            tag match {
              case container: Container[_] => container.contents.foreach {
                case child: HTMLTag => writeTag(child)
                case child: JavaScriptString => if (child.content.trim.nonEmpty) {
                  writeLine("contents += JavaScriptString(%s)".format(createWrappedString(child.content)))
                }
              }
              case _ => // Not a container
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
    throw new RuntimeException("writeAttributes disabled")
    /*tag.style().properties.foreach {
      case a: StyleSheetAttribute[_] if (a.shouldRender) => {
        writeLine("style.%s := %s".format(a.name().replace('-', '.'), valuify(tag, a.name(), a())), prefix)
      }
      case _ => // Ignore
    }*/
    // Write event data
    tag.xmlAttributes.foreach {
      case a: PropertyAttribute[_] with EventProperty if (a.shouldRender) => {
        writeLine("event.%s := %s".format(a.name.substring(2), valuify(tag, a.name, a())), prefix)
      }
      case a: PropertyAttribute[_] if (a.shouldRender && all) => {
        // Write out attributes that could be in constructor - used in <body>
        writeLine("%s := %s".format(namify(tag, a), valuify(tag, a.name, a())), prefix)
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

  def namify(tag: HTMLTag, a: PropertyAttribute[_]) = ScalaBuffer.attributeName(tag, a)

  def valuify(tag: HTMLTag, name: String, v: Any) = v match {
    case s: String => "\"%s\"".format(s)
    case l: List[_] if (name == "class") => "List(%s)".format(l.map(v => "\"%s\"".format(v)).mkString(", "))
    case js: JavaScriptString => "JavaScriptString(%s)".format(createWrappedString(js.content))
    case l: Length if (l.name == null && l.value.endsWith("px")) => "%s.px".format(l.pixels)
    case l: Length if (l.name == null && l.value.endsWith("%")) => "%s.pct".format(l.percent)
    case e: EnumEntry => "%s.%s".format(e.parent.name, e.name)
    case i: Int => i.toString
    case _ => throw new RuntimeException("Unsupported value: %s.%s (%s: %s)".format(tag.getClass.getName, name, v, v.asInstanceOf[AnyRef].getClass.getName))
  }
}

object ScalaBuffer {
  private var attributes = Map.empty[String, String]

  def attributeName(tag: HTMLTag, attribute: PropertyAttribute[_]) = synchronized {
    val key = "%s.%s".format(tag.xmlLabel, attribute.name)
    attributes.get(key) match {
      case Some(alias) => alias
      case None => {
        val method = tag.getClass.methods.find {
          case m if (m.name == "formValue") => false
          case m if (m.returnType.`type`.hasType(classOf[PropertyAttribute[_]])) => m.invoke[AnyRef](tag) == attribute
          case _ => false
        }.get
        attributes += key -> method.name
        method.name
      }
    }
  }
}

class ScalaWebpageBuffer(packageName: String, className: String, page: Webpage) extends ScalaBuffer {
  writeAttributes(page.head, all = true, prefix = "head")
  page.head.contents.foreach(t => writeTag(t, "head"))
  writeAttributes(page.body, all = true, prefix = "body")
  page.body.contents.foreach(t => writeTag(t, "body"))

  private def p = packageName match {
    case null => ""
    case _ => "package %s".format(packageName)
  }

  val code = HTMLToScala.WebpageTemplate.format(p, className, b.toString())
}

class ScalaTagBuffer(packageName: String, className: String, baseTag: HTMLTag) extends ScalaBuffer {
  writeAttributes(baseTag, all = true, prefix = null)
  baseTag match {
    case container: Container[_] => container.contents.foreach(tag => writeTag(tag.asInstanceOf[HTMLTag]))
    case _ => // Not a container
  }

  private def p = packageName match {
    case null => ""
    case _ => "package %s".format(packageName)
  }

  val code = HTMLToScala.TagTemplate.format(p, className, tagName(baseTag.getClass), b.toString())
}