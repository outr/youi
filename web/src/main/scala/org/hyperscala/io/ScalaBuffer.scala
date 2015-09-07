package org.hyperscala.io

import org.hyperscala._
import org.hyperscala.css.StyleSheetAttribute
import org.hyperscala.css.attributes.NumericLength
import org.hyperscala.html.HTMLTag
import org.hyperscala.html.tag.{Comment, HTML, Text, Title}
import org.hyperscala.javascript.{EventProperty, JavaScriptString}
import org.powerscala.StringUtil
import org.powerscala.enum.EnumEntry
import org.powerscala.reflect._

import scala.util.Try

case class WriterContext(
  var depth: Int = 1,
  content: StringBuilder = new StringBuilder
) {
  def writeLine(line: String = "", prefix: Option[String] = None, nlbr: Boolean = true, indent: Boolean = true) {
    if (indent) (0 until depth).foreach(_ => content.append("  "))
    prefix.foreach { pfx =>
      content.append(pfx)
      content.append(".")
    }
    content.append(line)
    if (nlbr) content.append('\n')
  }
}

/**
 * @author Matt Hicks <matt@outr.com>
 */
object ScalaBuffer {
  def tagName(clazz: Class[_]): String =
    if (clazz.getName.startsWith("org.hyperscala.html.tag.") && !clazz.getName.contains("$")) {
      clazz.getSimpleName
    } else {
      tagName(clazz.getSuperclass)
    }

  def encodeId(id: String): String = StringUtil.toCamelCase(id)

  def writeTag(tag: HTMLTag,
               prefix: Option[String] = None,
               code: WriterContext,
               vals: WriterContext,
               mapping: Map[String, String] = Map.empty,
               parentComponent: Option[String] = None) {
    if (tag.render) {
      tag match {
        case title: Title => code.writeLine("title := \"%s\"".format(title.content()), prefix)
        case comment: Comment => code.writeLine(s"contents += new tag.Comment(${createWrappedString(comment.content())})", prefix)
        case text: Text if text.content() != null && text.content().trim.length > 0 =>
          code.writeLine(s"contents += ${createWrappedString(text.content())}", prefix)
        case _ => {
          if (!Option(tag.id()).exists(_.nonEmpty)) {
            code.writeLine("contents += ", prefix, nlbr = false)
            instantiateTag(tag, code, vals, mapping, parentComponent)
          } else if (mapping.contains(tag.id())) {
            code.writeLine(s"contents += new ${mapping(tag.id())}", prefix)
          } else {
            val valContext = WriterContext()
            instantiateTag(tag, valContext, vals, mapping, parentComponent)

            val id = encodeId(tag.id())

            vals.writeLine(s"val $id = ", nlbr = false)
            vals.writeLine(valContext.content.toString(), nlbr = false, indent = false)

            code.writeLine(s"contents += $id", prefix)
          }
        }
      }
    }
  }

  def instantiateTag(tag: HTMLTag,
                     code: WriterContext,
                     vals: WriterContext,
                     mapping: Map[String, String],
                     parentComponent: Option[String]) {
    bootstrap.Generation.findComponent(tag, parentComponent) match {
      case Some(component) =>
        bootstrap.Generation.applyComponent(tag, component, code, vals, mapping)

      case _ =>
        val attributes = tag.xmlAttributes.collect {
          case a: PropertyAttribute[_]
            if a.shouldRender &&
              !a.isInstanceOf[EventProperty] &&
              !a.isInstanceOf[StyleSheetAttribute[_]] &&
              a() != null &&
              !a.name.startsWith("data-") &&
              !a.name.startsWith("aria-") &&
              !a.dynamic => {
            "%s = %s".format(namify(tag, a), valuify(tag, a.name, a()))
          }
        }.mkString(", ")

        val constructor = attributes.nonEmpty match {
          case true => "(%s)".format(attributes)
          case false => ""
        }

        val style = tag.style.toString
        val hasBody = tag match {
          case container: Container[_] if container.contents.nonEmpty => true
          case _ if style.trim.nonEmpty => true
          case _ if tag.attributes.keys.exists(name => name.startsWith("aria-") || name.startsWith("data-")) => true
          case _ => false
        }
        val opening = if (hasBody) {
          " {"
        } else {
          ""
        }

        code.writeLine(
          "new tag.%s%s%s".format(tagName(tag.getClass), constructor, opening),
           indent = false)

        if (hasBody) {
          code.depth += 1
          writeAttributes(tag, all = false, prefix = None, code = code)
          writeChildren(tag, code, vals, mapping, parentComponent)
          code.depth -= 1
          code.writeLine("}")
        }
    }
  }

