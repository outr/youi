package org.hyperscala.jquery.stylesheet

import com.outr.net.http.session.Session
import org.hyperscala.html._
import org.hyperscala.jquery.jQuery
import org.hyperscala.module._
import org.hyperscala.web.{Webpage, Website}
import org.powerscala.Version

/**
 * @author Matt Hicks <matt@outr.com>
 */
object jQueryStyleSheet extends Module {
  def name = "jquery-stylesheet"

  def version = Version(0, 3, 6)

  override def dependencies = List(jQuery)

  override def init[S <: Session](website: Website[S]) = {
    website.addClassPath("/jquery-stylesheet/", "jquery-stylesheet/")
  }

  override def load[S <: Session](webpage: Webpage[S]) = {
    webpage.head.contents += new tag.Script(mimeType = "text/javascript", src = "/jquery-stylesheet/jquery.stylesheet.js")
  }
}
