package org.hyperscala.web.cookie

import org.hyperscala.web.site.WebContext

/**
 * @author Matt Hicks <matt@outr.com>
 */
case class Cookies(cookies: Map[String, Cookie], original: Map[String, Cookie]) {
  def apply(name: String) = cookies(name)
  def get(name: String) = cookies.get(name)
  def getOrElse(name: String, default: => Cookie) = cookies.getOrElse(name, default)
  def apply(cookie: Cookie) = {
    val updated = cookies + (cookie.name -> cookie)
    WebContext().cookies = copy(cookies = updated)
    updated
  }
  def modified = cookies.collect {
    case (name, cookie) if (isModified(cookie)) => cookie
  }
  def isModified(cookie: Cookie) = cookie != original.getOrElse(cookie.name, null)
}
