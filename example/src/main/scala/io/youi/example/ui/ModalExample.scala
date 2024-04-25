//package io.youi.example.ui
//
//import io.youi._
//import io.youi.component.{Container, HTMLTextView, UIModal}
//import io.youi.easing.Easing
//import io.youi.example.screen.UIExampleScreen
//import io.youi.font.GoogleFont
//import spice.net._
//import io.youi.style.{Display, HTMLBorder, HTMLBorderStyle}
//import io.youi.task._
//
//import scala.concurrent.Future
//import scala.concurrent.duration._
//import scribe.Execution.global
//
//class ModalExample extends UIExampleScreen {
//  override def title: String = "Modal Example"
//  override def path: Path = path"/examples/modal.html"
//
//  override def createUI(): Future[Unit] = GoogleFont.`Open Sans`.load().map { fnt =>
//    val myModal = new Container with UIModal {
//      size.width @= 800.0
//      size.height @= 500.0
//      background @= Color.White
//      htmlBorder.radius @= 20.0
//      htmlBorder @= HTMLBorder(1.0, HTMLBorderStyle.Outset, Color.Black)
//
//      children += new HTMLTextView {
//        value @= "This is a modal!"
//        font @= fnt
//        font.size @= 50.px
//        color @= Color.DarkSlateBlue
//        position.center @= 400.0
//        position.middle @= 250.0
//      }
//
//      position.top @= 100.0
//
//      override def show(): Unit = {
//        position.bottom @= 0.0
//        opacity @= 0.0
//        parallel(
//          position.top to 100.0 in 250.millis easing Easing.quadraticOut,
//          opacity to 1.0 in 250.millis
//        ).start(this)
//
//        super.show()
//      }
//
//      override def hide(): Unit = {
//        UIModal.active @= None
//        parallel(
//          position.bottom to 0.0 in 500.millis,
//          opacity to 0.0 in 250.millis
//        )
//          .andThen(synchronous(display @= Display.None))
//          .start(this)
//      }
//    }
//    val button = new HTMLTextView {
//      value @= "Open Modal"
//      font @= fnt
//      font.size @= 64.px
//      cursor @= Cursor.Pointer
//      background @= Color.Blue
//      color @= Color.White
//      htmlBorder.radius @= 10.0
//      position.center := container.size.center()
//      position.middle := container.size.middle()
//      event.click.on {
//        myModal.show()
//      }
//    }
//    container.children += button
//    container.children += myModal
//  }
//}