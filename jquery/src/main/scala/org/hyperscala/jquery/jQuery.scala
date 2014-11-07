package org.hyperscala.jquery

import com.outr.net.http.session.Session
import org.hyperscala.html.tag
import org.hyperscala.module._
import org.hyperscala.web.useragent.{BrowserFamily, UserAgent}
import org.hyperscala.web.{Webpage, Website}
import org.powerscala.Version

/**
 * @author Matt Hicks <matt@outr.com>
 */
object jQuery extends Module {
  val name = "jquery"
  def version = version2

  val version1 = Version(1, 11, 0)
  val version2 = Version(2, 1, 1)

  override def init[S <: Session](website: Website[S]) = {}

  override def load[S <: Session](webpage: Webpage[S]) = {
    val userAgent = UserAgent(webpage)
    val v = if (userAgent.browser.family == BrowserFamily.IE && userAgent.browser.version.major < 9) {
      s"${version1.major}.${version1.minor}.${version1.maintenance}"
    } else {
      s"${version2.major}.${version2.minor}.${version2.maintenance}"
    }
    webpage.head.contents += new tag.Script(src = s"//code.jquery.com/jquery-$v.min.js")
  }
}