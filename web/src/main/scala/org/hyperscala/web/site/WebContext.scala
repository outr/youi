package org.hyperscala.web.site

import org.hyperscala.web.cookie.{Cookie, Cookies}
import org.hyperscala.web.session.Session
import org.jboss.netty.handler.codec.http.{Cookie => NettyCookie, CookieDecoder, HttpRequest}
import scala.collection.JavaConversions._
import com.outr.webcommunicator.URL
import com.outr.webcommunicator.netty._
import org.hyperscala.{Page, Unique}
import org.powerscala.bus.Bus
import org.powerscala.Logging

/**
 * @author Matt Hicks <matt@outr.com>
 */
class WebContext {
  private var _webpage: Webpage = _
  def webpage = {
    if (_webpage == null) {
      _webpage = Page().asInstanceOf[Webpage]
    }
    _webpage
  }
  def webpage_=(page: Webpage) = {
    _webpage = page
    if (page != null) {
      Page.instance.set(page)
      page.webContext = this
      Bus.current = page.bus
    }
  }
  var headers: Map[String, String] = _
  var url: URL = _
  var cookies: Cookies = _
  var session: Session = _
}

object WebContext extends Logging {
  val current = new ThreadLocal[WebContext]

  protected[web] def apply() = current.get() match {
    case null => throw new OutOfContextException
    case c => c
  }

  def inContext = current.get() != null

  def wrap[T](session: Session = null, webpage: Webpage = null, url: URL = null, headers: Map[String, String] = Map.empty, cookies: Cookies = Cookies(Map.empty, Map.empty), checkIn: Boolean = true)(action: => T): T = {
    val context = new WebContext
    val previous = current.get()
    val previousPage = Page.instance.get()
    val previousBus = Bus.current
    try {
      context.session = session
      context.webpage = webpage
      context.url = url
      context.headers = headers
      context.cookies = cookies
      Page.instance.set(context.webpage)

      if (checkIn) {
        if (context.webpage != null) {
          context.webpage.checkIn()
        } else if (context.session != null) {
          context.session.checkIn()
        }
      }
      if (context.webpage != null) {
        Bus.current = context.webpage.bus
      }
      action
    } finally {
      current.set(previous)
      Page.instance.set(previousPage)
      Bus.current = previousBus
    }
  }

  protected[web] def apply[T](context: WebContext, checkIn: Boolean)(action: => T): T = {
    val previous = current.get()
    current.set(context)
    val previousPage = Page.instance.get()    // Work-around for modularity
    Page.instance.set(context.webpage)
    if (context.webpage != null) {
      Bus.current = context.webpage.bus
    }
    if (checkIn) {
      if (context.webpage != null) {
        context.webpage.checkIn()     // Webpage checks-in session
      } else if (context.session != null) {
        context.session.checkIn()
      }
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
      val cs = List(context.cookies.get("%sWild".format(website.sessionCookieKey)), context.cookies.get(website.sessionCookieKey)).flatten
      val sessionCookie = cs.headOption match {
        case Some(cookie) => cookie
        case None => {
          val cookie = Cookie(name = website.sessionCookieKey,
                              value = Unique(),
                              maxAge = website.sessionCookieLifetime)
          context.cookies(cookie)   // Set a new cookie for the response
          context.url.domain match {    // Set a cookie for all subdomains
            case d if (website.sessionCookieWildcard && d.indexOf('.') != -1) => {
              context.cookies(cookie.copy(name = "%sWild".format(cookie.name), domain = ".%s".format(d)))
            }
            case _ => // Don't set a wildcard cookie
          }
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