package io.youi.example.ui

import io.youi.Color
import io.youi.example.screen.UIExampleScreen
import io.youi.font.{Material, MaterialIconView}
import io.youi.net._
import io.youi.style.FontWeight

import scala.concurrent.Future
import scribe.Execution.global

class MaterialIconsExample extends UIExampleScreen {
  override def title: String = "Material Icons Example"

  override def path: Path = path"/examples/material-icons.html"

  override def createUI(): Future[Unit] = for {
    material <- Material.load()
  } yield {
    val iconView = new MaterialIconView {
      value := Material.Icons.Action.Alarm
      font := material
      font.weight := FontWeight("bold")
      font.size := 128.0
      color := Color.Blue
      position.center := container.size.center
      position.middle := container.size.middle
    }
    container.children += iconView
  }
}