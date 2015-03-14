package org.hyperscala.jquery

import com.outr.net.http.session.Session
import org.hyperscala.html.tag
import org.hyperscala.module.Module
import org.hyperscala.web.{Webpage, Website}
import org.powerscala.Version

/**
 * @author Matt Hicks <matt@outr.com>
 */
object jQueryNearest extends Module {
  val name = "jquery.nearest"

  val version = Version(3, 1, 9)

  override def dependencies = List(jQuery)

  override def init(website: Website) = {
    website.register("/js/jquery.nearest.js", "jquery.nearest.js")
  }

  override def load(webpage: Webpage) = {
    webpage.head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/jquery.nearest.js")
  }
}
