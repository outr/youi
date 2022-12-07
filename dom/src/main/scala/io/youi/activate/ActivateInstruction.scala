package io.youi.activate

import io.youi.{History, dom}
import org.scalajs.dom.{Event, document, html, window}
import spice.net.Path

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
  private var activated = Vector.empty[html.Element]

  override def activate(): Unit = {
    if (debug) println(s"AddClass(selector: $selector, className: $className)")
    activated = dom.bySelector[html.Element](selector).filter(!_.classList.contains(className))
    activated.foreach(_.classList.add(className))
  }

  override def deactivate(): Unit = {
    activated.foreach(_.classList.remove(className))
    activated = Vector.empty
  }
}

class RemoveClassInstruction(selector: String, className: String) extends ActivateInstruction {
  private var activated = Vector.empty[html.Element]

  override def activate(): Unit = {
    if (debug) println(s"RemoveClass(selector: $selector, className: $className)")
    activated = dom.bySelector[html.Element](selector).filter(_.classList.contains(className))
    activated.foreach(_.classList.remove(className))
  }

  override def deactivate(): Unit = {
    activated.foreach(_.classList.add(className))
    activated = Vector.empty
  }
}

class ReplaceContentInstruction(selector: String, content: String) extends ActivateInstruction {
  private var activated = Vector.empty[(html.Element, String)]

  override def activate(): Unit = {
    if (debug) println(s"ReplaceContent(selector: $selector, content: $content)")
    activated = dom.bySelector[html.Element](selector).map(e => e -> e.innerHTML)
    activated.foreach(_._1.innerHTML = content)
  }

  override def deactivate(): Unit = {
    activated.foreach(e => e._1.innerHTML = e._2)
    activated = Vector.empty
  }
}

class ReplaceAttributeInstruction(selector: String, attributeName: String, content: String) extends ActivateInstruction {
  private var activated = Vector.empty[(html.Element, String)]

  override def activate(): Unit = {
    if (debug) println(s"ReplaceAttribute(selector: $selector, attributeName: $attributeName, content: $content)")
    activated = dom.bySelector[html.Element](selector).map(e => e -> e.getAttribute(attributeName))
    activated.foreach(_._1.setAttribute(attributeName, content))
  }

  override def deactivate(): Unit = {
    activated.foreach(e => e._1.setAttribute(attributeName, e._2))
    activated = Vector.empty
  }
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
    if (evt.target.isInstanceOf[html.Button]) {
      evt.preventDefault()
      evt.stopPropagation()
    }

    instruction.activate()
  }

  override def activate(): Unit = dom.oneBySelector[html.Element](selector).addEventListener("click", listener)

  override def deactivate(): Unit = dom.oneBySelector[html.Element](selector).removeEventListener("click", listener)
}

class OnChecked(selector: String, instruction: ActivateInstruction) extends ActivateInstruction {
  private val listener = (evt: Event) => if (evt.target.asInstanceOf[html.Input].checked) {
    instruction.activate()
  } else {
    instruction.deactivate()
  }

  override def activate(): Unit = dom.oneBySelector[html.Element](selector).addEventListener("change", listener)

  override def deactivate(): Unit = dom.oneBySelector[html.Element](selector).removeEventListener("change", listener)
}

class OnUnchecked(selector: String, instruction: ActivateInstruction) extends ActivateInstruction {
  private val listener = (evt: Event) => {
    evt.preventDefault()
    evt.stopPropagation()

    if (evt.target.asInstanceOf[html.Input].checked) {
      instruction.deactivate()
    } else {
      instruction.activate()
    }
  }

  override def activate(): Unit = dom.oneBySelector[html.Element](selector).addEventListener("change", listener)

  override def deactivate(): Unit = dom.oneBySelector[html.Element](selector).removeEventListener("change", listener)
}

class Link(path: Path) extends ActivateInstruction {
  override def activate(): Unit = History.pushPath(path)

  override def deactivate(): Unit = {}
}

class Call(code: String) extends ActivateInstruction {
  override def activate(): Unit = js.eval(code)

  override def deactivate(): Unit = {}
}