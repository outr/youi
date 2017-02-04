package io.youi.example.screen

import io.youi.app.screen.{ContentScreen, PathActivation}
import io.youi.dom
import io.youi.example.{ClientExampleApplication, ExampleCommunication}
import io.youi.example.ClientExampleApplication.comm
import org.scalajs.dom._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait ExampleScreen extends ContentScreen[html.Element] with PathActivation {
  override protected def containerSelector: String = "#content"
  override protected def contentPath: String = s"/template$path"

  protected def c: ExampleCommunication = comm(ClientExampleApplication.connection)

  def heading: html.Element = dom.byId[html.Element]("heading")

  override protected def activate(): Future[Unit] = super.activate().map { _ =>
    val name = getClass.getSimpleName
    document.title = name
    heading.innerHTML = name
  }
}