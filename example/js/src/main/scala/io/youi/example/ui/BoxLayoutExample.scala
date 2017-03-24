package io.youi.example.ui

import io.youi._
import io.youi.hypertext.border.BorderStyle
import io.youi.hypertext.style.Overflow
import io.youi.hypertext.{Button, Component, Container}
import io.youi.layout.VerticalBoxLayout
import reactify._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object BoxLayoutExample extends UIExampleScreen {
  override def name: String = "BoxLayout Example"

  override protected def load(): Future[Unit] = super.load().map { _ =>
    val layoutContainer = new Container {
      id := "main"

      layoutManager := Some(new VerticalBoxLayout(spacing = 10.0, fillWidth = true))
      backgroundColor := Color.Black
      size.width := 750.0
      size.height := 500.0
      position.left := 100.0
      position.top := 100.0
      overflow.x := Overflow.Hidden
      overflow.y := Overflow.Auto

      val box1: Component = createBox("box1", Color.Red)
      val box2: Component = createBox("box2", Color.Green)
      val box3: Component = createBox("box3", Color.Blue)
      val box4: Component = createBox("box4", Color.Magenta)
      val box5: Component = createBox("box5", Color.Cyan)

      children += box1
      children += box2
      children += box3
      children += box4
      children += box5
    }
    container.children += layoutContainer

    container.children += new Button {
      text := "Jump to Middle"

      event.click.attach { _ =>
        layoutContainer.scrollbar.vertical.percentage := 0.5
      }
    }
  }

  private def createBox(name: String, c: Color): Component = new Container {
    id := name
    size.width := 500.0
    size.height := 250.0
    backgroundColor := c
    border.color := Some(Color.DeepPink)
    border.size := Some(1.0)
    border.style := Some(BorderStyle.Solid)
    border.radius := 5.0
  }

  override def path: String = "/examples/boxlayout.html"
}