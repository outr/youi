package io.youi.ui.event

import org.scalajs.{dom => jsdom}
import scala.scalajs.js

@js.native
trait HTMLPointerEvent extends jsdom.MouseEvent {
  def pointerId: Int = js.native
  def width: Int = js.native
  def height: Int = js.native
  def pressure: Double = js.native
  def tangentialPressure: Double = js.native
  def tiltX: Double = js.native
  def tiltY: Double = js.native
  def twist: Double = js.native
  def pointerType: String = js.native
  def isPrimary: Boolean = js.native
}