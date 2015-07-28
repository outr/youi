package org.hyperscala.io

import scala.util.Try

import org.powerscala.reflect._

import org.hyperscala.web.Webpage
import org.hyperscala.html.HTMLTag
import org.hyperscala._
import org.hyperscala.html.tag.{Comment, Text, Title}
import org.hyperscala.css.StyleSheetAttribute
import org.hyperscala.javascript.{EventProperty, JavaScriptString}
import org.hyperscala.css.attributes.NumericLength
import org.powerscala.enum.EnumEntry

/**
 * @author Matt Hicks <matt@outr.com>
 */
abstract class ScalaBuffer {
  var depth = 1
  val b = new StringBuilder

  def tagName(clazz: Class[_]): String = if (clazz.getName.startsWith("org.hyperscala.html.tag.") && !clazz.getName.contains("$")) {
    clazz.getSimpleName
  } else {
    tagName(clazz.getSuperclass)
  }

  import ScalaBuffer.createWrappedString

  def writeTag(tag: HTMLTag, prefix: String = null): Unit = {
    if (tag.render) {
      tag match {
        case title: Title => writeLine("title := \"%s\"".format(title.content()))
        case comment: Comment => writeLine(s"contents += new tag.Comment(${createWrappedString(comment.content())})", prefix)
        case text: Text if text.content() != null && text.content().trim.length > 0 => writeLine(s"contents += ${createWrappedString(text.content())}", prefix)
        case _ => {
          val attributes = tag.xmlAttributes.collect {
            case a: PropertyAttribute[_] if a.shouldRender && !a.isInstanceOf[EventProperty] && !a.isInstanceOf[StyleSheetAttribute[_]] && a() != null && !a.name.startsWith("data-") && !a.name.startsWith("aria-") && !a.dynamic => {
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
            case _ if tag.attributes.keys.exists(name => name.startsWith("aria-") || name.startsWith("data-")) => true
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
//    throw new RuntimeException("writeAttributes disabled")
    tag.style.attributes.values.foreach {
      case a: StyleSheetAttribute[_] if a.shouldRender => {
        writeLine(s"style.${a.style.name} := ${valuify(tag, a.name, a())}", prefix)
      }
      case _ => // Ignore
    }
    // Write event data
    tag.xmlAttributes.foreach {
      case a: PropertyAttribute[_] with EventProperty if a.shouldRender => {
        writeLine(s"${a.name.substring(2)}Event := ${valuify(tag, a.name, a())}", prefix)
      }
      case a: PropertyAttribute[_] if a.shouldRender && a.name.startsWith("aria-") => {
        writeLine(s"${namify(tag, a)} := ${valuify(tag, a.name, a())}", prefix)
      }
      case a: PropertyAttribute[_] if a.shouldRender && a.name.startsWith("data-") => {
        writeLine(s"""data("${a.name.substring(5)}", ${valuify(tag, a.name, a())})""")
      }
      case a: PropertyAttribute[_] if a.shouldRender && all => {
        // Write out attributes that could be in constructor - used in <body>
        writeLine(s"${namify(tag, a)} := ${valuify(tag, a.name, a())}", prefix)
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

  def namify(tag: HTMLTag, a: PropertyAttribute[_]) = ScalaBuffer.attributeName(tag, a) match {
    case Some(name) => name
    case None => s"""attribute("${a.name}")"""      // Dynamically defined attribute
  }

  def valuify(tag: HTMLTag, name: String, v: Any): String = {
    Try(ScalaBuffer.encode(v))
      .getOrElse(throw new RuntimeException(s"Unsupported value in '${tag.xmlLabel}' for name: $name, value: '$v' of type ${v.asInstanceOf[AnyRef].getClass.getName}"))
  }
}

object ScalaBuffer {
  private var attributes = Map.empty[String, String]

  def attributeName(tag: HTMLTag, attribute: PropertyAttribute[_]): Option[String] = synchronized {
    val key = "%s.%s".format(tag.xmlLabel, attribute.name)
    attributes.get(key).orElse {
      val methodOption = tag.getClass.methods.find {
        case m if m.name.indexOf('$') != -1 => false
        case m if m.name == "formValue" => false
        case m if m.returnType.`type`.hasType(classOf[PropertyAttribute[_]]) && m.args.isEmpty => try {
          m.invoke[AnyRef](tag) eq attribute
        } catch {
          case t: Throwable => throw new RuntimeException(s"Failed to invoke ${m.absoluteSignature} on $key.", t)
        }
        case _ => false
      }

      // If `methodOption` is empty, ignore as attribute is dynamically defined
      methodOption.map { m =>
        attributes += key -> m.name
        m.name
      }
    }
  }

  def createWrappedString(s: String) = if (s.trim.nonEmpty) {
    val preWhitespace = s.charAt(0).isWhitespace
    val postWhitespace = s.charAt(s.length - 1).isWhitespace
    val b = new StringBuilder
    if (preWhitespace) {
      b.append(' ')
    }
    b.append(s.trim)
    if (postWhitespace) {
      b.append(' ')
    }
    val trimmed = b.toString()
    if (s.indexOf('\n') != -1) {
      "\"\"\"%s\"\"\"".format(trimmed)
    } else {
      "\"%s\"".format(trimmed.replaceAll("\"", "\\\\\""))
    }
  } else if (s.isEmpty) {
    "\"\""
  } else {
    "\" \""
  }

  def encode(v: Any): String = v match {
    case s: String => createWrappedString(s)
    case l: List[_] => "List(%s)".format(l.map(v => encode(v)).mkString(", "))
    case js: JavaScriptString => "JavaScriptString(%s)".format(createWrappedString(js.content))
    case l: NumericLength => s"${l.number}.${l.lengthType}"
    case e: EnumEntry if e.name != null => s"${e.parentName}.${e.name}"
    case e: EnumEntryAttributeValue => s"""${e.parentName}("${e.value}")"""
    case c: ToScala => c.toScala.toString()
    case i: Int => i.toString
    case b: Boolean => b.toString
    case _ => throw new RuntimeException(s"Unsupported value '$v' of type ${v.getClass.getName}")
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

class ScalaInstanceBuffer(baseTag: HTMLTag) extends ScalaBuffer {
  writeAttributes(baseTag, all = true, prefix = null)
  baseTag match {
    case container: Container[_] => container.contents.foreach(tag => writeTag(tag.asInstanceOf[HTMLTag]))
    case _ => // Not a container
  }

  val code = s"new tag.${tagName(baseTag.getClass)} {\r\n$b}"
}