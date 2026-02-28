package youi

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