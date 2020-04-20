package io.youi

import org.scalajs.dom.html

import scala.scalajs.js

@js.native
trait ResizeObserverEntry extends js.Object {
  def contentRect: DOMRectReadOnly = js.native
  def target: html.Element = js.native
}
