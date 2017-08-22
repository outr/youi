package io.youi.hypertext.style

import io.youi.Color
import reactify.Var

class ColorProperties(initialRed: Double, initialGreen: Double, initialBlue: Double, initialAlpha: Double) {
  val red: Var[Double] = Var(initialRed)
  val green: Var[Double] = Var(initialGreen)
  val blue: Var[Double] = Var(initialBlue)
  val alpha: Var[Double] = Var(initialAlpha)

  def isDefault: Boolean = red() == initialRed && green() == initialGreen && blue() == initialBlue && alpha() == initialAlpha

  def apply(): Color = Color.fromRGBA(red.get, green.get, blue.get, alpha.get)

  def :=(c: Color): Unit = {
    red := c.red
    green := c.green
    blue := c.blue
    alpha := c.alpha
  }

  def :=(cp: ColorProperties): Unit = {
    red := cp.red
    green := cp.green
    blue := cp.blue
    alpha := cp.alpha
  }
}
