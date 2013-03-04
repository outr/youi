package org.hyperscala.web.site

/**
 * SessionContextualizable allows easy ability to iterate over and communicate to all instances of a specific
 * Webpage type.
 *
 * @author Matt Hicks <matt@outr.com>
 */
class SessionContextualizable[T <: Webpage](implicit manifest: Manifest[T]) {
  def contextualize(f: T => Unit) = {
    Website().sessions.valuesByType[T](manifest).foreach {
      case page => {
        page.context {
          f(page)
        }
      }
    }
  }
}