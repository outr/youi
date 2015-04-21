package org.hyperscala.tweenjs

import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * Matt Hicks <matt@outr.com>
 */
sealed trait Ease extends EnumEntry

sealed abstract class BlankEase private[tweenjs]() extends Ease {
  def apply() = BlankEaseInstance(this)
}

sealed abstract class AmountEase private[tweenjs]() extends Ease {
  def apply(amount: Double) = AmountEaseInstance(this, amount)
}

sealed abstract class AmplitudePeriodEase private[tweenjs]() extends Ease {
  def apply(amplitude: Double, period: Double) = AmplitudePeriodEaseInstance(this, amplitude, period)
}

sealed abstract class PowerEase private[tweenjs]() extends Ease {
  def apply(pow: Double) = PowerEaseInstance(this, pow)
}

object Ease extends Enumerated[Ease] {
  case object BackIn extends AmountEase
  case object BackInOut extends AmountEase
  case object BackOut extends AmountEase
  case object ElasticIn extends AmplitudePeriodEase
  case object ElasticInOut extends AmplitudePeriodEase
  case object ElasticOut extends AmplitudePeriodEase
  case object PowIn extends PowerEase
  case object PowInOut extends PowerEase
  case object PowOut extends PowerEase
  case object Linear extends BlankEase
  case object None extends BlankEase
  case object QuadIn extends BlankEase
  case object QuadInOut extends BlankEase
  case object QuadOut extends BlankEase
  case object QuartIn extends BlankEase
  case object QuartInOut extends BlankEase
  case object QuartOut extends BlankEase
  case object QuintIn extends BlankEase
  case object QuintInOut extends BlankEase
  case object QuintOut extends BlankEase
  case object SineIn extends BlankEase
  case object SineInOut extends BlankEase
  case object SineOut extends BlankEase

  val values = findValues.toVector
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