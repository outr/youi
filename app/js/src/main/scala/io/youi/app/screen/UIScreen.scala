//package io.youi.app.screen
//
//import io.youi.component.Container
//import io.youi.drawable.Drawable
//import io.youi.ui
//
//import scala.concurrent.ExecutionContext.Implicits.global
//import scala.concurrent.Future
//
//trait UIScreen extends DrawableScreen {
//  protected lazy val container: Container = new Container
//
//  override protected def drawable: Future[Drawable] = createUI().map { _ =>
//    container.size.width := ui.width
//    container.size.height := ui.height
//    container
//  }
//
//  def createUI(): Future[Unit]
//
//  override protected def activate(): Future[Unit] = super.activate().map(_ => container.visible := true)
//
//  override protected def deactivate(): Future[Unit] = super.deactivate().map(_ => container.visible := false)
//}