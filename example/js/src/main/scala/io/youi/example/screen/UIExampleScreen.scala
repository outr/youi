package io.youi.example.screen

import io.youi.app.screen.UIScreen
import io.youi.component.types.Display
import io.youi.example.ui.component.Header
import io.youi.net.URL
import io.youi.ui

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait UIExampleScreen extends UIScreen {
  protected val header: Header = new Header
  def url: URL = URL(s"https://github.com/outr/youi/tree/master/example/js/src/main/scala/io/youi/example/ui/${getClass.getSimpleName}.scala")

  override protected def init(): Future[Unit] = {
    ui.children += header
    super.init().map { _ =>
      container.size.height := ui.size.height - header.size.height
    }
  }

  override protected def activate(): Future[Unit] = super.activate().map(_ => header.display @= Display.Block)

  override protected def deactivate(): Future[Unit] = super.deactivate().map(_ => header.display @= Display.None)
}