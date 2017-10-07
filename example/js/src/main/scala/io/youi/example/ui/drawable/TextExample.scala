package io.youi.example.ui.drawable

import io.youi.app.screen.UIScreen
import io.youi.drawable.Group
import io.youi.example.ui.UIExampleScreen
import io.youi.font.{GoogleFont, OpenTypeFont}

object TextExample extends UIExampleScreen with UIScreen {
  override def name: String = "Text Example"

  override protected val drawable = OpenTypeFont.fromURL(GoogleFont.`Open Sans`.`regular`).map { font =>
    Group(
      font("Testing", 96.0)
    )
  }

  override def path: String = "/examples/drawable/text.html"
}