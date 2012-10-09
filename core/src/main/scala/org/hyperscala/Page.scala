package org.hyperscala

import intercept.Intercepting

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait Page extends Intercepting {
  Page.instance.set(this)
}

object Page {
  val instance = new ThreadLocal[Page]

  def apply() = instance.get()
}