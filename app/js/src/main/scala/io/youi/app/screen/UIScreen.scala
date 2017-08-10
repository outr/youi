//package io.youi.app.screen
//
//import io.youi._
//import io.youi.component.{Container, Renderer}
//import io.youi.hypertext.Canvas
//
//import scala.concurrent.Future
//import scala.concurrent.ExecutionContext.Implicits.global
//
//trait UIScreen extends Screen {
//  def renderer: Renderer = UIScreen.renderer
//
//  lazy val container: Container = new Container
//
//  override protected def init(): Future[Unit] = super.init().map { _ =>
//    container.size.width := renderer.size.width
//    container.size.height := renderer.size.height
//    container.visible := false
//    renderer.children += container
//  }
//
//  override protected def load(): Future[Unit] = super.load().map(_ => createUI())
//
//  def createUI(): Unit
//
//  override protected def activate(): Future[Unit] = super.activate().map { _ =>
//    UIScreen.canvas.visible := true
//    container.visible := true
//  }
//
//  override protected def deactivate(): Future[Unit] = super.deactivate().map { _ =>
//    container.visible := false
//    UIScreen.canvas.visible := false
//  }
//}
//
//object UIScreen {
//  lazy val canvas = new Canvas {
//    ui.children += this
//  }
//
//  lazy val renderer: Renderer = {
//    val r = Renderer(canvas)
//    r.size.width := ui.size.width
//    r.size.height := ui.size.height
//    r
//  }
//}