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

class WellType(val className: Option[String]) extends EnumEntry with ClassName

object WellType extends Enumerated[WellType] {
  val Default = new WellType(Some("well-default"))
  val Small = new WellType(Some("well-sm"))
  val Larger = new WellType(Some("well-lg"))
}