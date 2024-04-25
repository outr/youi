//package io.youi.example.ui
//
//import io.youi._
//import io.youi.component.{Container, HTMLTextView}
//import io.youi.example.screen.UIExampleScreen
//import io.youi.font.GoogleFont
//import spice.net._
//import io.youi.style.{HTMLBorder, HTMLBorderStyle, PointerEvents, WhiteSpace}
//import reactify._
//
//import scala.concurrent.ExecutionContext.Implicits.global
//import scala.concurrent.Future
//
//class MeasuredExample extends UIExampleScreen {
//  override def title: String = "Measured Example"
//  override def path: Path = path"/examples/measured.html"
//
//  override def createUI(): Future[Unit] = GoogleFont.`Lobster`.load().map { fnt =>
//    val textView = new HTMLTextView {
//      value @= "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.<br/><br/>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
//      font @= fnt
//      font.size @= 24.px
//      color @= Color.DarkBlue
//      size.width @= 600.px
//      position.center := container.size.center
//      position.middle := container.size.middle
//    }
//    val heading = new HTMLTextView {
//      value @= "Heading Test"
//      font @= fnt
//      font.size @= 36.px
//      whiteSpace @= WhiteSpace.NoWrap
//      color @= Color.DarkBlue
//      position.center := container.size.center
//      position.bottom := textView.position.top - 10.0
//      event.click.on {
//        font.size.option.static(Some(font.size() + 5.0))
//      }
//    }
//    val textOutline = new Container {
//      pointerEvents @= PointerEvents.None
//      htmlBorder @= HTMLBorder(2.0, HTMLBorderStyle.Dotted, Color.DarkRed)
//      size.width := textView.size.view.width
//      size.height := textView.size.view.height
//      position.center := container.size.center
//      position.middle := container.size.middle
//    }
//    val headingOutline = new Container {
//      pointerEvents @= PointerEvents.None
//      htmlBorder @= HTMLBorder(2.0, HTMLBorderStyle.Dotted, Color.DarkGreen)
//      position.left := heading.position.left
//      position.top := heading.position.top
//      size.width := heading.size.view.width
//      size.height := heading.size.view.height
//    }
//    container.children ++= List(heading, textView, headingOutline, textOutline)
//  }
//}