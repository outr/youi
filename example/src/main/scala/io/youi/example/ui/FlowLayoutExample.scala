package io.youi.example.ui

import rapid.Task
import io.youi._
import io.youi.component.Container
import io.youi.example.screen.UIExampleScreen
import io.youi.layout.{FlowLayout, LayoutSupport}
import spice.net._

class FlowLayoutExample extends UIExampleScreen {
  override def title: String = "Flow Layout"
  override def path: URLPath = path"/examples/flow.html"

  private val colors = List(
    Color.Red, Color.Green, Color.Blue, Color.Orange, Color.Purple,
    Color.Pink, Color.Fuchsia, Color.Yellow, Color.LightBlue, Color.Black,
    Color.DarkRed, Color.DarkGreen, Color.DarkBlue, Color.Coral, Color.Teal
  )

  override def createUI(): Task[Unit] = Task {
    val flow = new Container with LayoutSupport {
      layout @= Some(FlowLayout(horizontalSpacing = 8.0, verticalSpacing = 8.0))
      background @= Color.AliceBlue
      size.width @= 450.0
      position.center := container.size.center
      position.top @= 20.0

      colors.zipWithIndex.foreach { case (boxColor, i) =>
        val w = 60.0 + (i % 4) * 20.0
        val box = new Container {
          background @= boxColor
          size.width @= w
          size.height @= 60.0
        }
        children += box
      }
    }
    container.children += flow
  }
}
