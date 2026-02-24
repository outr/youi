package io.youi.material.impl

import scala.scalajs.js

@js.native
trait MDCSliderImplementation extends js.Object {
  def getValue(): Double = js.native
  def setValue(value: Double): Unit = js.native
  var disabled: Boolean = js.native
  def layout(): Unit = js.native
}
