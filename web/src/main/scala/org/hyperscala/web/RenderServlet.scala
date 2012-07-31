package org.hyperscala.web

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}
import javax.servlet.ServletConfig

import org.powerscala.reflect._

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class RenderServlet extends HttpServlet {
  private var website: Website[_] = null

  override def init(config: ServletConfig) = {
    super.init(config)
    RenderServlet.instance = this

    val websiteClassName = config.getInitParameter("website")
    val websiteClass = Class.forName(websiteClassName)
    val companion = websiteClass.instance.getOrElse(throw new RuntimeException("Website (%s) instance should be object, not instance!".format(websiteClassName)))
    website = companion.asInstanceOf[Website[_]]
    website.reload(config)
  }

  override def service(request: HttpServletRequest, response: HttpServletResponse) = {
    val method = Method(request.getMethod)
    website.service(method, request, response)
  }
}

object RenderServlet {
  private var instance: RenderServlet = null

  def apply() = instance
}