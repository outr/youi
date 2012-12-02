package org.hyperscala.web.module

import org.hyperscala.web.site.Webpage
import org.powerscala.Version
import org.hyperscala.html.tag

/**
 * @author Matt Hicks <matt@outr.com>
 */
object StyleChange extends Module {
  def name = "stylechange"

  def version = Version(1)

  def load(page: Webpage) = {
    page.website.register("/js/stylechange.js", "stylechange.js")
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/stylechange.js")
  }
}
