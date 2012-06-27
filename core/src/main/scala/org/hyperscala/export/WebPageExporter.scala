package org.hyperscala.export

import org.hyperscala._
import js.JavaScriptContent
import style.{Length, StyleProperty, StyleSheet}
import org.sgine.Color
import tags.attributes.InputType
import tags.{Script, Tag, Text}
import annotation.tailrec

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
object WebPageExporter {
  def apply(webPage: WebPage, name: String) = new WebPageExporter(webPage, name).b.toString()
}

private class WebPageExporter(webPage: WebPage, name: String) {
  val LengthRegex = """(\d*)(.*)""".r

  var depth = 0
  val b = new StringBuilder

  writeLine("import org.hyperscala._")
  writeLine("import org.hyperscala.js.JavaScript")
  writeLine("import org.hyperscala.style._")
  writeLine("import org.hyperscala.tags._")
  writeLine("import org.hyperscala.tags.attributes._")
  writeLine("import org.sgine.Color")
  writeLine()
  writeLine("object %s extends WebPage(\"%s\") {".format(name, name))
  depth += 1
  webPage.head.title() match {
    case null => // Nothing set
    case title => writeLine("head.title := \"%s\"".format(title))
  }
  webPage.head.contents.foreach {
    case script: Script => writeLine("head += JavaScript(\"\"\"%s\"\"\")".format(script.contents.head.asInstanceOf[JavaScriptContent].toJS))
    case content => println("Content: %s".format(content))
  }
  // TODO: add head content
  process(webPage.body, "body")
  depth -= 1
  writeLine("}")

  def writeLine(line: String = "", prefix: String = null) = {
    (0 until depth).foreach(i => b.append('\t'))
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

  def process(content: WebContent, prefix: String): Unit = {
    content match {
      case bodyContent: BodyContent => {
        bodyContent.attributes.values.foreach {
          case a: GenericAttribute[_] if (a.modified && a.name.startsWith("on") && a.value.value.isInstanceOf[JavaScriptContent]) => {
            // Events
            writeLine("event.%s := %s".format(a.name.substring(2), convert(a.value.value)))
          }
          case ss: StyleSheet => {
            ss.contents.foreach {
              case sp: StyleProperty[_] if (sp.modified) => {
                val name = sp._name.replaceAll("-", ".")
                val value = convert(sp.value.value)
                writeLine("style.%s := %s".format(name, value), prefix)
              }
              case _ =>
            }
          }
          case _ =>
        }
      }
    }
    content match {
      case container: Container => {
        container.contents.foreach {
          case text: Text => writeLine("contents += \"%s\"".format(text.value.value), prefix)
          case tag: Tag => {
            var attributes = List.empty[String]
            tag.attributes.values.foreach {
              case a: GenericAttribute[_] if (a.modified) => {
                val name = a.name match {
                  case "type" if (a.value.value.isInstanceOf[InputType]) => "inputType"
                  case s if (s.startsWith("on") && a.value.value.isInstanceOf[JavaScriptContent]) => null
                  case s => s
                }
                if (name != null) {
                  attributes = "%s = %s".format(name, convert(a.value.value)) :: attributes
                }
              }
              case _ => // Ignore StyleSheet
            }
            attributes = attributes.reverse
            writeLine("contents += new %s(%s) {".format(tagName(tag.getClass), attributes.mkString(", ")), prefix)
            depth += 1
            process(tag, null)
            depth -= 1
            writeLine("}")
          }
          case child => sys.error("Unknown child type: %s (%s)".format(child, child.getClass.getSuperclass))
        }
      }
      case _ => // Not a container
    }
  }

  @tailrec
  private def tagName(clazz: Class[_]): String = if (clazz.getName.startsWith("org.hyperscala.tags.")) {
    clazz.getSimpleName
  } else {
    tagName(clazz.getSuperclass)
  }

  def convert(value: Any) = value match {
    case color: Color if (color.name != null) => "Color.%s".format(color.name)
    case color: Color => "Color.immutable(\"%s\")".format(color.hex.rgb)
    case length: Length => length.value match {
      case LengthRegex(n, t) => if (t == "auto") {
        "Length.Auto"
      } else if (t == "inherit") {
        "Length.Inherit"
      } else {
        "%s.%s".format(n, t)
      }
    }
    case jsc: JavaScriptContent => "JavaScript(\"\"\"%s\"\"\")".format(jsc.toJS)
    case inputType: InputType => "InputType.%s".format(inputType.name)
    case s: String => "\"%s\"".format(s)
  }
}