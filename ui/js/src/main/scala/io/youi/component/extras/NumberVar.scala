package io.youi.component.extras

import scala.language.implicitConversions

class NumberVar extends StandardVar[Dim](Dim.Auto, Mode.Normal, None)

object NumberVar {
  implicit def toDouble(v: NumberVar): Double = v.value
}