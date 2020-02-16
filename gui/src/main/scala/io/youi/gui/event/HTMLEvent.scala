package io.youi.gui.event

import org.scalajs.{dom => jsdom}

import scala.scalajs.js

abstract class HTMLEvent(val underlying: jsdom.UIEvent) {
  def stopPropagation(): Unit = underlying.stopPropagation()

  def stopImmediatePropagation(): Unit = underlying.stopImmediatePropagation()

  def preventDefault(): Unit = underlying.preventDefault()
}

object HTMLEvent {
  lazy val hasPointerSupport: Boolean = js.typeOf(js.Dynamic.global.PointerEvent) != "undefined"
  lazy val hasTouchSupport: Boolean = js.typeOf(jsdom.document.documentElement.asInstanceOf[js.Dynamic].ontouchstart) != "undefined"
}