package youi.example.ui

import rapid.Task
import youi._
import youi.component.Container
import youi.example.ExampleApp
import youi.example.screen.UIExampleScreen
import youi.layout.{LayoutSupport, VerticalLayout}
import spice.net._

class VerticalLayoutExample extends UIExampleScreen {
  override def title: String = "Vertical Layout"
  override def path: URLPath = path"/examples/vertical.html"

  private val colors = List(Color.Black, Color.Red, Color.Green, Color.Blue, Color.Orange)

  override def createUI(): Task[Unit] = Task {
    val boxes = new Container with LayoutSupport {
      layout @= Some(VerticalLayout(10.0))
      backgroundColor := ExampleApp.subtleBg
      size.width @= 120.0
      position.center := container.size.center
      position.top @= 20.0

      colors.foreach { boxColor =>
        val box = new Container {
          background @= boxColor
          size.width @= 100.0
          size.height @= 100.0
          position.left @= 10.0
        }
        children += box
      }
    }
    container.children += boxes
  }
}
