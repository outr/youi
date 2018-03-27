package io.youi.example.screen

import io.youi.app.screen.PathActivation
import io.youi.dom
import org.scalajs.dom.{Event, document, html}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.language.implicitConversions

object ExampleBootstrapScreen extends PathActivation {
  override def path: String = "/bootstrap.html"

  implicit def bc2E[E <: html.Element, T <: BootstrapComponent[E, T]](bc: BootstrapComponent[E, T]): E = bc.element

  override protected def init(): Future[Unit] = super.init().map { _ =>
    document.body.appendChild(bootstrap.button.content("Primary"))
  }
}

object bootstrap {
  def button: Button = {
    val b = dom.create[html.Button]("button")
    b.classList.add("btn")
    b.classList.add("btn-primary")
    new Button(b)
  }
}

abstract class BootstrapComponent[E <: html.Element, T <: BootstrapComponent[E, T]](val element: E) {
  def content(value: String): T = {
    element.innerHTML = value
    this.asInstanceOf[T]
  }
}

class Button(button: html.Button) extends BootstrapComponent[html.Button, Button](button) {
}