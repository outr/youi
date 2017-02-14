package io.youi.example.ui

import com.outr.reactify._
import io.youi.UI
import io.youi.hypertext.Button

object HelloWorld {
  import UI._

  children += new Button {
    text := "Say 'Hello World'"
    position.center := UI.position.center
    position.middle := UI.position.middle

    click.attach { evt =>
      scribe.info("Hello, World!")
    }
  }
}
