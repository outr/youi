package org.hyperscala.examples.ui

import org.hyperscala.examples.Example
import org.hyperscala.ui.dynamic.DynamicTag
import org.hyperscala.html._
import org.hyperscala.ui.form.CaseForm
import org.hyperscala.web.Webpage
import org.hyperscala.realtime.Realtime
import org.hyperscala.ui.form.error.{FieldErrorClassSupport, ErrorMessage, HTMLErrorSupport}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class CaseFormExample extends Example {
  Webpage().require(Realtime)
  Realtime.connectForm()

  Webpage().head.contents += new tag.Link(href = "/css/case_form.css")

  val form = DynamicTag.url[tag.Form]("case_form_example", getClass.getClassLoader.getResource("case_form.html")).create()
  val errorSupport = new HTMLErrorSupport[tag.Li, tag.Ul](form.getById[tag.Ul]("errors")) with FieldErrorClassSupport {
    protected def className = "error"

    override protected def displayError(error: ErrorMessage) = {
      super.displayError(error)

      container.contents += new tag.Li(content = error.message)
    }
  }
  val caseForm = new CaseForm[CaseFormExampleEntry](form, errorSupport) {
    fieldValidator[String]("name") {
      case n => if (n.length < 4) {
        Some("Name must be at least four characters")
      } else {
        None
      }
    }
    property := CaseFormExampleEntry("John Doe", 123)

    def submit() = {
      Thread.sleep(5000)
      println("Submit!")
    }
  }

  contents += form
}

case class CaseFormExampleEntry(name: String, age: Int)