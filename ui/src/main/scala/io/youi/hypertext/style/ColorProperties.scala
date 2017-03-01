package io.youi.hypertext.style

import reactify.Var

class ColorProperties {
  val red: Var[Double] = Var(1.0)
  val green: Var[Double] = Var(1.0)
  val blue: Var[Double] = Var(1.0)
  val alpha: Var[Double] = Var(1.0)

  def apply() = Color(red.get, green.get, blue.get, alpha.get)

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
