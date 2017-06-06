package io.youi.example.ui

import io.youi._
import io.youi.app.screen.UIScreen
import io.youi.component._
import io.youi.component.draw._
import io.youi.component.draw.path._

object DrawableExample extends UIExampleScreen with UIScreen {
  override def name: String = "Drawable Example"
  override def path: String = "/examples/drawable.html"

  override def createUI(): Unit = {
    val component = new DrawableComponent {
      position.x := 10.0
      position.y := 10.0
      size.width := 400.0
      size.height := 400.0
    }

    component.drawable := Group(
      Path(
        MoveTo(50.0, 50.0),
        LineTo(150.0, 50.0),
        LineTo(150.0, 150.0),
        LineTo(50.0, 150.0),
        LineTo(50.0, 50.0)
      ),
      Fill(Color.Red.copy(alpha = 0.5)),
      Path(
        MoveTo(100.0, 100.0),
        LineTo(200.0, 100.0),
        LineTo(200.0, 200.0),
        LineTo(100.0, 200.0),
        LineTo(100.0, 100.0)
      ),
      Fill(Color.Green.copy(alpha = 0.5)),
      Path(
        MoveTo(150.0, 150.0),
        LineTo(250.0, 150.0),
        LineTo(250.0, 250.0),
        LineTo(150.0, 250.0),
        LineTo(150.0, 150.0)
      ),
      Fill(Color.Blue.copy(alpha = 0.5))
    )

    container.children += component
  }
}
