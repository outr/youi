package org.hyperscala

import scala.xml.{Text => TextNode}

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
trait PreFormatted extends WebContent {
  def preFormatted: Option[String] = None

  override def afterRender() {
    super.afterRender()
    preFormatted match {
      case Some(text) => WebContent.render = WebContent.render.replaceAll(uuid.toString, text)
      case None =>
    }
  }
}
