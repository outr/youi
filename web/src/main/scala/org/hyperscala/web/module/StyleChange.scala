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

  def init[S <: Session](website: Website[S]) = {
    website.register("/js/stylechange.js", "stylechange.js")
  }

  def load[S <: Session](webpage: Webpage[S]) = {
    webpage.head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/stylechange.js")
  }
}
