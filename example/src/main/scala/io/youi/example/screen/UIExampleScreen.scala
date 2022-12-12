package io.youi.example.screen

import cats.effect.IO
import io.youi.app.screen.UIScreen
import io.youi.component.types.Display
import io.youi.example.ui.component.Header
import io.youi.ui
import spice.net._

trait UIExampleScreen extends UIScreen {
  protected val header: Header = new Header
  def url: URL = URL.parse(s"https://github.com/outr/youi/tree/master/example/js/src/main/scala/io/youi/example/ui/${getClass.getSimpleName}.scala")

  override protected def init(): IO[Unit] = {
    ui.children += header
    super.init().map { _ =>
      container.size.height := ui.size.height - header.size.height
    }
  }

  override protected def activate(): IO[Unit] = super.activate().map(_ => header.display @= Display.Block)

  override protected def deactivate(): IO[Unit] = super.deactivate().map(_ => header.display @= Display.None)
}