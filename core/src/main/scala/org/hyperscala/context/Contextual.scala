package org.hyperscala.context

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
trait Contextual {
  protected[context] val contextInstance: ContextInstance = Context().contextInstance
}
