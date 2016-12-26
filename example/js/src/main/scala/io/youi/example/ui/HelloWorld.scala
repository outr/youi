package io.youi.example.ui

import com.outr.props._
import com.outr.scribe.Logging
import io.youi.UI
import io.youi.html.{Button, Component}

object HelloWorld extends UI with Logging {
  title := "Hello World"

  children += new Button {
    text := "Say 'Hello World'"
    position.center := ui.position.center
    position.middle := ui.position.middle

    action.attach { evt =>
      logger.info("Hello, World!")
    }
  }
}
