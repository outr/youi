package org.hyperscala.jquery.ui

import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * jQuery UI Easings
 *
 * @see http://api.jqueryui.com/easings/
 * @author Matt Hicks <mhicks@outr.com>
 */
sealed class Easing extends EnumEntry {
  lazy val easingName = cs(name)

  protected def cs(s: String) = s.charAt(0).toLower + s.substring(1)
}

object Easing extends Enumerated[Easing] {
  val Linear = new Easing
  val Swing = new Easing
  val EaseInQuad = new Easing
  val EaseOutQuad = new Easing
  val EaseInOutQuad = new Easing
  val EaseInCubic = new Easing
  val EaseOutCubic = new Easing
  val EaseInOutCubic = new Easing
  val EaseInQuart = new Easing
  val EaseOutQuart = new Easing
  val EaseInOutQuart = new Easing
  val EaseInQuint = new Easing
  val EaseOutQuint = new Easing
  val EaseInOutQuint = new Easing
  val EaseInExpo = new Easing
  val EaseOutExpo = new Easing
  val EaseInOutExpo = new Easing
  val EaseInSine = new Easing
  val EaseOutSine = new Easing
  val EaseInOutSine = new Easing
  val EaseInCirc = new Easing
  val EaseOutCirc = new Easing
  val EaseInOutCirc = new Easing
  val EaseInElastic = new Easing
  val EaseOutElastic = new Easing
  val EaseInOutElastic = new Easing
  val EaseInBack = new Easing
  val EaseOutBack = new Easing
  val EaseInOutBack = new Easing
  val EaseInBounce = new Easing
  val EaseOutBounce = new Easing
  val EaseInOutBounce = new Easing
}
