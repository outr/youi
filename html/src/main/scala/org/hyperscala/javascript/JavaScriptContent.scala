package org.hyperscala.javascript

import org.hyperscala.selector.Selector
import org.hyperscala.{AttributeValue, XMLContent}
import org.jdom2.{Text, Content}
import org.hyperscala.io.HTMLWriter
import org.powerscala.Color
import org.powerscala.json.Jsonify
import org.powerscala.reflect._
import java.util.Date

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait JavaScriptContent extends XMLContent with Jsonify {
  def content: String

  def write(writer: HTMLWriter) = writer.write(content)

  def read(content: Content): Unit = throw new UnsupportedOperationException("JavaScriptContent does not support loading. Use JavaScriptString instead.")

  def +(js: JavaScriptContent) = JavaScriptString("%s\n\n%s".format(content, js.content))

  def parseJson(map: Map[String, Any]) = JavaScriptString(map("content").asInstanceOf[String])

  def generate() = content

  override def toString = s"${getClass.getSimpleName}(content = $content)"
}

object JavaScriptContent {
  def toJS(v: Any): String = v match {
    case null => "null"
    case a: AttributeValue => s"'${a.value}'"
    case js: JavaScriptContent => js.content
    case s: String => "'%s'".format(s.replaceAll("\n", " ").replaceAll("\r", " ").replaceAll("'", """\\\'"""))
    case l: List[_] => l.map(toJS).mkString("[", ", ", "]")
    case d: Date => s"new Date(${d.getTime})"
    case c: Color => s"'${c.hex.rgb}'"
    case s: Selector => s.content
    case o: JSObject => {
      val c: EnhancedClass = o.getClass
      val default = o match {
        case od: JSObjectWithDefault => {
          val d = od.default
          d.getClass.caseValues.map {
            case cv => JSObject.fieldName(d, cv).map(fn => fn -> cv[Any](d))
          }.flatten.toMap
        }
        case _ => Map.empty[String, Any]
      }
      c.caseValues.map {
        case cv => JSObject.fieldName(o, cv) match {
          case Some(fieldName) => {
            val value = cv[Any](o)
            default.get(fieldName) match {
              case Some(d) if d == value => None
              case _ => toJSOption(value).map(s => s"$fieldName: $s")
            }
          }
          case None => None
        }
      }.flatten.mkString("{ ", ", ", " }")
    }
    case _ => v.toString
  }

  def toJSOption(v: Any): Option[String] = v match {
    case Some(value) => Some(toJS(value))
    case None => None
    case _ => Some(toJS(v))
  }

  def options(options: JSOption*) = {
    options.collect {
      case o if o.value.isDefined => s"${o.name}: ${toJS(o.value.get)}"
    }.mkString("{ ", ", ", " }")
  }

  case class JSOption(name: String, value: Option[Any])
}

case class JavaScriptString(var content: String) extends JavaScriptContent {
  override def read(content: Content): Unit = this.content = content.asInstanceOf[Text].getText
}