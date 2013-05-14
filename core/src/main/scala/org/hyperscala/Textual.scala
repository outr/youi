package org.hyperscala

import io.HTMLWriter
import org.jdom2.{Element, Content}
import persistence.StringPersistence

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait Textual extends Markup {
  val content = PropertyAttribute[String]("content", null, inclusion = InclusionMode.Exclude)(StringPersistence, this, implicitly[Manifest[String]])

  private val children = List(new TextualChild(this))
  override def xmlChildren = if (content() == null || content() == "") {
    Nil
  } else {
    children
  }

  override def read(xml: Content) {
    super.read(xml)
    val element = xml.asInstanceOf[Element]
    content := element.getText.trim
  }
}

class TextualChild(textual: Textual) extends XMLContent {
  def write(writer: HTMLWriter) = writer.write(textual.content())

  def read(content: Content) = throw new NullPointerException("This should never be called")
}
