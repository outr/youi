package io.youi.example.ui

import io.youi._
import io.youi.app.screen.{PathActivation, Screen}
import io.youi.font.GoogleFont
import io.youi.gui._
import io.youi.gui.support.{MeasuredSupport, PositionSupport}
import io.youi.gui.types.PositionType
import io.youi.net._
import reactify._
import org.scalajs.dom.document

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class HelloWorld extends Screen with PathActivation {
  override def title: String = "Hello World"
  override def path: Path = path"/examples/hello.html"

  private val text = new TextView() with PositionSupport with MeasuredSupport {
    content @= "Hello, World!"
    font.size @= 64.px
    color @= Some(Color.DarkBlue)
    position.`type` @= PositionType.Absolute
    position.center := ui.size.center
    position.middle := ui.size.middle
  }

  override protected def init(): Future[Unit] = super.init().flatMap { _ =>
    GoogleFont.`Lobster`.load().map { fnt =>
      text.font.family @= fnt.family
      document.body.appendChild(text)
    }
  }

  override protected def activate(): Future[Unit] = super.activate().map { _ =>
    text.style.display = "block"
  }

  override protected def deactivate(): Future[Unit] = super.deactivate().map { _ =>
    text.style.display = "none"
  }
}