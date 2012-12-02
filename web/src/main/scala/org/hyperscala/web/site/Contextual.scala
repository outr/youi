package org.hyperscala.web.site

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait Contextual {
  protected[web] var webContext: WebContext = _
}
