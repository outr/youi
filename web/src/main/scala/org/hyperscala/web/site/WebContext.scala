package org.hyperscala.web.site

import org.hyperscala.web.cookie.{Cookie, Cookies}
import org.hyperscala.web.session.Session
import org.jboss.netty.handler.codec.http.{Cookie => NettyCookie, CookieDecoder, HttpRequest}
import scala.collection.JavaConversions._
import com.outr.webcommunicator.URL
import com.outr.webcommunicator.netty._
import org.hyperscala.{Page, Unique}
import org.powerscala.bus.Bus

/**
 * @author Matt Hicks <matt@outr.com>
 */
class WebContext {
  var website: Website[_ <: Session] = _
  private var _webpage: Webpage = _
  def webpage = {
    if (_webpage == null) {
      _webpage = Page().asInstanceOf[Webpage]
    }
    _webpage
  }
  def webpage_=(page: Webpage) = {
    _webpage = page
    Page.instance.set(page)
    page.webContext = this
    Bus.current = page.bus
  }
  var headers: Map[String, String] = _
  var url: URL = _
  var cookies: Cookies = _
  var session: Session = _
}

object WebContext {
  val current = new ThreadLocal[WebContext]

  protected[web] def apply() = current.get() match {
    case null => throw new OutOfContextException
    case c => c
  }

  def inContext = current.get() != null

  protected[site] def apply[T](context: WebContext)(action: => T): T = {
    val previous = current.get()
    current.set(context)
    val previousPage = Page.instance.get()    // Work-around for modularity
    Page.instance.set(context.webpage)
    if (context.webpage != null) {
      Bus.current = context.webpage.bus
    }
    try {
      action
    } finally {
      current.set(previous)
      Page.instance.set(previousPage)
      Bus.current = null
    }
  }

  protected[site] def create(website: Website[_ <: Session], request: HttpRequest) = {
    val context = new WebContext
    current.set(context)
    try {
      context.website = website
      context.headers = request.getHeaders.map(entry => entry.getKey -> entry.getValue).toMap
      context.url = request

      // Process cookies
      val cookieHeader = request.getHeader("Cookie")
      val cookies = if (cookieHeader != null) {
        val cookieSet = new CookieDecoder().decode(cookieHeader)
        cookieSet.map(cookieConverter).toMap
      } else {
        Map.empty[String, Cookie]
      }
      context.cookies = Cookies(cookies, cookies)

      // Load or create session
      val sessionCookie = context.cookies.get(website.sessionCookieKey) match {
        case Some(cookie) => cookie
        case None => {
          val domain = context.url.domain match {
            case d if (d.indexOf('.') == -1) => null
            case d => d
          }
          val cookie = Cookie(name = website.sessionCookieKey,
                              value = Unique(),
                              domain = domain,
                              maxAge = website.sessionCookieLifetime)
          context.cookies(cookie)   // Set a new cookie for the response
          cookie
        }
      }
      context.session = website.sessions.get(sessionCookie.value) match {
        case Some(s) => s.asInstanceOf[Session]
        case None => website.instantiateSession(sessionCookie.value).asInstanceOf[Session]
      }
    } finally {
      current.set(null)
    }
    context
  }

  private def cookieConverter(cookie: NettyCookie) = {
    cookie.getName -> Cookie(name = cookie.getName,
      value = cookie.getValue,
      comment = cookie.getComment,
      commentUrl = cookie.getCommentUrl,
      discard = cookie.isDiscard,
      domain = cookie.getDomain,
      httpOnly = cookie.isHttpOnly,
      maxAge = cookie.getMaxAge,
      path = cookie.getPath,
      ports = cookie.getPorts.toList.map(i => i.intValue()),
      secure = cookie.isSecure,
      version = cookie.getVersion)
  }
}