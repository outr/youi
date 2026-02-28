package youi.example.ui

import rapid.Task
import youi._
import youi.component.{Container, Selection, TextView}
import youi.component.support.BorderSupport
import youi.component.types.{Border, BorderStyle}
import youi.event.EventSupport
import youi.example.ExampleApp
import youi.example.screen.UIExampleScreen
import youi.layout.{GridLayout, LayoutSupport}
import spice.net._

class SelectionExample extends UIExampleScreen {
  override def title: String = "Selection"
  override def path: URLPath = path"/examples/selection.html"

  private val colors = List(
    Color.Red, Color.Green, Color.Blue,
    Color.Orange, Color.Purple, Color.Pink,
    Color.Teal, Color.Coral, Color.SteelBlue
  )

  override def createUI(): Task[Unit] = Task {
    val selection = new Selection[Container]

    val status = new TextView {
      content @= "Click to select, Ctrl+click to toggle, Shift+click for range"
      font.size @= 16.0
      color := ExampleApp.textColor
      position.center := container.size.center
      position.top @= 10.0
    }

    val grid = new Container with LayoutSupport {
      layout @= Some(GridLayout(columns = 3, horizontalSpacing = 10.0, verticalSpacing = 10.0))
      size.width @= 340.0
      position.center := container.size.center
      position.top @= 50.0
    }

    // Create boxes first
    val boxes: List[Container & EventSupport & BorderSupport] = colors.map { boxColor =>
      val box = new Container with EventSupport with BorderSupport {
        background @= boxColor
        size.height @= 100.0
        border.radius @= 4.0
      }
      grid.children += box
      box
    }

    // Wire up click handlers after boxes list is complete
    var lastClicked: Option[Container] = None
    boxes.foreach { box =>
      box.event.click.attach { evt =>
        if (evt.shiftPressed) {
          lastClicked.foreach { from =>
            selection.range(from, box, boxes)
          }
        } else if (evt.controlPressed || evt.metaPressed) {
          selection.toggle(box)
        } else {
          selection.set(box)
        }
        lastClicked = Some(box)
      }
    }

    selection.changed.attach { selected =>
      boxes.foreach { box =>
        if (selected.contains(box)) {
          box.border @= Border(3.0, BorderStyle.Solid, Color.White)
        } else {
          box.border @= Border.none
        }
      }
      val count = selected.size
      status.content @= (if (count == 0) "Click to select, Ctrl+click to toggle, Shift+click for range"
                          else s"$count item${if (count != 1) "s" else ""} selected")
    }

    container.children ++= Seq(status, grid)
  }
}
