package org.hyperscala

import scala.xml.{Text => TextNode}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait PreFormatted extends WebContent {
  def preFormatted: Option[String] = None

  override def afterRender() {
    super.afterRender()
    preFormatted match {
      case Some(text) => {
        WebContent.render = WebContent.render.replaceAllLiterally(uuid.toString, text)
      }
      case None =>
    }
  }
}
