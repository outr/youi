package org.hyperscala

import org.sgine.reflect._

/**
 * List WebPage instances that should be processed and converted to HTML pages.
 *
 * @author Matt Hicks <mhicks@sgine.org>
 */
trait WebSite {
  def default: WebPage

  def pages = getClass.methods.filter {
    m => classOf[WebPage].isAssignableFrom(m.returnType.`type`.javaClass)
  }.map(m => m.invoke[WebPage](this))
}