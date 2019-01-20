package io.youi.component

import io.youi.{AnimationFrame, ui}
import io.youi.component.extras.HTMLComponent
import io.youi.dom._
import io.youi.theme.{HTMLTextInputTheme, Theme}
import org.scalajs.dom._

class HTMLTextInput(protected val element: html.Input) extends HTMLComponent[html.Input] with HTMLTextInputTheme {
  def this() = {
    this(create[html.Input]("input"))
  }

  override protected def init(): Unit = {
    super.init()

    element.addEventListener("input", (_: Event) => {
      value := element.value
    })
  }

  override protected def defaultParentTheme: Theme = HTMLTextInput

  override def componentType: String = "HTMLTextInput"
}

object HTMLTextInput extends HTMLTextInputTheme {
  override protected def defaultParentTheme: Theme = HTMLComponent

  def existing(id: String, in: html.Element = document.body): HTMLTextInput = {
    val e = in.byId[html.Input](id)
    val i = new HTMLTextInput(e)
    HTMLComponent.existing(i)
    AnimationFrame.delta.attach(i.update)
    i
  }
}