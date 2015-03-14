package org.hyperscala.web.module

import org.hyperscala.web.{Website, Webpage}
import org.powerscala.Version
import org.hyperscala.html.tag
import org.hyperscala.module._
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <matt@outr.com>
 */
object StyleChange extends Module {
  def name = "stylechange"

  def version = Version(1)

  def init(website: Website) = {
    website.register("/js/stylechange.js", "stylechange.js")
  }

  def load(webpage: Webpage) = {
    webpage.head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/stylechange.js")
  }
}
