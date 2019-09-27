package io.youi.example.screen

import io.youi.dom
import io.youi.example.ClientExampleApplication
import io.youi.net._
import org.scalajs.dom._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object LoginScreen extends ExampleScreen {
  override def path: Path = path"/login.html"

  def form: html.Form = dom.byId[html.Form]("loginScreen")
  def message: html.Div = dom.byId[html.Div]("message")
  def username: html.Input = dom.byId[html.Input]("username")
  def password: html.Input = dom.byId[html.Input]("password")

  override protected def load(): Future[Unit] = super.load().flatMap { _ =>
    content.addEventListener("submit", (evt: Event) => {
      evt.stopPropagation()
      evt.preventDefault()

      hookup.communication.logIn(username.value, password.value).onComplete {
        case Success(error) => {
          message.innerHTML = error.getOrElse("")
          if (error.isEmpty) {
            ClientExampleApplication.active @= CommunicationScreen
          }
        }
        case Failure(exception) => scribe.warn(s"Failed to log in with exception: ${exception.getMessage}")
      }
    })
    ClientExampleApplication.login.preload()
  }
}