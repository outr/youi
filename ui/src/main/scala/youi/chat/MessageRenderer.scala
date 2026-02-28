package youi.chat

import youi.component.{Component, TextView}
import youi.component.types.WhiteSpace

trait MessageRenderer {
  def render(message: ChatMessage): Component

  def renderStreaming(message: ChatMessage.Text): StreamingHandle = {
    val component = render(message)
    val textView = new TextView {
      content @= message.text
      whiteSpace @= WhiteSpace.PreWrap
    }
    StreamingHandle(
      component = component,
      appendText = text => textView.content @= textView.content() + text,
      complete = () => ()
    )
  }
}

case class StreamingHandle(
  component: Component,
  appendText: String => Unit,
  complete: () => Unit = () => ()
)
