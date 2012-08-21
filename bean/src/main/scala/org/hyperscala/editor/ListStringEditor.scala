package org.hyperscala.editor

import org.powerscala.property.StandardProperty
import org.hyperscala.javascript.JavaScriptString

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class ListStringEditor(val property: StandardProperty[List[String]]) extends ListEditor[String] {
  lazy val inputName = "%sField".format(property.name())
  lazy val valueEditor = new InputEditor[String](new StandardProperty[String](inputName, ""))
  lazy val manifest = Manifest.classType[String](classOf[String])

  override def filterOut(value: String) = value == null || value.trim.length == 0 || super.filterOut(value)

  button.event.click := JavaScriptString("$('#%s').focus(); $(this).closest('form').submit();".format(inputName))
}