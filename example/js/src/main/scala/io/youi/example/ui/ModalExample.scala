package io.youi.example.ui

import io.youi._
import io.youi.component.{Container, HTMLTextView, UIModal}
import io.youi.example.screen.UIExampleScreen
import io.youi.font.GoogleFont
import io.youi.net._
import io.youi.style.{HTMLBorder, HTMLBorderStyle}

import scala.concurrent.Future
import scribe.Execution.global

class ModalExample extends UIExampleScreen {
  override def title: String = "Modal Example"
  override def path: Path = path"/examples/modal.html"

  override def createUI(): Future[Unit] = GoogleFont.`Open Sans`.load().map { fnt =>
    val myModal = UIModal(new Container {
      size.width := 800.0
      size.height := 500.0
      background := Color.White
      htmlBorder.radius := 20.0
      htmlBorder := HTMLBorder(1.0, HTMLBorderStyle.Outset, Color.Black)
    })
    val button = new HTMLTextView {
      value := "Open Modal"
      font := fnt
      font.size := 64.px
      cursor := Cursor.Pointer
      background := Color.Blue
      color := Color.White
      htmlBorder.radius := 10.0
      position.center := container.size.center()
      position.middle := container.size.middle()
      event.click.on {
        myModal.show()
      }
    }
    container.children += button
    container.children += myModal
  }
}