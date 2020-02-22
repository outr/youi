package io.youi.example.screen

import io.youi.app.screen.UIScreen
import io.youi.example.ui.component.Header
import io.youi.net.URL
import io.youi.component

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait UIExampleScreen extends UIScreen {
  protected def header: Header = UIExampleScreen.header
  def url: URL = URL(s"https://github.com/outr/youi/tree/master/example/js/src/main/scala/io/youi/example/ui/${getClass.getSimpleName}.scala")

  override protected def init(): Future[Unit] = super.init().map { _ =>
    container.position.top := header.position.bottom
    container.size.height := component.size.height - header.size.height
  }
}

object UIExampleScreen {
  lazy val header: Header = {
    val h = new Header
    component.children += h
    h
  }
}