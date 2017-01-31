package io.youi.example

import org.scalajs.dom._
import scala.concurrent.Future

trait ClientExampleCommunication extends ExampleCommunication {
  override def url: Future[String] = Future.successful(window.location.href)

  override def navigateTo(url: String, push: Boolean): Future[Unit] = Future.successful(if (push) {
    window.history.pushState(url, url, url)
  } else {
    window.location.href = url
  })
}