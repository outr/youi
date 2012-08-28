package org.hyperscala.web

import org.hyperscala.html._
import attributes.{InputType, ButtonType}
import tag.{Form, Button, Input}

/**
 * ActionButton fires FormSubmit events when clicked.
 *
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class ActionButton(_name: String, text: String, override val confirmMessage: String = null) extends Form(id = _name, name = _name) with AJAXForm {
  val button = new Button(id = "%sButton".format(_name), name = "%sButton".format(_name), content = text, buttonType = ButtonType.Submit)
  val input = new Input(id = "%sHidden".format(_name), name = "%sHidden".format(_name), value = "%sHidden".format(_name), inputType = InputType.Hidden)

  contents += button
  contents += input
}