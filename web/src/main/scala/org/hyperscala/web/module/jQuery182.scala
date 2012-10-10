package org.hyperscala.web.module

import org.hyperscala.web.HTMLPage
import org.hyperscala.html.tag

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object jQuery182 extends Module {
  def name = "jquery"

  def version = "1.8.2"

  def load(page: HTMLPage) = {
    page.website.register("/jquery-1.8.2.js", "jquery-1.8.2.min.js")
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "/jquery-1.8.2.js")
  }
}
