package org.hyperscala.examples.basic

import org.powerscala.IO
import org.hyperscala.ui.DynamicWebpage
import org.hyperscala.html._

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class DynamicPageExample extends DynamicWebpage {
  def content = DynamicPageExample.content

  val message = getById[tag.Strong]("message")

  message.contents.replaceWith("Dynamically updated content!")
}

object DynamicPageExample {
  val content = IO.copy(getClass.getClassLoader.getResource("dynamic_page.html"))
}