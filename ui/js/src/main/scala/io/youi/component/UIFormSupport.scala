package io.youi.component

import io.youi.component.extras.HTMLComponent
import io.youi.theme.{HTMLComponentTheme, Theme}
import org.scalajs.dom._
import io.youi.dom._
import io.youi.form.{FormInput, FormSupport}

abstract class UIFormSupport(override val form: html.Form) extends Container(form) with FormSupport {
  def this() = {
    this(create[html.Form]("form"))
  }

  override protected def init(): Unit = {
    super.init()

    val formSubmit = create[html.Input]("input")
    formSubmit.`type` = "submit"
    formSubmit.style.display = "none"
    element.appendChild(formSubmit)

    initForm()
  }

  override protected def defaultParentTheme: Theme = UIFormSupport

  override def componentType: String = "UIFormSupport"

  def apply(component: Component): FormInput = {
    val element = HTMLComponent.element(component)
    input(element)
  }

  def apply(components: Component*): List[FormInput] = components.toList.map(apply)
}

object UIFormSupport extends HTMLComponentTheme {
  override protected def defaultParentTheme: Theme = HTMLComponent
}