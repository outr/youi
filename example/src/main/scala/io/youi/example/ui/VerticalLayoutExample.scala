package io.youi.example.ui

import rapid.Task
import io.youi._
import io.youi.component.Container
import io.youi.component.support.{PositionSupport, SizeSupport}
import io.youi.example.screen.UIExampleScreen
import spice.net._

class VerticalLayoutExample extends UIExampleScreen {
  override def title: String = "Vertical Layout"
  override def path: URLPath = path"/examples/vertical.html"

  private val boxSize    = 100.0
  private val spacing    = 10.0
  private val colors     = List(Color.Black, Color.Red, Color.Green, Color.Blue, Color.Orange)

  override def createUI(): Task[Unit] = Task {
    val totalHeight = colors.length * boxSize + (colors.length - 1) * spacing

    val boxes = new Container with SizeSupport with PositionSupport {
      background @= Color.LightBlue
      size.width  @= 120.0
      size.height @= totalHeight
      position.center := container.size.center
      position.middle := container.size.middle

      colors.zipWithIndex.foreach { case (boxColor, i) =>
        val box = new Container with SizeSupport with PositionSupport {
          background @= boxColor
          size.width  @= 100.0
          size.height @= boxSize
          position.left @= 10.0
          position.top  @= i * (boxSize + spacing)
        }
        children += box
      }
    }
    container.children += boxes
  }
}
