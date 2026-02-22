package io.youi.example.ui

import rapid.Task
import io.youi.*
import io.youi.component.DrawableView
import io.youi.component.support.PositionSupport
import io.youi.drawable.{Group, TextDrawable, Transformation}
import io.youi.example.screen.UIExampleScreen
import io.youi.font.{CanvasFont, CanvasText}
import io.youi.paint.Stroke
import spice.net._

import scala.util.Random

class HeavyTextExample extends UIExampleScreen {
  override def title: String = "Heavy Text Example"
  override def path: URLPath = path"/examples/heavy-text.html"

  override def createUI(): Task[Unit] = Task {
    val size = 26.0
    val charSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789~!@#$%^&*()-=_+{}[]:"
    val font = CanvasFont("Open Sans, sans-serif", "normal", "normal", "regular")

    val rows = (0 until 80).map { index =>
      val color = Color.fromRGBA(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256), 1.0)
      val text = CanvasText(font, charSet, size, Int.MaxValue, kerning = false)
      val textDrawable = new TextDrawable(text, color, Stroke.none)
      Transformation(y = size * index)(textDrawable)
    }

    val view = new DrawableView with PositionSupport {
      width := container.size.width
      height := container.size.height
      position.left @= 20.0
      position.top @= header.size.height + 20.0
      drawable @= Group(rows*)
    }
    container.children += view
  }
}
