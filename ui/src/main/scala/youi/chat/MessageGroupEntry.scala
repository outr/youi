package youi.chat

import youi.component.{Component, Container, TextView}
import youi.component.support.{BorderSupport, PaddingSupport}
import youi.component.types.Display
import youi.event.EventSupport
import reactify.Var

class MessageGroupEntry(group: ChatMessage.Group, renderer: MessageRenderer)
    extends Container with BorderSupport with PaddingSupport {

  element.style.setProperty("box-sizing", "border-box")

  val expanded: Var[Boolean] = Var(false)

  // Summary — always visible
  private var summaryComponent: Component = renderer.render(group.summary)

  // Toggle label
  private val toggleLabel: TextView & EventSupport = new TextView with EventSupport {
    classes += "chat-group-toggle"
    content @= toggleText(false)
  }
  toggleLabel.event.click.on {
    expanded @= !expanded()
  }

  // Steps container — hidden by default
  private val stepsContainer: Container = new Container {
    display @= Display.None
    element.style.setProperty("flex-direction", "column")
    element.style.setProperty("gap", "6px")
    element.style.setProperty("padding-left", "12px")
    element.style.setProperty("border-left", "2px solid rgba(255,255,255,0.2)")
    element.style.setProperty("margin-top", "4px")
  }

  private var stepsPopulated = false
  private var currentSteps: Vector[ChatMessage] = group.steps

  expanded.attach {
    case true =>
      populateIfNeeded()
      stepsContainer.display @= Display.Flex
      toggleLabel.content @= toggleText(true)
    case false =>
      stepsContainer.display @= Display.None
      toggleLabel.content @= toggleText(false)
  }

  children ++= Vector(summaryComponent, toggleLabel, stepsContainer)

  // Show toggle only if there are steps
  if (currentSteps.isEmpty) toggleLabel.display @= Display.None

  def addStep(msg: ChatMessage): Unit = {
    currentSteps = currentSteps :+ msg
    toggleLabel.display @= Display.Block
    toggleLabel.content @= toggleText(expanded())
    if (expanded() && stepsPopulated) {
      stepsContainer.children += renderer.render(msg)
    } else if (expanded()) {
      populateIfNeeded()
    }
  }

  def updateSummary(msg: ChatMessage): Unit = {
    val newSummary = renderer.render(msg)
    val idx = children().indexOf(summaryComponent)
    if (idx >= 0) {
      children.replace(summaryComponent, newSummary)
    }
    summaryComponent = newSummary
  }

  private def populateIfNeeded(): Unit = if (!stepsPopulated) {
    stepsPopulated = true
    currentSteps.foreach { step =>
      stepsContainer.children += renderer.render(step)
    }
  }

  private def toggleText(isExpanded: Boolean): String = {
    val count = currentSteps.size
    val noun = if (count == 1) "step" else "steps"
    if (isExpanded) s"▾ Hide $count $noun" else s"▸ Show $count $noun"
  }
}
