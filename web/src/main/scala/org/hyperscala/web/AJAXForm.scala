package org.hyperscala.web

import org.hyperscala.html.{Input, Button, Script, Form}
import io.Source
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.Unique
import org.hyperscala.html.attributes.InputType

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait AJAXForm extends Form {
  lazy val submitButton = HTMLPage(this).view.find {
    case tag: Button => tag.buttonType() == "submit"
    case _ => false
  }.getOrElse(throw new RuntimeException("Unable to find submit button for form!"))
  lazy val script = new Script(content = JavaScriptString(AJAXForm.template.format(id(), submitButton.id())))

  override protected def before() = {
    super.before()

    if (id() == null) {
      id := Unique()
    }
    if (submitButton.id() == null) {
      submitButton.id := Unique()
    }

    if (!contents.contains(script)) {
      contents += script
    }
  }

  contents += new Input(inputType = InputType.Hidden, name = "sendResponse", value = "false")
}

object AJAXForm {
  lazy val template = Source.fromURL(getClass.getClassLoader.getResource("ajaxform.template")).mkString
}