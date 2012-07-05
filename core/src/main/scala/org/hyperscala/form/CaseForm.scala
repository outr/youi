package org.hyperscala.form

import org.hyperscala.tags._

import org.powerscala.reflect._
import org.powerscala.reflect.CaseValue
import org.hyperscala.js.JavaScript

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class CaseForm[T](submitLabel: String = "Submit", postURL: String, resultFunction: String)(implicit manifest: Manifest[T]) extends Form {
  id := "postForm"
  action := "/"

  val fields = manifest.erasure.caseValues.map(cv => cv.name -> new CaseField(cv)).toMap
  fields.values.foreach(f => contents += f)

  val button = new Button(id = "submitButton", buttonType = "submit", content = submitLabel)
  contents += button

  contents += new Script {
    contents += JavaScript(
      """
        |$("#postForm").submit(function(event) {
        | event.preventDefault();
        | $("#submitButton").attr("disabled", "disabled");
        | $.post("%s", $("#postForm").serialize(), function(data) {
        |   %s(data);
        |   $("#submitButton").removeAttr("disabled");
        | });
        |});
      """.stripMargin.format(postURL, resultFunction))
  }
}

class CaseField(caseValue: CaseValue) extends Div {
  val label = new Label(forElement = caseValue.name, content = caseValue.name.capitalize)
  val field = new Input(name = caseValue.name)

  contents += label
  contents += field
}