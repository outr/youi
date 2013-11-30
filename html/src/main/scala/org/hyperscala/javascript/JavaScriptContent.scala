package org.hyperscala.javascript

import org.hyperscala.{AttributeValue, XMLContent}
import org.jdom2.{Text, Content}
import org.hyperscala.io.HTMLWriter
import org.powerscala.Color
import org.powerscala.json.Jsonify

/**
 * @author Matt Hicks <mhicks@powerscala.org>
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
    case c: Color => s"'${c.hex.rgb}'"
    case _ => v.toString
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