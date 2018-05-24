package io.youi.example.screen

import io.youi.app.screen.UIScreen
//import io.youi.example.ui.component.Header
//import io.youi.ui

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait UIExampleScreen extends UIScreen {
//  protected def header: Header = UIExampleScreen.header

  override protected def init(): Future[Unit] = super.init().map { _ =>
//    container.position.top := header.position.bottom
//    container.size.height := ui.size.height - header.size.height
  }
}

object UIExampleScreen {
//  lazy val header: Header = {
//    val h = new Header
//    ui.children += h
//    h
//  }
}