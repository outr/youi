package org.hyperscala.fabricjs.util

import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Ease private() extends EnumEntry

object Ease extends Enumerated[Ease] {
  val InBack = new Ease()
  val InBounce = new Ease()
  val InCirc = new Ease()
  val InCubic = new Ease()
  val InElastic = new Ease()
  val InExpo = new Ease()
  val InOutBack = new Ease()
  val InOutBounce = new Ease()
  val InOutCirc = new Ease()
  val InOutCubic = new Ease()
  val InOutElastic = new Ease()
  val InOutExpo = new Ease()
  val InOutQuad = new Ease()
  val InOutQuart = new Ease()
  val InOutQuint = new Ease()
  val InOutSine = new Ease()
  val InQuad = new Ease()
  val InQuart = new Ease()
  val InQuint = new Ease()
  val InSine = new Ease()
  val OutBack = new Ease()
  val OutBounce = new Ease()
  val OutCirc = new Ease()
  val OutCubic = new Ease()
  val OutElastic = new Ease()
  val OutExpo = new Ease()
  val OutQuad = new Ease()
  val OutQuart = new Ease()
  val OutQuint = new Ease()
  val OutSine = new Ease()
}