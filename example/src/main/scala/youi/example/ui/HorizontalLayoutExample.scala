package youi.example.ui

import rapid.Task
import youi.*
import youi.component.Container
import youi.event.EventSupport
import youi.example.ExampleApp
import youi.example.screen.UIExampleScreen
import youi.layout.{HorizontalLayout, LayoutSupport}
import spice.net.*

class HorizontalLayoutExample extends UIExampleScreen {
  override def title: String = "Horizontal Layout"
  override def path: URLPath = path"/examples/horizontal.html"

  private val colors = List(Color.Red, Color.Green, Color.Blue, Color.Orange, Color.Purple)
  private var index = 0

  private lazy val row = new Container with LayoutSupport {
    layout @= Some(HorizontalLayout(10.0))
    backgroundColor := ExampleApp.subtleBg
    position.center := container.size.center
    position.middle @= container.size.middle

    colors.foreach { _ =>
      val box = createBox()
      children += box
    }
  }

  override def createUI(): Task[Unit] = Task {
    container.children += row
  }

  private def createBox(): Container = new Container with EventSupport {
    background @= colors(index)
    size.width @= 100.0
    size.height @= 100.0

    index += 1
    if (index >= colors.length) {
      index = 0
    }

    event.click.on {
      val box = createBox()
      row.children += box
    }
  }
}
