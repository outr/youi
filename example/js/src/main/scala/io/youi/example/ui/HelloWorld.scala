package io.youi.example.ui

import com.outr.reactify._
import scribe._
import io.youi.UI
import io.youi.hypertext.Button

import scala.scalajs.js.annotation.JSExportTopLevel

object HelloWorld {
  import UI._

  children += new Button {
    text := "Say 'Hello World'"
    position.center := UI.position.center
    position.middle := UI.position.middle

    click.attach { evt =>
      logger.info("Hello, World!")
    }
  }
}
