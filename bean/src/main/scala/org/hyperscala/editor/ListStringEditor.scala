package org.hyperscala.editor

import org.powerscala.property.StandardProperty

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class ListStringEditor(val property: StandardProperty[List[String]]) extends ListEditor[String] {
  lazy val valueEditor = new InputEditor[String](new StandardProperty[String]("%sField".format(property.name()), ""))
  lazy val manifest = Manifest.classType[String](classOf[String])

  override def filterOut(value: String) = value.trim.length == 0 || property().contains(value)
}