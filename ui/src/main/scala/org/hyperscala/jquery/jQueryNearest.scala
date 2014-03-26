package org.hyperscala.jquery

import org.hyperscala.module.Module
import org.powerscala.Version
import org.hyperscala.web.{Webpage, Website}
import org.hyperscala.html.tag
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <matt@outr.com>
 */
object jQueryNearest extends Module {
  val name = "jquery.nearest"

  val version = Version(3, 1, 9)

  override def dependencies = List(jQuery.LatestWithDefault)

  override def init[S <: Session](website: Website[S]) = {
    website.register("/js/jquery.nearest.js", "jquery.nearest.js")
  }

  override def load[S <: Session](webpage: Webpage[S]) = {
    webpage.head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/jquery.nearest.js")
  }
}
