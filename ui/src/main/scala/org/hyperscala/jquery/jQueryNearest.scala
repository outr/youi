package org.hyperscala.jquery

import org.hyperscala.module.Module
import org.powerscala.Version
import org.hyperscala.web.{Webpage, Website}
import org.hyperscala.html.tag

/**
 * @author Matt Hicks <matt@outr.com>
 */
object jQueryNearest extends Module {
  val name = "jquery.nearest"

  val version = Version(3, 1, 9)

  override def dependencies = List(jQuery.LatestWithDefault)

  def init() = {
    Website().register("/js/jquery.nearest.js", "jquery.nearest.js")
  }

  def load() = {
    val page = Webpage()
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/jquery.nearest.js")
  }
}
