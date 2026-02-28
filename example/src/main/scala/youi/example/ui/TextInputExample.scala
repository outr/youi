package youi.example.ui

import rapid.Task
import youi._
import youi.component.TextInput
import youi.example.screen.UIExampleScreen
import youi.font.GoogleFont
import spice.net._

class TextInputExample extends UIExampleScreen {
  override def title: String = "TextInput Example"
  override def path: URLPath = path"/examples/input.html"

  override def createUI(): Task[Unit] = for {
    pacifico  <- GoogleFont.`Pacifico`.`regular`.load()
    roboto    <- GoogleFont.`Roboto`.`900`.load()
    berkshire <- GoogleFont.`Berkshire Swash`.`regular`.load()
  } yield {
    val pacificoView = new TextInput {
      value @= "Pacifico Regular"
      font.weight @= pacifico
      font.size @= 64.0
      color @= Color.Red
      position.center := container.size.center
      position.middle := container.size.middle
    }
    val robotoView = new TextInput {
      value @= "Roboto 900"
      font.weight @= roboto
      color @= Color.Green
      font.size @= 64.0
      position.center := container.size.center
      position.bottom := pacificoView.position.top - 150.0
    }
    val berkshireView = new TextInput {
      value @= "Berkshire Swash Regular"
      font.weight @= berkshire
      color @= Color.Blue
      font.size @= 64.0
      size.width @= 900.0
      value.attach { v =>
        scribe.info(s"Value: $v")
      }
      position.center := container.size.center
      position.top := pacificoView.position.bottom + 150.0
    }

    container.children ++= Seq(pacificoView, robotoView, berkshireView)
  }
}
