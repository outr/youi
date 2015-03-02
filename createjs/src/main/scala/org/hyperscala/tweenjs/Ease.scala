package org.hyperscala.tweenjs

import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * Matt Hicks <matt@outr.com>
 */
trait Ease extends EnumEntry

class BlankEase private[tweenjs]() extends Ease {
  def apply() = BlankEaseInstance(this)
}

class AmountEase private[tweenjs]() extends Ease {
  def apply(amount: Double) = AmountEaseInstance(this, amount)
}

class AmplitudePeriodEase private[tweenjs]() extends Ease {
  def apply(amplitude: Double, period: Double) = AmplitudePeriodEaseInstance(this, amplitude, period)
}

class PowerEase private[tweenjs]() extends Ease {
  def apply(pow: Double) = PowerEaseInstance(this, pow)
}

object Ease extends Enumerated[Ease] {
  val BackIn = new AmountEase
  val BackInOut = new AmountEase
  val BackOut = new AmountEase
  val ElasticIn = new AmplitudePeriodEase
  val ElasticInOut = new AmplitudePeriodEase
  val ElasticOut = new AmplitudePeriodEase
  val PowIn = new PowerEase
  val PowInOut = new PowerEase
  val PowOut = new PowerEase
  val Linear = new BlankEase
  val None = new BlankEase
  val QuadIn = new BlankEase
  val QuadInOut = new BlankEase
  val QuadOut = new BlankEase
  val QuartIn = new BlankEase
  val QuartInOut = new BlankEase
  val QuartOut = new BlankEase
  val QuintIn = new BlankEase
  val QuintInOut = new BlankEase
  val QuintOut = new BlankEase
  val SineIn = new BlankEase
  val SineInOut = new BlankEase
  val SineOut = new BlankEase
}

trait EaseInstance {
  def ease: Ease

  def toJS: String
}

case class AmountEaseInstance(ease: Ease, amount: Double) extends EaseInstance {
  def toJS = s"createjs.Ease.get${ease.name}($amount)"
}

case class AmplitudePeriodEaseInstance(ease: Ease, amplitude: Double, period: Double) extends EaseInstance {
  def toJS = s"createjs.Ease.get${ease.name}($amplitude, $period)"
}

case class PowerEaseInstance(ease: Ease, pow: Double) extends EaseInstance {
  def toJS = s"createjs.Ease.get${ease.name}($pow)"
}

case class BlankEaseInstance(ease: Ease) extends EaseInstance {
  def toJS = if (ease == Ease.Linear) {
    s"createjs.Ease.linear"
  } else {
    s"createjs.Ease.get${ease.name}()"
  }
}