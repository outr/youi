package org.hyperscala.fabricjs.util

import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class Ease extends EnumEntry

object Ease extends Enumerated[Ease] {
  case object InBack extends Ease
  case object InBounce extends Ease
  case object InCirc extends Ease
  case object InCubic extends Ease
  case object InElastic extends Ease
  case object InExpo extends Ease
  case object InOutBack extends Ease
  case object InOutBounce extends Ease
  case object InOutCirc extends Ease
  case object InOutCubic extends Ease
  case object InOutElastic extends Ease
  case object InOutExpo extends Ease
  case object InOutQuad extends Ease
  case object InOutQuart extends Ease
  case object InOutQuint extends Ease
  case object InOutSine extends Ease
  case object InQuad extends Ease
  case object InQuart extends Ease
  case object InQuint extends Ease
  case object InSine extends Ease
  case object OutBack extends Ease
  case object OutBounce extends Ease
  case object OutCirc extends Ease
  case object OutCubic extends Ease
  case object OutElastic extends Ease
  case object OutExpo extends Ease
  case object OutQuad extends Ease
  case object OutQuart extends Ease
  case object OutQuint extends Ease
  case object OutSine extends Ease
  
  val values = findValues.toVector
}