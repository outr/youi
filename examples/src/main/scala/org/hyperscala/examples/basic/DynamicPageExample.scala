package org.hyperscala.examples.basic

import org.hyperscala.ui.DynamicWebpage
import org.hyperscala.html._
import org.hyperscala.ui.dynamic.DynamicTag

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class DynamicPageExample extends DynamicWebpage {
//  def content = DynamicPageExample.content

  def dynamicTag = DynamicTag[tag.HTML](getClass.getName, getClass.getClassLoader.getResource("dynamic_page.html"))

  val message = getById[tag.Strong]("message")

  message.contents.replaceWith("Dynamically updated content!")
}