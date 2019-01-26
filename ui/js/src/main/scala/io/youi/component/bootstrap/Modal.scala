package io.youi.component.bootstrap

import org.scalajs.dom.html

import scala.scalajs.js

trait Modal {
  protected val div: html.Div

  protected def call(content: String): Unit = {
    js.eval(s"$$('#${div.getAttribute("id")}').modal($content)")
    ()
  }

  def show(): Unit = call("'show'")
  def hide(): Unit = call("'hide'")
  def toggle(): Unit = call("'toggle'")
  def handleUpdate(): Unit = call("'handleUpdate'")
  def dispose(): Unit = call("'dispose'")
}