package org.hyperscala.easeljs

import com.outr.net.http.session.Session
import org.hyperscala.module.Module
import org.hyperscala.realtime.Realtime
import org.hyperscala.web.{Website, Webpage}
import org.powerscala.Version
import org.hyperscala.html._

/**
 * Matt Hicks <matt@outr.com>
 */
object EaselJS extends Module {
  val name = "easelJS"
  val version = Version(0, 8, 0)

  override def dependencies = List(Realtime)

  override def init[S <: Session](website: Website[S]) = {
    website.register("/js/hyperscala-easel.js", "hyperscala-easel.js")
  }

  override def load[S <: Session](webpage: Webpage[S]) = {
    webpage.head.contents += new tag.Script(src = s"https://code.createjs.com/easeljs-0.8.0.min.js")
    webpage.head.contents += new tag.Script(src = "/js/hyperscala-easel.js")
  }
}
