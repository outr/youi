package org.hyperscala.editor

import org.powerscala.property.StandardProperty
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.web.HTMLPage
import org.hyperscala.web.live.LivePage

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class ListStringEditor(val property: StandardProperty[List[String]]) extends ListEditor[String] {
  lazy val inputName = "%sField".format(property.name())
  lazy val valueEditor = new InputEditor[String](new StandardProperty[String](inputName, ""))
  val manifest = implicitly[Manifest[List[String]]]

  override def filterOut(value: String) = value == null || value.trim.length == 0 || super.filterOut(value)

  if (!HTMLPage().isInstanceOf[LivePage]) {
    button.event.click := JavaScriptString("$('#%s').focus(); $(this).closest('form').submit();".format(inputName))
  }
}