package io.youi.chat

import io.youi.Color
import io.youi.component.{Container, ImageView, TextView}
import io.youi.component.support.{BorderSupport, MaxSizeSupport, PaddingSupport}
import io.youi.component.types.{Display, WhiteSpace}

import scala.scalajs.js

class MessageBubble(message: ChatMessage) extends Container with BorderSupport with PaddingSupport {
  backgroundColor @= (message.role match {
    case "assistant" => Color.fromHex("3a3a3a")
    case "system"    => Color.fromHex("888888")
    case _           => Color.SteelBlue
  })
  border.radius @= 12.0
  padding @= 10.0
  display @= Display.InlineBlock

  element.style.setProperty("max-width", "70%")
  element.style.setProperty("box-sizing", "border-box")
  element.style.setProperty("overflow-wrap", "break-word")

  message.image.foreach { img =>
    val imageView = new ImageView with MaxSizeSupport {
      image @= img
      maxSize.width @= 300.0
      maxSize.height @= 200.0
      val scale = math.min(300.0 / img.width, 200.0 / img.height).min(1.0)
      width @= img.width * scale
      height @= img.height * scale
      display @= Display.Block
    }
    children += imageView
  }

  private val textView: TextView = new TextView {
    content @= message.text
    color @= Color.White
    font.size @= 14.0
    whiteSpace @= WhiteSpace.PreWrap
    display @= Display.Block
    if (message.role == "system") element.style.fontStyle = "italic"
  }
  children += textView

  private val date = new js.Date(message.timestamp.toDouble)
  private val timeStr = s"${pad(date.getHours().toInt)}:${pad(date.getMinutes().toInt)}"

  val timestampView: TextView = new TextView {
    content @= timeStr
    color @= Color.White.withAlpha(0.6)
    font.size @= 11.0
    display @= Display.Block
  }
  children += timestampView

  /** Append text to this bubble (for streaming tokens). */
  def appendText(text: String): Unit = {
    textView.content @= textView.content() + text
  }

  private def pad(n: Int): String = if (n < 10) s"0$n" else n.toString
}
