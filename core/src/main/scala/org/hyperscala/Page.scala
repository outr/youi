package org.hyperscala

import org.powerscala.event.Listenable

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait Page extends Listenable {
  Page.instance.set(this)

  protected def parentIntercept: MarkupIntercepting = null

  val intercept = new MarkupIntercepting("Page", bus, parentIntercept)
}

object Page {
  val instance = new ThreadLocal[Page]

  def apply() = instance.get()
}