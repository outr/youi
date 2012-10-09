package org.hyperscala

import org.powerscala.hierarchy.MutableContainer
import org.jdom2.{Comment, Text, Content, Element}
import scala.collection.JavaConversions._

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait Container[C <: XMLContent] extends MutableContainer[C] with Markup {
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
      case childComment: Comment => // Ignore comments
    }
  }

  protected def generateChildFromTagName(name: String): XMLContent

  protected def processText(text: String): Unit
}
