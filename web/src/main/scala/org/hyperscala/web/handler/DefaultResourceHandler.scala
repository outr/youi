package org.hyperscala.web.handler

import org.powerscala.Priority
import org.hyperscala.web.RenderServlet

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object DefaultResourceHandler extends ResourceHandler {
  def link = null

  def lookup(uri: String) = if (uri.trim.length > 1) {
    // Don't want to match the context root
    RenderServlet().getServletContext.getResource(uri) match {
      case null => classLoader.getResource(uri.substring(1))
      case url => url
    }
  } else {
    null
  }

  override def priority = Priority.Low
}
