package io.youi.example.ui

import rapid.Task
import io.youi._
import io.youi.component.MaterialIconView
import io.youi.example.ExampleApp
import io.youi.example.screen.UIExampleScreen
import io.youi.material.{Material, MaterialComponents}
import spice.net._

class MaterialIconsExample extends UIExampleScreen {
  override def title: String = "Material Icons Example"
  override def path: URLPath = path"/examples/material-icons.html"

  override def createUI(): Task[Unit] = MaterialComponents.waitForLoaded().map { _ =>
    val iconView = new MaterialIconView {
      icon @= Material.Icons.Action.Alarm
      font.size @= 128.0
      color := ExampleApp.accentColor
      position.center := container.size.center
      position.middle := container.size.middle
    }
    container.children += iconView
  }
}
