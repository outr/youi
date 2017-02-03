package io.youi.example.screen

import io.youi.dom
import io.youi.example.ClientExampleApplication
import org.scalajs.dom._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object LoginScreen extends ExampleScreen {
  override protected def contentSelector: String = "#loginScreen"
  override def path: String = "/login.html"

  def message: html.Div = dom.byId[html.Div]("message")
  def username: html.Input = dom.byId[html.Input]("username")
  def password: html.Input = dom.byId[html.Input]("password")

  override protected def load(): Future[Unit] = super.load().map { _ =>
    content.addEventListener("submit", (evt: Event) => {
      evt.stopPropagation()
      evt.preventDefault()

      ClientExampleApplication.comm().logIn(username.value, password.value).onComplete {
        case Success(error) => {
          message.innerHTML = error.getOrElse("")
          if (error.isEmpty) {
            ClientExampleApplication.activate(CommunicationScreen)
          }
        }
        case Failure(exception) => println(s"Failed to log in with exception: ${exception.getMessage}")
      }
    })
  }
}