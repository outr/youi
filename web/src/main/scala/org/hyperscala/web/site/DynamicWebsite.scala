package org.hyperscala.web.site

import org.powerscala.reflect._
import org.hyperscala.web.Scope

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait DynamicWebsite {
  registerDynamicPages()

  private def registerDynamicPages() = {
    val c = EnhancedClass(getClass)
    c.methods.filter(em => em.returnType.`type`.hasType(classOf[Webpage]) && em.name != "webpage").foreach(registerMethod)
  }

  private def registerMethod(em: EnhancedMethod) = {
    val path = s"/${em.name}.html"
    val resource = WebpageResource(path, em[Webpage](this), Scope.Page)
    if (em.name == "index") {
      resource.matchers += resource.matches("/")
    }
    resource
  }
}

