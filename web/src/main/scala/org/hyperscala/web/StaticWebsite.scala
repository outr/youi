package org.hyperscala.web

import org.powerscala.reflect.{EnhancedMethod, EnhancedClass}

/**
 * StaticWebsite trait can be mixed into a Website to allow pages to be more easily defined by
 *
 * @author Matt Hicks <matt@outr.com>
 */
trait StaticWebsite {
  registerStaticPages()

  protected def defaultScope = Scope.Page

  private def registerStaticPages() = {
    val c = EnhancedClass(getClass)
    c.methods.filter(em => em.returnType.`type`.hasType(classOf[Webpage]) && em.name != "webpage").foreach(registerMethod)
  }

  private def registerMethod(em: EnhancedMethod) = {
    val path = s"/${em.name}.html"
    val uris = if (em.name == "index") {
      List(path, "/")
    } else {
      List(path)
    }
    Website().page(em[Webpage](this), defaultScope, uris: _*)
  }
}
