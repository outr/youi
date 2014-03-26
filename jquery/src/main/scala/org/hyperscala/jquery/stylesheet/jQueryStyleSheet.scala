package org.hyperscala.jquery.stylesheet

import org.hyperscala.web.{Website, Webpage}
import org.hyperscala.html._
import org.powerscala.Version
import org.hyperscala.module._
import org.hyperscala.jquery.jQuery
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <matt@outr.com>
 */
object jQueryStyleSheet extends Module {
  def name = "jquery-stylesheet"

  def version = Version(0, 3, 6)

  override def dependencies = List(InterfaceWithDefault(jQuery, jQuery.Latest))

  override def init[S <: Session](website: Website[S]) = {
    website.addClassPath("/jquery-stylesheet/", "jquery-stylesheet/")
  }

  override def load[S <: Session](webpage: Webpage[S]) = {
    webpage.head.contents += new tag.Script(mimeType = "text/javascript", src = "/jquery-stylesheet/jquery.stylesheet.js")
  }
}
