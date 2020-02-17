package io.youi.gui.event

import org.scalajs.{dom => jsdom}

abstract class HTMLEvent(val underlying: jsdom.UIEvent) {
  val time: Long = System.currentTimeMillis()

  def stopPropagation(): Unit = underlying.stopPropagation()

  def stopImmediatePropagation(): Unit = underlying.stopImmediatePropagation()

  def preventDefault(): Unit = underlying.preventDefault()
}