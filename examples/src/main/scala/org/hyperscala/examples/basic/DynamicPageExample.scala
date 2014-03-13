package org.hyperscala.examples.basic

import org.hyperscala.html._
import org.hyperscala.ui.dynamic.{DynamicWebpage, DynamicTag}

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class DynamicPageExample extends DynamicWebpage {
  def dynamicTag = DynamicTag.url[tag.HTML](getClass.getName, getClass.getClassLoader.getResource("dynamic_page.html"))

  val message = getById[tag.Strong]("message")

  message.contents.replaceWith("Dynamically updated content from an existing HTML page!")
}