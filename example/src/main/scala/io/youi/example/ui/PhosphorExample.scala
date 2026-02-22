package io.youi.example.ui

import rapid.Task
import io.youi._
import io.youi.component.PhosphorView
import io.youi.component.support.{MeasuredSupport, PositionSupport}
import io.youi.example.screen.UIExampleScreen
import io.youi.font.Phosphor
import spice.net._

class PhosphorExample extends UIExampleScreen {
  override def title: String = "Icon Example"
  override def path: URLPath = path"/examples/phosphor.html"

  override def createUI(): Task[Unit] = Phosphor.load().map { _ =>
    val iconView = new PhosphorView with PositionSupport with MeasuredSupport {
      icon @= Phosphor.AndroidLogo
      font.size @= 128.0
      color @= Color.Blue
      position.center := container.size.center
      position.middle := container.size.middle
    }
    container.children += iconView
  }
}