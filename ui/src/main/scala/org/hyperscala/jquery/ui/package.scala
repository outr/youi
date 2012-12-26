package org.hyperscala.jquery

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
package object ui {
  implicit def effect2EffectInstance(effect: Effect) = effect.instance()
}
