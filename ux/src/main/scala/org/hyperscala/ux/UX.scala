package org.hyperscala.ux

import com.outr.net.http.session.Session
import org.hyperscala.html._
import org.hyperscala.jquery.jQuery
import org.hyperscala.module.{InterfaceWithDefault, Module}
import org.hyperscala.web.{Webpage, Website}
import org.powerscala.Version

/**
 * @author Matt Hicks <matt@outr.com>
 */
object UX extends Module {
  def name = "hyperscala-ux"
  def version = Version(1, 0, 0)

  override def dependencies = List(InterfaceWithDefault(jQuery, jQuery.Latest))

  override def init[S <: Session](website: Website[S]) = {
    website.addClassPath("/ux", "ux")
  }

  override def load[S <: Session](webpage: Webpage[S]) = {
    webpage.head.contents += new tag.Script(src = "/ux/dropdown.js")
  }
}