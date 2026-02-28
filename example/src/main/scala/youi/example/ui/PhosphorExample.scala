package youi.example.ui

import rapid.Task
import youi._
import youi.component.PhosphorView
import youi.example.ExampleApp
import youi.example.screen.UIExampleScreen
import youi.font.Phosphor
import spice.net._

class PhosphorExample extends UIExampleScreen {
  override def title: String = "Icon Example"
  override def path: URLPath = path"/examples/phosphor.html"

  override def createUI(): Task[Unit] = Phosphor.load().map { _ =>
    val iconView = new PhosphorView {
      icon @= Phosphor.AndroidLogo
      font.size @= 128.0
      color := ExampleApp.accentColor
      position.center := container.size.center
      position.middle := container.size.middle
    }
    container.children += iconView
  }
}