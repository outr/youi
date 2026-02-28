package youi.chat

import youi._
import youi.component.{Component, Container}
import youi.component.support.{FontSupport, OverflowSupport, ThemeSupport}
import youi.component.types.Overflow
import youi.datatransfer.DataTransferManager
import reactify.{Channel, Var}

class ChatView(
  val renderer: MessageRenderer = BubbleRenderer,
  showInput: Boolean = true
) extends Container with OverflowSupport with FontSupport with ThemeSupport {
  classes += "chat-view"
  overflow @= Overflow.Hidden

  ChatTheme.init()

  val messageList: MessageListView = new MessageListView(renderer)
  val chatInput: Option[ChatInput] = if (showInput) Some(new ChatInput) else None

  val messageSent: Channel[ChatMessage] = Channel[ChatMessage]

  // Message list fills all height minus input bar
  messageList.size.width := size.width
  chatInput match {
    case Some(input) =>
      messageList.size.height := math.max(0.0, size.height() - input.size.height())
      input.size.width := size.width
      input.position.top := math.max(0.0, size.height() - input.size.height())

      input.sent.attach { msg =>
        messageList.addMessage(msg)
        messageSent @= msg
      }

      children ++= Seq(messageList, input)
      DataTransferManager.addDragTarget(element)

    case None =>
      messageList.size.height := size.height
      children += messageList
  }

  // Public API

  def addMessage(msg: ChatMessage): Component = messageList.addMessage(msg)

  def addStreamingMessage(text: String = "", role: String = "assistant"): StreamingHandle = {
    val msg = ChatMessage.Text(ChatMessage.nextId(), text, role)
    messageList.addStreamingMessage(msg)
  }

  def startGroup(summary: ChatMessage, role: String = "assistant"): GroupHandle = {
    val groupId = ChatMessage.nextId()
    val group = ChatMessage.Group(groupId, summary, Vector.empty, role)
    val entry = messageList.addMessage(group).asInstanceOf[MessageGroupEntry]
    GroupHandle(groupId, entry)
  }

  def replaceMessage(id: String, newMsg: ChatMessage): Unit = messageList.replaceMessage(id, newMsg)

  def clearMessages(): Unit = messageList.clearMessages()

  def maxVisibleMessages: Var[Int] = messageList.maxVisible

  def autoLoadOnScroll: Var[Boolean] = messageList.autoLoadOnScroll
}

case class GroupHandle(groupId: String, private val entry: MessageGroupEntry) {
  def addStep(msg: ChatMessage): Unit = entry.addStep(msg)
  def updateSummary(msg: ChatMessage): Unit = entry.updateSummary(msg)
}
