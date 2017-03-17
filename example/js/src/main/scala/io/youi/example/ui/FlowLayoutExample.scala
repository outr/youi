package io.youi.example.ui

import io.youi._
import io.youi.hypertext.border.BorderStyle
import io.youi.hypertext.style.Overflow
import io.youi.hypertext.{Button, Component, Container}
import io.youi.layout.FlowLayout
import reactify._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object FlowLayoutExample extends UIExampleScreen {
  override def name: String = "FlowLayout Example"

  override protected def load(): Future[Unit] = super.load().map { _ =>
    val layoutContainer = new Container {
      id := "main"

      layoutManager := Some(new FlowLayout(xOffset = 5.0, yOffset = 5.0, verticalPadding = 5.0, horizontalPadding = 5.0))
      backgroundColor := Color.Black
      size.width := 500.0
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
      val box6: Component = createBox("box6", Color.SandyBrown)
      val box7: Component = createBox("box7", Color.Orange)
      val box8: Component = createBox("box8", Color.DarkSlateGray)

      children += box1
      children += box2
      children += box3
      children += box4
      children += box5
      children += box6
      children += box7
      children += box8
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
    size.width := 150.0
    size.height := 150.0
    backgroundColor := c
    border.color := Some(Color.DeepPink)
    border.width := Some(1.0)
    border.style := Some(BorderStyle.Solid)
    border.radius := 5.0
  }

  override def path: String = "/examples/flowlayout.html"
}