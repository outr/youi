package org.hyperscala.web

import event.FormSubmit
import org.hyperscala.html._
import io.Source
import org.hyperscala.html.attributes.{AutoComplete, InputType}
import org.powerscala.property.Property
import org.hyperscala.Unique
import org.hyperscala.javascript.JavaScriptString
import scala.Some

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait ActionForm extends Form {
  val submittedBy = Property[HTMLTag]("submittedBy", null)

  val lastFocused = new Input(id = "%sLastFocused".format(ActionForm.this.name()), name = "%sLastFocused".format(ActionForm.this.name()), inputType = InputType.Hidden, autoComplete = AutoComplete.Off)
  listeners.synchronous {     // Form will receive an ActionEvent upon completion of POST
    case evt: FormSubmit => {
//      println("LastFocused: %s".format(lastFocused.value()))
      lastFocused.value() match {
        case "" | null => // Nothing selectable focused
        case s if (s.startsWith("id=")) => HTMLPage().byId[HTMLTag](s.substring(3)) match {
          case Some(tag) => {
            submittedBy := tag
            if (tag != this) {
              tag.fire(FormSubmit(evt.method))
            }
          }
          case None => // Cannot find by id
        }
        case s if (s.startsWith("name=")) => HTMLPage().byName[HTMLTag](s.substring(5)) match {
          case Some(tag) => {
            submittedBy := tag
            tag.fire(FormSubmit(evt.method))
          }
          case None => // Cannot find by name
        }
      }
    }
  }

  def focus(tag: HTMLTag): Unit = {
    if (tag.id() == null) {
      tag.id := Unique()
    }
    autoFocus(tag)
    lastFocused.value := "id=%s".format(tag.id())
  }

  def autoFocus(tag: HTMLTag) = tag match {
    case input: Input => input.autoFocus := true
    case button: Button => button.autoFocus := true
    case select: Select => select.autoFocus := true
    case textArea: TextArea => textArea.autoFocus := true
  }

  contents += lastFocused

  contents += new Script(content = JavaScriptString(ActionForm.template.format("%sLastFocused".format(ActionForm.this.name()))))
}

object ActionForm {
  lazy val template = Source.fromURL(getClass.getClassLoader.getResource("action_support.template")).mkString
}