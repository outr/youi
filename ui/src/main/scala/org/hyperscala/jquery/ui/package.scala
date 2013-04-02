package org.hyperscala.jquery

import org.hyperscala.html._

import language.implicitConversions

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
package object ui {
  implicit def effect2EffectInstance(effect: Effect) = effect.instance()
  implicit def input2Autocompletified(input: tag.Input) = Autocompletified(input)
}
