package org.hyperscala.examples.ui

import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.jquery.Gritter
import org.hyperscala.realtime._
import org.hyperscala.ui.dynamic.DynamicTag
import org.hyperscala.ui.form.CaseForm
import org.hyperscala.ui.form.error.{ErrorMessage, FieldErrorClassSupport, HTMLErrorSupport}
import org.hyperscala.web._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class CaseFormExample extends Webpage with Example {
  require(Realtime)
  require(Gritter)
  this.connectForm()
  head.contents += new tag.Link(href = "/css/case_form.css")
  body.contents += new tag.P {
    contents += "CaseForm provides a simple generated form wrapper around a case class allowing visualization and editing of a case class in real-time."
  }

  val form = DynamicTag.url[tag.Form]("case_form_example", getClass.getClassLoader.getResource("case_form.html")).create()
  val errorSupport = new HTMLErrorSupport[tag.Li, tag.Ul](form.getById[tag.Ul]("errors")) with FieldErrorClassSupport {
    def page = CaseFormExample.this
    protected def className = "error"

    override protected def displayError(error: ErrorMessage) = {
      super.displayError(error)

      container.contents += new tag.Li(content = error.message)
    }
  }
  new CaseForm[CaseFormExampleEntry](form, errorSupport) {
    fieldValidator[String]("name") {
      case n => if (n.length < 4) {
        Some("Name must be at least four characters")
      } else {
        None
      }
    }
    property := CaseFormExampleEntry("John Doe", 123)

    def submit() = {
      Gritter.add(CaseFormExample.this, "Form Submitted", s"Value: ${property()}")
    }
  }

  body.contents += form
}

case class CaseFormExampleEntry(name: String, age: Int)