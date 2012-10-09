package org.hyperscala.intercept

import org.hyperscala.Markup

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
abstract class Interceptor[T <: Markup](implicit val manifest: Manifest[T]) {
  def init(markup: T): Unit = {}

  def before(markup: T): Unit = {}

  def after(markup: T): Unit = {}
}
