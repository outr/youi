package org.hyperscala.web

import org.hyperscala.html.{HTMLTag, Input, Script, Form}
import io.Source
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.html.attributes.InputType
import org.powerscala.event.ActionEvent
import org.powerscala.property.Property
import org.hyperscala.Unique

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait ActionForm extends Form {
  val submittedBy = Property[HTMLTag]("submittedBy", null)

  val lastFocused = new Input(id = "lastFocused", name = "lastFocused", inputType = InputType.Hidden, autoComplete = "off")
  listeners.synchronous {     // Form will receive an ActionEvent upon completion of POST
    case evt: ActionEvent if (evt.action == "submit") => {
      lastFocused.value() match {
        case "" => // Nothing selectable focused
        case s if (s.startsWith("id=")) => HTMLPage().byId[HTMLTag](s.substring(3)) match {
          case Some(tag) => {
            submittedBy := tag
            tag.fire(ActionEvent("submit"))
          }
          case None => // Cannot find by id
        }
        case s if (s.startsWith("name=")) => HTMLPage().byName[HTMLTag](s.substring(5)) match {
          case Some(tag) => {
            submittedBy := tag
            tag.fire(ActionEvent("submit"))
          }
          case None => // Cannot find by name
        }
      }
    }
  }

  def focus(tag: HTMLTag) = {
    if (tag.id() == null) {
      tag.id := Unique()
    }
    lastFocused.value := "id=%s".format(tag.id())
  }

  contents += lastFocused

  contents += new Script(content = JavaScriptString(ActionForm.template.format("lastFocused")))
}

object ActionForm {
  lazy val template = Source.fromURL(getClass.getClassLoader.getResource("action_support.template")).mkString
}