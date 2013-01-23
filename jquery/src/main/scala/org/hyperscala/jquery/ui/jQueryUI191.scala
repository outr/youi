package org.hyperscala.jquery.ui

import org.hyperscala.web.site.{Website, Webpage}
import org.hyperscala.html._
import org.powerscala.Version
import org.hyperscala.module._
import com.outr.webcommunicator.netty.handler.PathHandler
import org.hyperscala.jquery.{jQuery182, jQuery}

/**
 * @author Matt Hicks <matt@outr.com>
 */
object jQueryUI191 extends Module {
  def name = "jquery-ui"

  def version = Version(1, 9, 1)

  override def implements = List(jQueryUI)

  override def dependencies = List(InterfaceWithDefault(jQuery, jQuery182))

  def init() = {
    Website().register(PathHandler("/jquery_ui/", "jquery_ui/"))
  }

  def load() = {
    val page = Webpage()
    page.head.contents += new tag.Link(href = "/jquery_ui/jquery-ui-1.9.1.custom.min.css", rel = "stylesheet")
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "/jquery_ui/jquery-ui-1.9.1.custom.min.js")
  }
}
