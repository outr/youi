package org.hyperscala.examples.ui

import org.hyperscala.examples.Example
import org.hyperscala.ui.dynamic.DynamicTag
import org.hyperscala.html._
import org.hyperscala.ui.form.CaseForm
import org.hyperscala.web._
import org.hyperscala.realtime._
import org.hyperscala.ui.form.error.{FieldErrorClassSupport, ErrorMessage, HTMLErrorSupport}
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <matt@outr.com>
 */
class CaseFormExample extends Example {
  this.require(Realtime)
  connected[Webpage[Session]] {
    case webpage => {
      webpage.connectForm()
      webpage.head.contents += new tag.Link(href = "/css/case_form.css")
      contents += new tag.P {
        contents += "CaseForm provides a simple generated form wrapper around a case class allowing visualization and editing of a case class in real-time."
      }

      val form = DynamicTag.url[tag.Form]("case_form_example", getClass.getClassLoader.getResource("case_form.html")).create()
      val errorSupport = new HTMLErrorSupport[tag.Li, tag.Ul](form.getById[tag.Ul]("errors")) with FieldErrorClassSupport {
        def page = webpage
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
          Thread.sleep(1000)
          println(s"Submit! ${property()}")
        }
      }

      contents += form
    }
  }
}

case class CaseFormExampleEntry(name: String, age: Int)