  def writeChildren(tag: HTMLTag,
                    code: WriterContext,
                    vals: WriterContext,
                    mapping: Map[String, String],
                    parentComponent: Option[String]) {
    tag match {
      case child: Textual =>
        code.writeLine(s"content := ${createWrappedString(child.content())}")
      case container: Container[_] => container.contents.foreach {
        case child: HTMLTag =>
          writeTag(child, code = code, vals = vals, mapping = mapping, parentComponent = parentComponent)
        case child: JavaScriptString => if (child.content.trim.nonEmpty) {
          code.writeLine("contents += JavaScriptString(%s)".format(createWrappedString(child.content)))
        }
      }
      case _ => // Not a container
    }
  }

  def writeAttributes(tag: HTMLTag,
                      all: Boolean,
                      prefix: Option[String] = None,
                      code: WriterContext,
                      withoutAttributes: Set[String] = Set.empty) = {
    // Write style data
    tag.style.attributes.values.foreach {
      case a: StyleSheetAttribute[_] if a.shouldRender => {
        code.writeLine(s"style.${a.style.name} := ${valuify(tag, a.name, a())}", prefix)
      }
      case _ => // Ignore
    }
    // Write event data
    tag.xmlAttributes
      .filter(attr => !withoutAttributes.contains(namify(tag, attr)))
      .foreach {
      case a: PropertyAttribute[_] with EventProperty if a.shouldRender => {
        code.writeLine(s"${a.name.substring(2)}Event := ${valuify(tag, a.name, a())}", prefix)
      }
      case a: PropertyAttribute[_] if a.shouldRender && a.name.startsWith("aria-") => {
        code.writeLine(s"${namify(tag, a)} := ${valuify(tag, a.name, a())}", prefix)
      }
      case a: PropertyAttribute[_] if a.shouldRender && a.name.startsWith("data-") => {
        code.writeLine(s"""data("${a.name.substring(5)}", ${valuify(tag, a.name, a())})""")
      }
      case a: PropertyAttribute[_] if a.shouldRender && all => {
        // Write out attributes that could be in constructor - used in <body>
        code.writeLine(s"${namify(tag, a)} := ${valuify(tag, a.name, a())}", prefix)
      }
      case a: PropertyAttribute[_] if a.shouldRender && a.dynamic => {
        code.writeLine(s"${namify(tag, a)} := ${valuify(tag, a.name, a())}", prefix)
      }
      case a => // Ignore
    }
  }

  def namify(tag: HTMLTag, a: PropertyAttribute[_]) = ScalaBuffer.attributeName(tag, a) match {
    case Some(name) => name
    case _ if a.name == "value" => a.name
    case None => s"""attribute[String]("${a.name}").get"""      // Dynamically defined attribute
  }

  def valuify(tag: HTMLTag, name: String, v: Any): String = {
    Try(ScalaBuffer.encode(v))
      .getOrElse(throw new RuntimeException(s"Unsupported value in '${tag.xmlLabel}' for name: $name, value: '$v' of type ${v.asInstanceOf[AnyRef].getClass.getName}"))
  }

  private var attributes = Map.empty[String, String]

