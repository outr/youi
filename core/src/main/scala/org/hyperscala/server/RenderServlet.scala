package com.outr.webframework.server

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}
import javax.servlet.ServletConfig

import org.sgine.reflect._
import com.outr.webframework.{WebPage, WebSite}

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
class RenderServlet extends HttpServlet {
  private var webSite: WebSite = _
  private var pages = Map.empty[String, WebPage]

  override def init(config: ServletConfig) {
    super.init(config)

    val webSiteClassName = config.getInitParameter("webSite")
    val webSiteClass = Class.forName(webSiteClassName)
    webSite = webSiteClass.instance match {
      case Some(companion) if (companion.isInstanceOf[WebSite]) => companion.asInstanceOf[WebSite]
      case _ => webSiteClass.newInstance().asInstanceOf[WebSite]
    }
    pages = webSite.pages.map(page => page.filename -> page).toMap
  }

  override def doGet(request: HttpServletRequest, response: HttpServletResponse) {
    val writer = response.getWriter
    val filename = request.getRequestURI.substring(1)
    val webPage = pages.get(filename) match {
      case Some(page) => page
      case None => webSite.default
    }
    webPage.write(writer)
    writer.close()
  }
}
