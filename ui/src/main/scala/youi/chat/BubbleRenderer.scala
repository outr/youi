package youi.chat

import youi.Color
import youi.component.{Container, ImageView, TextView}
import youi.component.support.{MaxSizeSupport, PaddingSupport}
import youi.component.types.{Display, SizeType, WhiteSpace}

import scala.scalajs.js

object BubbleRenderer extends MessageRenderer {
  // Ensure theme CSS rules are installed
  ChatTheme.init()

  override def render(message: ChatMessage): Container = message match {
    case m: ChatMessage.Text      => createBubble(m.text, m.role, m.timestamp)
    case m: ChatMessage.WithImage => createImageBubble(m.text, m.image, m.role, m.timestamp)
    case m: ChatMessage.Rich      => createRichBubble(m.html, m.role, m.timestamp)
    case m: ChatMessage.Custom    => wrapCustom(m.factory(), m.role)
    case m: ChatMessage.Group     => render(m.summary)
  }

  override def renderStreaming(msg: ChatMessage.Text): StreamingHandle = {
    val bubble = createBubble(msg.text, msg.role, msg.timestamp)
    // The text view is the first child with content (after any image)
    val textView = bubble.children().collectFirst { case tv: TextView if tv.whiteSpace() == WhiteSpace.PreWrap => tv }
      .getOrElse(throw new IllegalStateException("No text view found in bubble"))

    StreamingHandle(
      component = bubble,
      appendText = text => textView.content @= textView.content() + text,
      complete = () => ()
    )
  }

  private def createBubble(text: String, role: String, timestamp: Long): Container = {
    val bubble = new Container with PaddingSupport
    bubble.classes += ChatTheme.bubbleClassForRole(role)

    val textView = new TextView {
      content @= text
      whiteSpace @= WhiteSpace.PreWrap
      display @= Display.Block
    }
    bubble.children += textView

    bubble.children += makeTimestamp(timestamp)
    bubble
  }

  private def createImageBubble(text: String, img: youi.image.Image, role: String, timestamp: Long): Container = {
    val bubble = new Container with PaddingSupport
    bubble.classes += ChatTheme.bubbleClassForRole(role)

    val scale = math.min(300.0 / img.width, 200.0 / img.height).min(1.0)
    val imageView = new ImageView with MaxSizeSupport {
      image @= img
      maxSize.width.set(100.0, SizeType.Percent)
      maxSize.height @= 200.0
      width @= img.width * scale
      height @= img.height * scale
      display @= Display.Block
    }
    bubble.children += imageView

    if (text.nonEmpty) {
      val textView = new TextView {
        content @= text
        whiteSpace @= WhiteSpace.PreWrap
        display @= Display.Block
      }
      bubble.children += textView
    }

    bubble.children += makeTimestamp(timestamp)
    bubble
  }

  private def createRichBubble(html: String, role: String, timestamp: Long): Container = {
    val bubble = new Container with PaddingSupport
    bubble.classes += ChatTheme.bubbleClassForRole(role)

    val content = new Container
    content.element.innerHTML = html
    content.display @= Display.Block
    bubble.children += content

    bubble.children += makeTimestamp(timestamp)
    bubble
  }

  private def wrapCustom(component: youi.component.Component, role: String): Container = {
    val wrapper = new Container with PaddingSupport
    wrapper.classes += ChatTheme.bubbleClassForRole(role)
    wrapper.children += component
    wrapper
  }

  private def makeTimestamp(timestamp: Long): TextView = {
    val date = new js.Date(timestamp.toDouble)
    val timeStr = s"${pad(date.getHours().toInt)}:${pad(date.getMinutes().toInt)}"
    new TextView {
      content @= timeStr
      classes += "chat-timestamp"
    }
  }

  private def pad(n: Int): String = if (n < 10) s"0$n" else n.toString
}
