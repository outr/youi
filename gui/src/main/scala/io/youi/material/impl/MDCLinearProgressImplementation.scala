package io.youi.material.impl

import scala.scalajs.js

@js.native
trait MDCLinearProgressImplementation extends js.Object {
  def `foundation_`: MDCLinearProgressImplementationFoundation
}

@js.native
trait MDCLinearProgressImplementationFoundation extends js.Object {
  def setDeterminate(value: Boolean): Unit
  def setProgress(value: Double): Unit
  def setBuffer(value: Double): Unit
  def setReverse(value: Boolean): Unit
  def open(): Unit
  def close(): Unit
}