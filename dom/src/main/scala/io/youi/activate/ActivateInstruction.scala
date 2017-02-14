package io.youi.activate

import io.youi.dom
import org.scalajs.dom.{Event, document, html, window}

sealed trait ActivateInstruction {
  def activate(): Unit

  def deactivate(): Unit
}

abstract class ConditionalInstruction(trueInstruction: ActivateInstruction,
                                      falseInstruction: Option[ActivateInstruction]) extends ActivateInstruction {
  def condition: Boolean

  override def activate(): Unit = if (condition) {
    trueInstruction.activate()
  } else {
    falseInstruction.foreach(_.activate())
  }

  override def deactivate(): Unit = if (condition) {
    trueInstruction.deactivate()
  } else {
    falseInstruction.foreach(_.deactivate())
  }
}

class HasClassInstruction(selector: String,
                          className: String,
                          trueInstruction: ActivateInstruction,
                          falseInstruction: Option[ActivateInstruction]) extends ConditionalInstruction(trueInstruction, falseInstruction) {
  override def condition: Boolean = dom.bySelector[html.Element](selector).forall(_.classList.contains(className))
}

class SetTitleInstruction(title: String) extends ActivateInstruction {
  private var previousTitle: String = document.title

  override def activate(): Unit = {
    previousTitle = document.title
    document.title = title
  }

  override def deactivate(): Unit = {
    document.title = previousTitle
  }
}

class AddClassInstruction(selector: String, className: String) extends ActivateInstruction {
  override def activate(): Unit = dom.bySelector[html.Element](selector).foreach(_.classList.add(className))

  override def deactivate(): Unit = dom.bySelector[html.Element](selector).foreach(_.classList.remove(className))
}

class RemoveClassInstruction(selector: String, className: String) extends ActivateInstruction {
  override def activate(): Unit = dom.bySelector[html.Element](selector).foreach(_.classList.remove(className))

  override def deactivate(): Unit = dom.bySelector[html.Element](selector).foreach(_.classList.add(className))
}

class AlertInstruction(message: String) extends ActivateInstruction {
  override def activate(): Unit = window.alert(message)

  override def deactivate(): Unit = {}
}

class TestLink(selector: String, path: String) extends ActivateInstruction {
  private val listener = (evt: Event) => {
    evt.preventDefault()
    evt.stopPropagation()

    window.history.pushState(path, path, path)
  }

  override def activate(): Unit = dom.bySelector[html.Element](selector).head.addEventListener("click", listener)

  override def deactivate(): Unit = dom.bySelector[html.Element](selector).head.removeEventListener("click", listener)
}