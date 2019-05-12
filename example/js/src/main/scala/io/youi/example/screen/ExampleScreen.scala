package io.youi.example.screen

import io.youi.app.screen.{ContentScreen, PathActivation}
import io.youi.dom
import io.youi.example.{ClientExampleApplication, ExampleCommunication, ExampleHookup, SimpleCommunication}
import io.youi.example.ClientExampleApplication._
import org.scalajs.dom._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait ExampleScreen extends ContentScreen with PathActivation {
  protected def hookup: ExampleHookup = ClientExampleApplication.hookup

  def heading: Option[html.Element] = dom.getById[html.Element]("heading")

  override protected def activate(): Future[Unit] = super.activate().map { _ =>
    val name = getClass.getSimpleName
    document.title = name
    heading.foreach(_.innerHTML = name)
  }
}