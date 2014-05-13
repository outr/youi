package org.hyperscala.html.tag

import org.hyperscala.{PropertyAttribute, Textual, TextMarkup, Container}
import org.hyperscala.html.HTMLTag
import org.hyperscala.html.constraints.{HeadChild, BodyChild}
import org.hyperscala.io.HTMLWriter

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ConditionalComment extends Container[HTMLTag] with BodyChild with HeadChild with HTMLTag {
  lazy val condition = PropertyAttribute[String]("condition", null)

  def this(condition: String) = {
    this()
    this.condition := condition
  }

  def xmlLabel = null

  override protected def writeTag(writer: HTMLWriter) = {
    writer.write(writer.newLine)
    writer.writeTabs()
    writer.write(s"<!--[if ${condition()}]>")
    val children = xmlChildren
    if (xmlExpanded || children.nonEmpty) {
      writer.tabbed {
        writeChildren(writer, children)
      }
      if (children.nonEmpty && children.find(c => !c.isInstanceOf[TextMarkup]).nonEmpty && !this.isInstanceOf[Textual]) {
        writer.write(writer.newLine)
        writer.writeTabs()
      }
    }
    writer.write("<![endif]-->")
  }
}
