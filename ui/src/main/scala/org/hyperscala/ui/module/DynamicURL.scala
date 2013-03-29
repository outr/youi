package org.hyperscala.ui.module

import org.hyperscala.module.Module
import org.powerscala.Version
import org.hyperscala.web.site.{Webpage, Website}
import org.hyperscala.html._
import org.hyperscala.realtime.Realtime

/**
 * @author Matt Hicks <matt@outr.com>
 */
object DynamicURL extends Module {
  val name = "dynamicURL"
  val version = Version(1)

  override val dependencies = List(Realtime)

  def init() = {
    Website().register("/js/dynamic_url.js", "dynamic_url.js")
  }

  def load() = {
    Webpage().head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/dynamic_url.js")
    Webpage().body.handle("hashChanged") {
      case message => println("Hash has changed: %s".format(message("hash")))
    }
  }
}