package org.hyperscala.examples.basic

import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.ui.dynamic.{DynamicTag, DynamicWebpage}

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class DynamicPageExample extends DynamicWebpage with Example {
  def dynamicTag = DynamicTag.url[tag.HTML](getClass.getName, getClass.getClassLoader.getResource("dynamic_page.html"))

  val message = getById[tag.Strong]("message")

  message.contents.replaceWith("Dynamically updated content from an existing HTML page!")
}