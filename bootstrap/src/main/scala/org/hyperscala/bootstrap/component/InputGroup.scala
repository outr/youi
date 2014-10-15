package org.hyperscala.bootstrap.component

import org.hyperscala.html._
import org.powerscala.enum.{Enumerated, EnumEntry}
import org.hyperscala.html.constraints.BodyChild

/**
 * @author Matt Hicks <matt@outr.com>
 */
class InputGroup extends tag.Div(clazz = List("input-group")) with BootstrapComponent {
  val size = classProp(InputGroupSize.Default)

  def addOn(content: BodyChild) = contents += new tag.Span(clazz = List("input-group-addon"), content = content)
}

class InputGroupSize(val className: String) extends ClassNameEnumEntry

object InputGroupSize extends Enumerated[InputGroupSize] {
  val Default = new InputGroupSize("")
  val Large = new InputGroupSize("input-group-lg")
  val Small = new InputGroupSize("input-group-sm")
}