package org.hyperscala.bootstrap.component

import org.hyperscala.html._
import org.hyperscala.html.extension.{ClassName, ClassProperty}
import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Well extends tag.Div {
  def this(wellType: WellType) = {
    this()
    this.wellType := wellType
  }

  clazz += "well"

  val wellType = new ClassProperty[WellType](this, WellType.Default)
}

sealed abstract class WellType(val className: Option[String]) extends EnumEntry with ClassName

object WellType extends Enumerated[WellType] {
  case object Default extends WellType(Some("well-default"))
  case object Small extends WellType(Some("well-sm"))
  case object Larger extends WellType(Some("well-lg"))

  val values = findValues.toVector
}