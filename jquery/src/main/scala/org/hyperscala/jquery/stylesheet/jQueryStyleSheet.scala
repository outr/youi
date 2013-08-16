package org.hyperscala.jquery.stylesheet

import org.hyperscala.web.site.{Website, Webpage}
import org.hyperscala.html._
import org.powerscala.Version
import org.hyperscala.module._
import com.outr.webcommunicator.netty.handler.PathHandler
import org.hyperscala.jquery.jQuery

/**
 * @author Matt Hicks <matt@outr.com>
 */
object jQueryStyleSheet extends Module {
  def name = "jquery-stylesheet"

  def version = Version(0, 3, 6)

  override def dependencies = List(InterfaceWithDefault(jQuery, jQuery.Latest))

  def init() = {
    Website().register(PathHandler("/jquery-stylesheet/", "jquery-stylesheet/"))
  }

  def load() = {
    val page = Webpage()
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "/jquery-stylesheet/jquery.stylesheet.js")
  }
}
