package org.hyperscala.jquery.ui

import org.hyperscala.web.{Website, Webpage}
import org.hyperscala.html._
import org.powerscala.Version
import org.hyperscala.module._
import org.hyperscala.jquery.jQuery
import org.powerscala.property.Property
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <matt@outr.com>
 */
object jQueryUI1103 extends Module {
  def name = "jquery-ui"
  val theme = Property[Theme](default = Option(Theme.Redmond))

  def version = Version(1, 10, 3)

  override def implements = List(jQueryUI)

  override def dependencies = List(InterfaceWithDefault(jQuery, jQuery.Latest))

  override def init[S <: Session](website: Website[S]) = {
    website.addClassPath("/jquery-ui-1.10.3", "jquery-ui-1.10.3")
  }

  override def load[S <: Session](webpage: Webpage[S]) = {
    webpage.head.contents += new tag.Link(href = themeCSS, rel = "stylesheet")
    webpage.head.contents += new tag.Script(mimeType = "text/javascript", src = "/jquery-ui-1.10.3/js/jquery-ui-1.10.3.custom.min.js")
  }

  def themeCSS = {
    val t = theme()
    if (t.cssPath != null) {
      t.cssPath
    } else {
      s"/jquery-ui-1.10.3/css/${t.directory}/jquery-ui.min.css"
    }
  }
}
