package io.youi.example.ui.drawable

import io.youi.Color
import io.youi.app.screen.DrawableScreen
import io.youi.drawable._
import io.youi.example.ui.UIExampleScreen
import io.youi.font.{GoogleFont, OpenTypeFont}
import io.youi.paint.Paint

import scala.concurrent.Future

object HeavyTextExample extends UIExampleScreen with DrawableScreen {
  override def name: String = "Heavy Text Example"

  override protected val drawable: Future[RenderTimer] = OpenTypeFont.fromURL(GoogleFont.`Open Sans`.`regular`).map(_.cached).map { font =>
    def row(y: Double, size: Double, fill: Paint): Group = {
      val chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789~!@#$%^&*()-=_+{}[]:".map { c =>
        font(c.toString, size).toDrawable(fill)
      }.zipWithIndex.map {
        case (c, index) => Transformation(x = (size * 0.7) * index, y = y)(c)
      }
      Group(chars: _*)
    }
    def rows = (0 until 80).map { index =>
      row(10.0 * index, 26.0, Color.random)
    }
    RenderTimer(
      Group(rows: _*)
    )
  }

  override def path: String = "/examples/drawable/heavy-text.html"
}