package io.youi

import org.scalajs.dom.html

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal("ResizeObserver")
class ResizeObserver(callback: js.Function1[js.Array[ResizeObserverEntry], Unit]) extends js.Object {
  def observe(target: html.Element): Unit = js.native
  def unobserve(target: html.Element): Unit = js.native
  def disconnect(): Unit = js.native
}

@js.native
trait ResizeObserverEntry extends js.Object {
  def contentRect: DOMRectReadOnly = js.native
  def target: html.Element = js.native
}

@js.native
trait DOMRectReadOnly extends js.Object {
  def x: Double
  def y: Double
  def width: Double
  def height: Double
  def top: Double
  def right: Double
  def bottom: Double
  def left: Double
}