package youi.chat

import youi.component.{Component, Container, TextView}
import youi.component.support.{OverflowSupport, PaddingSupport, ScrollSupport}
import youi.component.types.{Display, Overflow}
import youi.event.EventSupport
import org.scalajs.dom
import reactify.Var

class MessageListView(renderer: MessageRenderer) extends Container with ScrollSupport with OverflowSupport with PaddingSupport {
  overflow.x @= Overflow.Hidden
  overflow.y @= Overflow.Auto
  padding @= 10.0

  element.style.setProperty("box-sizing", "border-box")
  element.style.display = "flex"
  element.style.setProperty("flex-direction", "column")
  element.style.setProperty("gap", "var(--chat-gap, 8px)")

  private var allMessages: Vector[(ChatMessage, Component)] = Vector.empty
  private var visibleStartIndex: Int = 0
  private var pool: Vector[Component] = Vector.empty

  val maxVisible: Var[Int] = Var(100)
  val loadEarlierBatchSize: Var[Int] = Var(50)
  val autoLoadOnScroll: Var[Boolean] = Var(false)
  val recycleEnabled: Var[Boolean] = Var(true)

  // "Load earlier" button
  private val loadEarlierButton: TextView & EventSupport = new TextView with EventSupport {
    classes += "chat-load-earlier"
    content @= "Load earlier messages"
    display @= Display.None
  }
  loadEarlierButton.event.click.on {
    loadEarlier()
  }
  children += loadEarlierButton

  // Scroll listener for auto-load
  element.addEventListener("scroll", { (_: dom.Event) =>
    if (autoLoadOnScroll() && element.scrollTop < 50 && visibleStartIndex > 0) {
      loadEarlier()
    }
  })

  def addMessage(msg: ChatMessage): Component = {
    val component = msg match {
      case g: ChatMessage.Group => new MessageGroupEntry(g, renderer)
      case _ => renderer.render(msg)
    }

    // Role-based alignment
    component.element.style.setProperty("align-self", alignForRole(msg.role))

    allMessages = allMessages :+ (msg, component)

    // Evict if over max
    if (allMessages.size - visibleStartIndex > maxVisible()) {
      evictOldest(allMessages.size - visibleStartIndex - maxVisible())
    }

    children += component
    scrollToBottom()
    component
  }

  def addStreamingMessage(msg: ChatMessage.Text): StreamingHandle = {
    val handle = renderer.renderStreaming(msg)

    handle.component.element.style.setProperty("align-self", alignForRole(msg.role))

    allMessages = allMessages :+ (msg, handle.component)

    if (allMessages.size - visibleStartIndex > maxVisible()) {
      evictOldest(allMessages.size - visibleStartIndex - maxVisible())
    }

    children += handle.component
    scrollToBottom()
    handle
  }

  def replaceMessage(id: String, newMsg: ChatMessage): Unit = {
    val idx = allMessages.indexWhere(_._1.id == id)
    if (idx >= 0) {
      val (_, oldComponent) = allMessages(idx)
      val newComponent = renderer.render(newMsg)
      newComponent.element.style.setProperty("align-self", alignForRole(newMsg.role))
      allMessages = allMessages.updated(idx, (newMsg, newComponent))
      // Only replace in DOM if visible
      if (idx >= visibleStartIndex) {
        children.replace(oldComponent, newComponent)
      }
    }
  }

  def clearMessages(): Unit = {
    allMessages.drop(visibleStartIndex).foreach { case (_, comp) =>
      children -= comp
    }
    allMessages = Vector.empty
    visibleStartIndex = 0
    pool = Vector.empty
    loadEarlierButton.display @= Display.None
  }

  private def evictOldest(count: Int): Unit = {
    val toEvict = math.min(count, allMessages.size - visibleStartIndex)
    for (i <- visibleStartIndex until visibleStartIndex + toEvict) {
      val comp = allMessages(i)._2
      children -= comp
      if (recycleEnabled()) {
        pool = pool :+ comp
      }
    }
    visibleStartIndex += toEvict
    updateLoadEarlierVisibility()
  }

  private def loadEarlier(): Unit = {
    val batchSize = loadEarlierBatchSize()
    val newStart = math.max(0, visibleStartIndex - batchSize)
    if (newStart < visibleStartIndex) {
      val toLoad = allMessages.slice(newStart, visibleStartIndex)
      toLoad.foreach { case (msg, component) =>
        component.element.style.setProperty("align-self", alignForRole(msg.role))
      }
      // Build new children vector: loadEarlierButton + new messages + existing visible messages
      val existingVisible = children().filterNot(_ eq loadEarlierButton)
      val newComponents = toLoad.map(_._2)
      val newChildren = Vector(loadEarlierButton) ++ newComponents ++ existingVisible
      // Use static assignment — the changes handler will sync the DOM via verifyChildrenInOrder
      children @= newChildren
      visibleStartIndex = newStart
      updateLoadEarlierVisibility()
    }
  }

  private def updateLoadEarlierVisibility(): Unit = {
    loadEarlierButton.display @= (if (visibleStartIndex > 0) Display.Block else Display.None)
  }

  private def scrollToBottom(): Unit = {
    dom.window.requestAnimationFrame { _ =>
      element.scrollTop = element.scrollHeight.toDouble
    }
  }

  private def alignForRole(role: String): String = role match {
    case "user"   => "flex-end"
    case "system" => "center"
    case _        => "stretch"
  }
}
