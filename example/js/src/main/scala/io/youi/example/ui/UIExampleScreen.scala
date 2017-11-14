//package io.youi.example.ui
//
//import io.youi.example.screen.ExampleScreen
//
//import scala.concurrent.{ExecutionContext, Future}
//import org.scalajs.dom._
//
//trait UIExampleScreen extends ExampleScreen {
//  def name: String
//
//  implicit def executionContext: ExecutionContext = scala.concurrent.ExecutionContext.Implicits.global
//
//  override protected def activate(): Future[Unit] = super.activate().map { _ =>
//    document.title = name
//  }
//}