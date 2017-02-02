package io.youi.example

import io.youi.dom
import org.scalajs.dom._

import scala.concurrent.Future

import scala.concurrent.ExecutionContext.Implicits.global

trait ClientExampleCommunication extends ExampleCommunication {
  override def url: Future[String] = Future.successful(window.location.href)

  override def navigateTo(url: String, push: Boolean): Future[Unit] = Future.successful(if (push) {
    window.history.pushState(url, url, url)
  } else {
    window.location.href = url
  })

  override def show(message: String): Future[Unit] = Future(dom.byId[html.Div]("content").appendChild(dom.fromString[html.Element](s"<h2>$message</h2>").head))
}