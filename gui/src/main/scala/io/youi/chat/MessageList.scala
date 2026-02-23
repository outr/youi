package io.youi.chat

import io.youi.component.Container
import io.youi.component.support.{OverflowSupport, PaddingSupport, ScrollSupport}
import io.youi.component.types.Overflow

class MessageList extends Container with ScrollSupport with OverflowSupport with PaddingSupport {
  overflow.x @= Overflow.Hidden
  overflow.y @= Overflow.Auto
  padding @= 10.0

  // Use CSS flex column for vertical stacking with gap and role-based alignment
  element.style.setProperty("box-sizing", "border-box")
  element.style.display = "flex"
  element.style.setProperty("flex-direction", "column")
  element.style.setProperty("gap", "8px")

  def addMessage(msg: ChatMessage): MessageBubble = {
    val bubble = new MessageBubble(msg)
    // Role-based horizontal alignment
    bubble.element.style.setProperty("align-self", msg.role match {
      case "user"   => "flex-end"
      case "system" => "center"
      case _        => "flex-start"
    })
    children += bubble
    // Auto-scroll to bottom after DOM update
    org.scalajs.dom.window.requestAnimationFrame { _ =>
      element.scrollTop = element.scrollHeight.toDouble
    }
    bubble
  }
}
