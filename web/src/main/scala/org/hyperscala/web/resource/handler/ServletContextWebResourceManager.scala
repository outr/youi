package org.hyperscala.web.resource.handler

import org.hyperscala.web.RenderServlet

/**
 * ServletContextWebResourceManager provides access to the servlet context to get WebResources.
 *
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object ServletContextWebResourceManager extends URLWebResourceManager {
  def lookup(uri: String) = RenderServlet().getServletContext.getResource(uri)
}