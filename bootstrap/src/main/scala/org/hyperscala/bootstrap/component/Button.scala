package org.hyperscala.bootstrap.component

import org.hyperscala.html._
import org.hyperscala.html.extension.{ClassBooleanProperty, ClassProperty, ClassName}
import org.powerscala.enum.{Enumerated, EnumEntry}
import org.powerscala.property.Property

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Button extends tag.Button {
  clazz += "btn"

  val buttonStyle = new ClassProperty[ButtonStyle](this, ButtonStyle.Default)
  val buttonSize = new ClassProperty[ButtonSize](this, ButtonSize.Default)
  val block = new ClassBooleanProperty(this, enabled = Some("btn-block"))
  val active = new ClassBooleanProperty(this, enabled = Some("active"))

  def this(label: String, buttonStyle: ButtonStyle = ButtonStyle.Default, buttonSize: ButtonSize = ButtonSize.Default, block: Boolean = false, active: Boolean = false) = {
    this()
    contents += label
    this.buttonStyle := buttonStyle
    this.buttonSize := buttonSize
    this.block := block
    this.active := active
  }
}

sealed abstract class ButtonStyle(val className: Option[String]) extends EnumEntry with ClassName

object ButtonStyle extends Enumerated[ButtonStyle] {
  case object Default extends ButtonStyle(Some("btn-default"))
  case object Primary extends ButtonStyle(Some("btn-primary"))
  case object Success extends ButtonStyle(Some("btn-success"))
  case object Info extends ButtonStyle(Some("btn-info"))
  case object Warning extends ButtonStyle(Some("btn-warning"))
  case object Danger extends ButtonStyle(Some("btn-danger"))
  case object Link extends ButtonStyle(Some("btn-link"))

  val values = findValues.toVector
}

sealed abstract class ButtonSize(val className: Option[String]) extends EnumEntry with ClassName

object ButtonSize extends Enumerated[ButtonSize] {
  case object Default extends ButtonSize(None)
  case object Large extends ButtonSize(Some("btn-lg"))
  case object Small extends ButtonSize(Some("btn-sm"))
  case object ExtraSmall extends ButtonSize(Some("btn-xs"))

  val values = findValues.toVector
}