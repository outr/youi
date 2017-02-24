package io.youi.activate

import io.youi.{History, dom}
import org.scalajs.dom.{Event, document, html, window}

import scala.scalajs.js

sealed trait ActivateInstruction {
  def debug: Boolean = ActivationSupport.debug

  def activate(): Unit

  def deactivate(): Unit
}

abstract class ConditionalInstruction(trueInstruction: Option[ActivateInstruction],
                                      falseInstruction: Option[ActivateInstruction]) extends ActivateInstruction {
  def condition: Boolean

  override def activate(): Unit = if (condition) {
    trueInstruction.foreach(_.activate())
  } else {
    falseInstruction.foreach(_.activate())
  }

  override def deactivate(): Unit = if (condition) {
    trueInstruction.foreach(_.deactivate())
  } else {
    falseInstruction.foreach(_.deactivate())
  }
}

class HasClassInstruction(selector: String,
                          className: String,
                          trueInstruction: Option[ActivateInstruction],
                          falseInstruction: Option[ActivateInstruction]) extends ConditionalInstruction(trueInstruction, falseInstruction) {
  override def condition: Boolean = {
    val result = dom.bySelector[html.Element](selector).forall(_.classList.contains(className))
    if (debug) println(s"HasClass(selector: $selector, className: $className, result: $result)")
    result
  }
}

class SetTitleInstruction(title: String) extends ActivateInstruction {
  private var previousTitle: String = document.title

  override def activate(): Unit = {
    if (debug) println(s"SetTitle(title: $title)")
    previousTitle = document.title
    document.title = title
  }

  override def deactivate(): Unit = {
    document.title = previousTitle
  }
}

class AddClassInstruction(selector: String, className: String) extends ActivateInstruction {
  override def activate(): Unit = {
    if (debug) println(s"AddClass(selector: $selector, className: $className)")
    dom.bySelector[html.Element](selector).foreach(_.classList.add(className))
  }

  override def deactivate(): Unit = dom.bySelector[html.Element](selector).foreach(_.classList.remove(className))
}

class RemoveClassInstruction(selector: String, className: String) extends ActivateInstruction {
  override def activate(): Unit = {
    if (debug) println(s"RemoveClass(selector: $selector, className: $className)")
    dom.bySelector[html.Element](selector).foreach(_.classList.remove(className))
  }

  override def deactivate(): Unit = dom.bySelector[html.Element](selector).foreach(_.classList.add(className))
}

class AlertInstruction(message: String) extends ActivateInstruction {
  override def activate(): Unit = {
    if (debug) println(s"Alert(message: $message)")
    window.alert(message)
  }

  override def deactivate(): Unit = {}
}

class OnClick(selector: String, instruction: ActivateInstruction) extends ActivateInstruction {
  private val listener = (evt: Event) => {
    evt.preventDefault()
    evt.stopPropagation()

    instruction.activate()
  }

  override def activate(): Unit = dom.bySelector[html.Element](selector).head.addEventListener("click", listener)

  override def deactivate(): Unit = dom.bySelector[html.Element](selector).head.removeEventListener("click", listener)
}

class Link(path: String) extends ActivateInstruction {
  override def activate(): Unit = History.pushPath(path)

  override def deactivate(): Unit = {}
}

class Call(code: String) extends ActivateInstruction {
  override def activate(): Unit = js.eval(code)

  override def deactivate(): Unit = {}
}