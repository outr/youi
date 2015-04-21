package org.hyperscala.jquery.ui

import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * jQuery UI Easings
 *
 * @see http://api.jqueryui.com/easings/
 * @author Matt Hicks <mhicks@outr.com>
 */
sealed abstract class Easing extends EnumEntry {
  lazy val easingName = cs(name)

  protected def cs(s: String) = s.charAt(0).toLower + s.substring(1)
}

object Easing extends Enumerated[Easing] {
  case object Linear extends Easing
  case object Swing extends Easing
  case object EaseInQuad extends Easing
  case object EaseOutQuad extends Easing
  case object EaseInOutQuad extends Easing
  case object EaseInCubic extends Easing
  case object EaseOutCubic extends Easing
  case object EaseInOutCubic extends Easing
  case object EaseInQuart extends Easing
  case object EaseOutQuart extends Easing
  case object EaseInOutQuart extends Easing
  case object EaseInQuint extends Easing
  case object EaseOutQuint extends Easing
  case object EaseInOutQuint extends Easing
  case object EaseInExpo extends Easing
  case object EaseOutExpo extends Easing
  case object EaseInOutExpo extends Easing
  case object EaseInSine extends Easing
  case object EaseOutSine extends Easing
  case object EaseInOutSine extends Easing
  case object EaseInCirc extends Easing
  case object EaseOutCirc extends Easing
  case object EaseInOutCirc extends Easing
  case object EaseInElastic extends Easing
  case object EaseOutElastic extends Easing
  case object EaseInOutElastic extends Easing
  case object EaseInBack extends Easing
  case object EaseOutBack extends Easing
  case object EaseInOutBack extends Easing
  case object EaseInBounce extends Easing
  case object EaseOutBounce extends Easing
  case object EaseInOutBounce extends Easing

  val values = findValues.toVector
}
