package org.hyperscala.web.resource.handler

import org.hyperscala.web.RenderServlet

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object ServletContextWebResourceManager extends URLWebResourceManager {
  def lookup(uri: String) = RenderServlet().getServletContext.getResource(uri)
}