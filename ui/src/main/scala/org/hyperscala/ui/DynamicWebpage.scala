package org.hyperscala.ui

import org.hyperscala.web.site.Webpage
import org.jdom2.Element
import java.io.StringReader
import org.hyperscala.html._

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
abstract class DynamicWebpage extends Webpage {
  def content: String

  private lazy val dynamicHTML = DynamicWebpage.html(content)
  override def html = dynamicHTML
}

object DynamicWebpage {
  private var map = Map.empty[String, Element]

  // TODO: add support for DynamicTag to provide an integration point between DynamicWebpage and DynamicContent and allow a specific tag to be loaded from a file and fully parsed.

  def html(content: String) = {
    val element = htmlXML(content)
    val html = HTMLTag.create("html").asInstanceOf[tag.HTML]
    html.read(element)
    html
  }

  private def htmlXML(content: String) = synchronized {
    map.get(content) match {
      case Some(element) => element
      case None => {
        val element = DynamicContent.builder.build(new StringReader(content)).getRootElement
        map += content -> element
        element
      }
    }
  }
}