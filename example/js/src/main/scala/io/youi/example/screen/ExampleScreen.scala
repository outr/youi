package io.youi.example.screen

import io.youi.app.screen.{ContentScreen, PathActivation}
import io.youi.dom
import io.youi.example.{ClientExampleApplication, ExampleConnection}
import org.scalajs.dom._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait ExampleScreen extends ContentScreen with PathActivation {
  def connection: ExampleConnection = ClientExampleApplication.connection
  def heading: Option[html.Element] = dom.getById[html.Element]("heading")

  override protected def activate(): Future[Unit] = super.activate().map { _ =>
    heading.foreach(_.innerHTML = title)
  }
}