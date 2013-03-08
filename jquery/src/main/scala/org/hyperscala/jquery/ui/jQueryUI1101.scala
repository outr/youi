package org.hyperscala.jquery.ui

import org.hyperscala.web.site.{Website, Webpage}
import org.hyperscala.html._
import org.powerscala.Version
import org.hyperscala.module._
import com.outr.webcommunicator.netty.handler.PathHandler
import org.hyperscala.jquery.jQuery

/**
 * @author Matt Hicks <matt@outr.com>
 */
object jQueryUI1101 extends Module {
  def name = "jquery-ui"

  def version = Version(1, 10, 1)

  override def implements = List(jQueryUI)

  override def dependencies = List(InterfaceWithDefault(jQuery, jQuery.Latest))

  def init() = {
    Website().register(PathHandler("/jquery-ui-1.10.1/", "jquery-ui-1.10.1/"))
  }

  def load() = {
    val page = Webpage()
    page.head.contents += new tag.Link(href = "/jquery-ui-1.10.1/jquery-ui-1.10.1.custom.min.css", rel = "stylesheet")
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "/jquery-ui-1.10.1/jquery-ui-1.10.1.custom.min.js")
  }
}
