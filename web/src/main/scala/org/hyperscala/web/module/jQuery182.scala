package org.hyperscala.web.module

import org.hyperscala.html.tag
import org.hyperscala.web.site.Webpage
import org.powerscala.Version

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object jQuery182 extends Module {
  def name = "jquery"

  def version = Version(1, 8, 2)

  def load(page: Webpage) = {
    page.website.register("/jquery-1.8.2.js", "jquery-1.8.2.min.js")
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "/jquery-1.8.2.js")
  }
}
