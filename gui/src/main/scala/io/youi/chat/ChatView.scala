package io.youi.chat

import io.youi.component.Container
import io.youi.component.support.{FontSupport, OverflowSupport}
import io.youi.component.types.Overflow
import io.youi.datatransfer.DataTransferManager
import reactify._

class ChatView extends Container with OverflowSupport with FontSupport {
  overflow @= Overflow.Hidden

  val messageList: MessageList = new MessageList
  val chatInput: ChatInput = new ChatInput

  val messageSent: Channel[ChatMessage] = Channel[ChatMessage]

  // Message list fills all height minus input bar
  messageList.size.width := size.width
  messageList.size.height := math.max(0.0, size.height() - chatInput.size.height())

  // Input bar at bottom, full width
  chatInput.size.width := size.width
  chatInput.position.top := math.max(0.0, size.height() - chatInput.size.height())

  // Wire send -> list + external channel
  chatInput.sent.attach { msg =>
    messageList.addMessage(msg)
    messageSent @= msg
  }

  children ++= Seq(messageList, chatInput)

  // Enable drag-drop images onto entire chat
  DataTransferManager.addDragTarget(element)

  def addMessage(msg: ChatMessage): MessageBubble = messageList.addMessage(msg)
}