  def attributeName(tag: HTMLTag, attribute: PropertyAttribute[_]): Option[String] = synchronized {
    val key = "%s.%s".format(tag.xmlLabel, attribute.name)
    attributes.get(key).orElse {
      val methodOption = tag.getClass.getDeclaredMethods.find {
        case m if m.name.indexOf('$') != -1 => false
        case m if m.name == "formValue" => false
        case m if m.returnType.`type`.hasType(classOf[PropertyAttribute[_]]) && m.args.isEmpty => try {
          m.invoke(tag) eq attribute
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

trait ScalaBuffer {
  val attrsContext = WriterContext()
  val valsContext = WriterContext()
  val codeContext = WriterContext()
}

/**
 * @param mapping Mapping of extracted tag IDs to their corresponding Screen
 *                class names
 */
class ScalaWebpageBuffer(packageName: Option[String],
                         className: String,
                         html: HTML,
                         mapping: Map[String, String] = Map.empty) extends ScalaBuffer {
  import ScalaBuffer._
  writeAttributes(html.head, all = true, prefix = Some("head"), code = attrsContext)
  html.head.contents.foreach(t => writeTag(t, Some("head"), codeContext, valsContext, mapping))

  writeAttributes(html.body, all = true, prefix = Some("body"), code = attrsContext)
  html.body.contents.foreach(t => writeTag(t, Some("body"), codeContext, valsContext, mapping))

  private def pkg = packageName.map("package %s".format(_)).getOrElse("")
  val code = HTMLToScala.WebpageTemplate.format(pkg, className,
    attrsContext.content.toString ++ valsContext.content.toString ++ codeContext.content.toString)
}

class ScalaTagBuffer(packageName: Option[String],
                     className: String,
                     baseTag: HTMLTag,
                     mapping: Map[String, String] = Map.empty) extends ScalaBuffer {
  import ScalaBuffer._
  writeAttributes(baseTag, all = true, prefix = None, attrsContext)

  private def pkg = packageName.map("package %s".format(_)).getOrElse("")

  baseTag match {
    case container: Container[_] => container.contents.foreach(tag =>
      writeTag(tag.asInstanceOf[HTMLTag],
        code = codeContext,
        vals = valsContext,
        mapping = mapping))
    case _ => // Not a container
  }

  val code = HTMLToScala.TagTemplate.format(pkg, className,
    tagName(baseTag.getClass),
    attrsContext.content.toString ++ valsContext.content.toString ++ codeContext.content.toString)
}

class ScalaScreenBuffer(packageName: Option[String],
                        className: String,
                        baseTag: HTMLTag,
                        mapping: Map[String, String] = Map.empty) extends ScalaBuffer {
  import ScalaBuffer._
  writeAttributes(baseTag, all = true, prefix = None, code = codeContext)

  private def pkg = packageName.map("package %s".format(_)).getOrElse("")

  baseTag match {
    case html: HTML =>
      html.head.contents.foreach { child =>
        writeTag(child,
          prefix = Some("webpage.head"),
          code = codeContext,
          vals = valsContext,
          mapping = mapping)
      }

      html.body.contents.foreach { tag =>
        writeTag(tag,
          prefix = Some("webpage.body"),
          code = codeContext,
          vals = valsContext,
          mapping = mapping)
      }

    case container: Container[_] => container.contents.foreach(tag =>
      writeTag(tag.asInstanceOf[HTMLTag],
        code = codeContext,
        vals = valsContext,
        mapping = mapping))
    case _ => // Not a container
  }

  val code = HTMLToScala.ScreenTemplate.format(pkg, className,
    tagName(baseTag.getClass),
    attrsContext.content.toString ++ valsContext.content.toString ++ codeContext.content.toString)
}

class ScalaInstanceBuffer(baseTag: HTMLTag) extends ScalaBuffer {
  import ScalaBuffer._
  writeAttributes(baseTag, all = true, prefix = None, code = codeContext)

  baseTag match {
    case container: Container[_] => container.contents
      .foreach(tag => writeTag(tag.asInstanceOf[HTMLTag], code = codeContext, vals = valsContext))
    case _ => // Not a container
  }

  val code = s"new tag.${tagName(baseTag.getClass)} {\n${codeContext.content}}"
}