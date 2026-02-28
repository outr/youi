package youi.example.ui

import rapid.Task
import youi._
import youi.component._
import youi.component.types.PositionType
import youi.easing.Linear
import youi.event.EventSupport
import youi.example.ExampleApp
import youi.example.screen.UIExampleScreen
import youi.font.GoogleFont
import spice.net._
import reactify._

class PopupExample extends UIExampleScreen {
  override def title: String = "Popup Example"
  override def path: URLPath = path"/examples/popup.html"

  private val text = new TextView() with EventSupport {
    content @= "Show Popup"
    font.size @= 64.px
    color := ExampleApp.textColor
    position.`type` @= PositionType.Absolute
    position.center := ui.size.center
    position.middle := ui.size.middle
  }

  override def createUI(): Task[Unit] = for {
    fnt <- GoogleFont.`Lobster`.load()
  } yield {
    text.font.family @= fnt.family
    container.children += text

    val popup = new Popup(showGlassPane = true) with EventSupport
    popup.backgroundColor @= Color.Green.withBrightness(0.5)
    popup.init()
    popup.easing @= Linear
    container.children += popup

    text.event.click.on {
      popup.show().startUnit()
    }
  }
}