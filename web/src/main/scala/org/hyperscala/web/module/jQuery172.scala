package org.hyperscala.web.module

import org.hyperscala.html._
import org.hyperscala.web.HTMLPage

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object jQuery172 extends Module {
  def name = "jquery"

  def version = "1.7.2"

  def load(page: HTMLPage) = {
    page.website.register("/jquery-1.7.2.js", "jquery-1.7.2.js")
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "/jquery-1.7.2.js")
  }
}
