package io.youi.example.ui

import rapid.Task
import io.youi._
import io.youi.component.support.{BorderSupport, MeasuredSupport, PaddingSupport, PositionSupport}
import io.youi.component.types.{BoxShadow, Cursor, PositionType}
import io.youi.component.{Popup, TextView}
import io.youi.event.EventSupport
import io.youi.example.screen.UIExampleScreen
import io.youi.font.GoogleFont
import spice.net._

class ModalExample extends UIExampleScreen {
  override def title: String = "Modal Example"
  override def path: URLPath = path"/examples/modal.html"

  override def createUI(): Task[Unit] = GoogleFont.`Open Sans`.`regular`.load().map { fnt =>
    val popup = new Popup(showGlassPane = true) with BorderSupport {
      override def preferredWidth: Double  = 600.0
      override def preferredHeight: Double = 400.0
    }
    popup.backgroundColor @= Color.White
    popup.border.radius @= 12.0
    popup.boxShadow @= BoxShadow(0.0, 4.0, 32.0, 0.0, Color.Black.withAlpha(0.25))
    popup.init()

    val popupText = new TextView with PositionSupport with MeasuredSupport {
      content @= "This is a modal!"
      font.weight @= fnt
      font.size   @= 48.0
      color       @= Color.DarkSlateBlue
      position.`type` @= PositionType.Absolute
      position.center := popup.size.center
      position.middle := popup.size.middle
    }
    popup.children += popupText

    val button = new TextView with PositionSupport with MeasuredSupport with EventSupport with BorderSupport with PaddingSupport {
      content @= "Open Modal"
      font.weight @= fnt
      font.size   @= 48.0
      color       @= Color.White
      backgroundColor @= Color.RoyalBlue
      border.radius @= 8.0
      padding.top    @= 12.0
      padding.bottom @= 12.0
      padding.left   @= 24.0
      padding.right  @= 24.0
      cursor @= Cursor.Pointer
      position.`type` @= PositionType.Absolute
      position.center := container.size.center
      position.middle := container.size.middle
    }
    button.event.click.on {
      popup.show().startUnit()
    }

    container.children ++= Seq(button, popup)
  }
}
