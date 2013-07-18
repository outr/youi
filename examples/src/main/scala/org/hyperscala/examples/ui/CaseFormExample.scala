package org.hyperscala.examples.ui

import org.hyperscala.examples.Example
import org.hyperscala.ui.dynamic.DynamicTag
import org.hyperscala.html._
import org.hyperscala.ui.form.CaseForm
import org.hyperscala.web.site.Webpage
import org.hyperscala.realtime.Realtime

/**
 * @author Matt Hicks <matt@outr.com>
 */
class CaseFormExample extends Example {
  Webpage().require(Realtime)
  Realtime.connectForm()

  val form = DynamicTag.url[tag.Form]("case_form_example", getClass.getClassLoader.getResource("case_form.html")).create()
  val caseForm = new CaseForm[CaseFormExampleEntry](form)
  caseForm.property := CaseFormExampleEntry("John Doe", 123)

  contents += form
}

case class CaseFormExampleEntry(name: String, age: Int)