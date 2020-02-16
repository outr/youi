package io.youi.gui

import reactify.Val

trait SizeSupport {
  this: Component =>

  object size {
    lazy val width: SizeProperty = new SizeProperty(element.style.width, element.style.width_=)
    lazy val height: SizeProperty = new SizeProperty(element.style.height, element.style.height_=)

    lazy val center: Val[Double] = Val(width / 2.0)
    lazy val middle: Val[Double] = Val(height / 2.0)
  }
}
