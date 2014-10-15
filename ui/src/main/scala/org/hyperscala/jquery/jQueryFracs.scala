package org.hyperscala.jquery

import com.outr.net.http.session.Session
import org.hyperscala.html.tag
import org.hyperscala.module.Module
import org.hyperscala.realtime.Realtime
import org.hyperscala.web.{Webpage, Website}
import org.powerscala.Version

/**
 * @author Matt Hicks <matt@outr.com>
 */
object jQueryFracs extends Module {
  val name = "jquery-fracs"
  val version = Version(0, 15, 0)

  override def dependencies = List(Realtime)

  override def init[S <: Session](website: Website[S]) = {
    val v = s"${version.major}.${version.minor}.${version.maintenance}"
    website.register("/js/jquery.fracs.min.js", s"jquery-fracs-${v}/jquery.fracs-${v}.min.js")
  }

  override def load[S <: Session](webpage: Webpage[S]) = {
    webpage.head.contents += new tag.Script(src = "/js/jquery.fracs.min.js")
  }
}
