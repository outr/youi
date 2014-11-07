package org.hyperscala.jquery

import com.outr.net.http.session.Session
import org.hyperscala.html._
import org.hyperscala.module.Module
import org.hyperscala.web.{Webpage, Website}
import org.powerscala.Version

/**
 * @author Matt Hicks <matt@outr.com>
 */
object jQuery1110 extends Module {
  val name = "jquery"
  val version = Version(1, 11, 0)

  override def implements = List(jQuery)

  override def init[S <: Session](website: Website[S]) = {}

  override def load[S <: Session](webpage: Webpage[S]) = {
    val v = s"${version.major}.${version.minor}.${version.maintenance}"
    webpage.head.contents += new tag.Script(src = s"//code.jquery.com/jquery-$v.min.js")
  }
}
