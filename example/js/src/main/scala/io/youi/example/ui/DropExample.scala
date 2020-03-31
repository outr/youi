package io.youi.example.ui

import io.youi._
import io.youi.component._
import io.youi.component.support.{MeasuredSupport, PositionSupport}
import io.youi.component.types.{DropType, PositionType}
import io.youi.event.EventSupport
import io.youi.example.screen.UIExampleScreen
import io.youi.font.GoogleFont
import io.youi.net._
import reactify._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class DropExample extends UIExampleScreen {
  override def title: String = "Drop Example"
  override def path: Path = path"/examples/drop.html"

  private val text = new TextView() with PositionSupport with MeasuredSupport with EventSupport {
    content @= "Show Dropdown"
    font.size @= 64.px
    color @= Color.DarkBlue
    position.`type` @= PositionType.Absolute
    position.center := ui.size.center
    position.middle := ui.size.middle
  }

  override def createUI(): Future[Unit] = for {
    fnt <- GoogleFont.`Lobster`.load()
  } yield {
    text.font.family @= fnt.family
    container.children += text

    val dropdown = new Drop with EventSupport
    dropdown.backgroundColor @= Color.LightCoral
    dropdown.border.radius @= 5.0
    dropdown.container.children += new TextView {
      content @= "This is dropdown text!<br/>One<br/>Two<br/>Three"
    }
    container.children += dropdown

    text.event.click.attach { evt =>
      dropdown.toggle(text, DropType.Up)
    }
    dropdown.event.click.on {
      dropdown.hide()
    }
  }
}