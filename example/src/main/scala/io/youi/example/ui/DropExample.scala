package io.youi.example.ui

import rapid.Task
import io.youi._
import io.youi.component._
import io.youi.component.support.PaddingSupport
import io.youi.component.types.{DropType, PositionType}
import io.youi.event.EventSupport
import io.youi.example.screen.UIExampleScreen
import io.youi.font.GoogleFont
import spice.net._
import reactify._

class DropExample extends UIExampleScreen {
  override def title: String = "Drop Example"
  override def path: URLPath = path"/examples/drop.html"

  private val text = new TextView() with EventSupport {
    content @= "Show Dropdown"
    font.size @= 64.px
    color @= Color.DarkBlue
    position.`type` @= PositionType.Absolute
    position.center := ui.size.center
    position.middle := ui.size.middle
  }

  override def createUI(): Task[Unit] = for {
    fnt <- GoogleFont.`Lobster`.load()
  } yield {
    text.font.family @= fnt.family
    container.children += text

    val dropdown = new Drop with EventSupport with PaddingSupport
    dropdown.backgroundColor @= Color.LightCoral
    dropdown.border.radius @= 5.0
    dropdown.container.padding @= 10.px
    dropdown.container.children += new TextView {
      content @= "This is dropdown text!<br/>One<br/>Two<br/>Three"
    }
    container.children += dropdown

    text.event.click.attach { _ =>
      dropdown.toggle(text, DropType.Up).startUnit()
    }
    dropdown.event.click.attach { evt =>
      evt.preventDefault()
      evt.stopPropagation()
      dropdown.container.children += new TextView {
        content @= "This is dropdown text!<br/>One<br/>Two<br/>Three"
      }
    }
  }
}