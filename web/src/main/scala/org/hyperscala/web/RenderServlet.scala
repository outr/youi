package org.hyperscala.web

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}
import javax.servlet.ServletConfig

import org.powerscala.reflect._
import org.hyperscala.html.attributes.Method

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
    val methodName = request.getMethod
    val method = Method.values.find(m => m.name().equalsIgnoreCase(methodName)).getOrElse(throw new RuntimeException("Unknown Method type: %s".format(methodName)))
    website.service(method, request, response)
  }

  override def destroy() {
    website.destroy()
    super.destroy()
  }
}

object RenderServlet {
  private var instance: RenderServlet = null

  def apply() = instance
}