package io.youi.component.bootstrap

import org.scalajs.dom.html

import scala.scalajs.js

trait Modal {
  protected val element: html.Element

  protected def call(content: String): Unit = {
    js.eval(s"$$('#${element.getAttribute("id")}').modal($content)")
    ()
  }

  def initialize(backdrop: String = "true",
                 keyboard: Boolean = true,
                 focus: Boolean = true,
                 show: Boolean = true): Unit = {
    call(s"{backdrop: $backdrop, keyboard: $keyboard, focus: $focus, show: $show}")
  }
  def show(): Unit = call("'show'")
  def hide(): Unit = call("'hide'")
  def toggle(): Unit = call("'toggle'")
  def handleUpdate(): Unit = call("'handleUpdate'")
  def dispose(): Unit = call("'dispose'")
}