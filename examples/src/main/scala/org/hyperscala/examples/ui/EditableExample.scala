package org.hyperscala.examples.ui

import org.hyperscala.web.live._
import org.hyperscala.ui.widgets.editable._

import org.hyperscala.html._
import org.powerscala.Country

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class EditableExample extends LivePage {
  val editableString = new Editable[String, tag.Input]("test") with InputEditable with StringEditable
  val editableEnum = new EnumEditable(Country, Country.AG)
  val editableTextArea = new Editable[String, tag.TextArea]("Testing text area!") with TextAreaEditable with StringEditable
  val button = new tag.Button(content = "Toggle Editing") {
    this.onClick {
      editableString.editing := !editableString.editing()
      editableEnum.editing := !editableEnum.editing()
      editableTextArea.editing := !editableTextArea.editing()
    }
  }

  body.contents += editableString
  body.contents += editableEnum
  body.contents += editableTextArea
  body.contents += button
}
