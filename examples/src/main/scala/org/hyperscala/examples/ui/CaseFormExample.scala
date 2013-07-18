package org.hyperscala.examples.ui

import org.hyperscala.examples.Example
import org.hyperscala.ui.dynamic.DynamicTag
import org.hyperscala.html._
import org.hyperscala.ui.form.CaseForm

/**
 * @author Matt Hicks <matt@outr.com>
 */
class CaseFormExample extends Example {
  val form = DynamicTag.url[tag.Form]("case_form_example", getClass.getClassLoader.getResource("case_form.html")).create()
  val caseForm = new CaseForm[CaseFormExampleEntry](form)
  info(s"Property: ${caseForm.property()}")
  caseForm.refreshPropertyFromForm()
  info(s"Property: ${caseForm.property()}")

  contents += form
}

case class CaseFormExampleEntry(name: String, age: Int)