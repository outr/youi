package org.hyperscala.bootstrap.component

import org.hyperscala.html._
import org.hyperscala.html.extension.{ClassName, ClassProperty}
import org.powerscala.enum.{Enumerated, EnumEntry}
import org.hyperscala.html.constraints.BodyChild

/**
 * @author Matt Hicks <matt@outr.com>
 */
class InputGroup extends tag.Div {
  clazz += "input-group"
  val size = new ClassProperty[InputGroupSize](this, InputGroupSize.Default)
}

class InputGroupAddon extends tag.Span(clazz = List("input-group-addon"))

sealed abstract class InputGroupSize(val className: Option[String]) extends EnumEntry with ClassName

object InputGroupSize extends Enumerated[InputGroupSize] {
  case object Default extends InputGroupSize(None)
  case object Large extends InputGroupSize(Some("input-group-lg"))
  case object Small extends InputGroupSize(Some("input-group-sm"))

  val values = findValues.toVector
}