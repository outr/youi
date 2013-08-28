package org.hyperscala.jquery.ui

import org.hyperscala.web.site.{Website, Webpage}
import org.hyperscala.html._
import org.powerscala.Version
import org.hyperscala.module._
import com.outr.webcommunicator.netty.handler.PathHandler
import org.hyperscala.jquery.jQuery
import org.powerscala.property.Property

/**
 * @author Matt Hicks <matt@outr.com>
 */
object jQueryUI1103 extends Module {
  def name = "jquery-ui"
  val theme = Property[Theme](default = Option(Theme.Redmond))

  def version = Version(1, 10, 3)

  override def implements = List(jQueryUI)

  override def dependencies = List(InterfaceWithDefault(jQuery, jQuery.Latest))

  def init() = {
    Website().register(PathHandler("/jquery-ui-1.10.3/", "jquery-ui-1.10.3/"))
  }

  def themeCSS = {
    val t = theme()
    if (t.cssPath != null) {
      t.cssPath
    } else {
      s"/jquery-ui-1.10.3/css/${t.directory}/jquery-ui.min.css"
    }
  }

  def load() = {
    val page = Webpage()
    page.head.contents += new tag.Link(href = themeCSS, rel = "stylesheet")
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "/jquery-ui-1.10.3/js/jquery-ui-1.10.3.custom.min.js")
  }
}
