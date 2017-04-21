package io.youi.example.ui

import io.youi.app.screen.UIScreen
import io.youi.component.Graphics

object GraphicsExample extends UIExampleScreen with UIScreen {
  override def name: String = "Graphics Example"
  override def path: String = "/examples/graphics.html"

  override def createUI(): Unit = {
    container.children += new Graphics {
      // set a fill and line style
      drawable.beginFill(0xFF3300)
      drawable.lineStyle(4.0, 0xffd900, 1.0)

      // draw a shape
      drawable.moveTo(50.0, 50.0)
      drawable.lineTo(250.0, 50.0)
      drawable.lineTo(100.0, 100.0)
      drawable.lineTo(50.0, 50.0)
      drawable.endFill()

      // set a fill and a line style again and draw a rectangle
      drawable.lineStyle(2.0, 0x0000FF, 1.0)
      drawable.beginFill(0xFF700B, 1.0)
      drawable.drawRect(50.0, 250.0, 120.0, 120.0)

      // draw a rounded rectangle
      drawable.lineStyle(2.0, 0xFF00FF, 1.0)
      drawable.beginFill(0xFF00BB, 0.25)
      drawable.drawRoundedRect(150.0, 450.0, 300.0, 100.0, 15.0)
      drawable.endFill()

      // draw a circle, set the lineStyle to zero so the circle doesn't have an outline
      drawable.lineStyle(0.0)
      drawable.beginFill(0xFFFF0B, 0.5)
      drawable.drawCircle(470.0, 90.0, 60.0)
      drawable.endFill()
    }
  }
}
