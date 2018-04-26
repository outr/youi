package io.youi.example.screen

import io.youi.app.screen.UIScreen
import io.youi.example.ui.component.Header

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait UIExampleScreen extends UIScreen {
  protected lazy val header: Header = new Header

  override protected def init(): Future[Unit] = super.init().map { _ =>
    container.children += header
  }
}
