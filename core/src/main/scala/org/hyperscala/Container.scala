package org.hyperscala

import org.powerscala.hierarchy.MutableContainer
import org.jdom2.{Comment => JDOMComment, Text, Content, Element}
import scala.collection.JavaConversions._

/**
 * @author Matt Hicks <matt@outr.com>
 */
abstract class Container[C <: XMLContent](implicit val childManifest: Manifest[C]) extends MutableContainer[C] with Markup {
  override def xmlChildren = contents

  override def read(xml: Content) {
    super.read(xml)
    val element = xml.asInstanceOf[Element]
    element.getContent.foreach {
      case childElement: Element => {
        val child = generateChildFromTagName(childElement.getName).asInstanceOf[C]
        child.read(childElement)
        if (!contents.contains(child)) {
          contents += child
        }
      }
      case childText: Text => processText(childText.getText)
      case comment: JDOMComment => processComment(comment.getText)
    }
  }

  protected def generateChildFromTagName(name: String): XMLContent

  protected def processText(text: String): Unit

  protected def processComment(text: String): Unit
}
