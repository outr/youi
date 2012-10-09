package org.hyperscala.web

import org.hyperscala.html.tag.{Script, Form, Button, Input}
import io.Source
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.Unique
import org.hyperscala.html.attributes.{ButtonType, InputType}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait AJAXForm extends Form {
  def confirmMessage: String = null

  lazy val submitButton = HTMLPage(this).view.find {
    case tag: Button => tag.buttonType() == ButtonType.Submit
    case _ => false
  }.getOrElse(null)
  lazy val submitButtonId = submitButton match {
    case null => null
    case b => b.id()
  }
  def submitURL: String = null
  lazy val script = new Script(content = JavaScriptString(AJAXForm.template.format(id(), submitButtonId, jsString(confirmMessage), jsString(submitURL))))

  private def jsString(v: String) = v match {
    case null => "null"
    case _ => "'%s'".format(v)
  }

  override protected def before() = {
    super.before()

    if (id() == null) {
      id := Unique()
    }
    if (submitButton != null && submitButton.id() == null) {
      submitButton.id := Unique()
    }

    if (script != null && !contents.contains(script)) {
      contents += script
    }
  }

  contents += new Input(inputType = InputType.Hidden, name = "%sSendResponse".format(name()), value = "false")
}

object AJAXForm {
  lazy val template = Source.fromURL(getClass.getClassLoader.getResource("ajaxform.template")).mkString
}