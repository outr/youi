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

class ButtonStyle(val className: Option[String]) extends EnumEntry with ClassName

object ButtonStyle extends Enumerated[ButtonStyle] {
  val Default = new ButtonStyle(Some("btn-default"))
  val Primary = new ButtonStyle(Some("btn-primary"))
  val Success = new ButtonStyle(Some("btn-success"))
  val Info = new ButtonStyle(Some("btn-info"))
  val Warning = new ButtonStyle(Some("btn-warning"))
  val Danger = new ButtonStyle(Some("btn-danger"))
  val Link = new ButtonStyle(Some("btn-link"))
}

class ButtonSize(val className: Option[String]) extends EnumEntry with ClassName

object ButtonSize extends Enumerated[ButtonSize] {
  val Default = new ButtonSize(None)
  val Large = new ButtonSize(Some("btn-lg"))
  val Small = new ButtonSize(Some("btn-sm"))
  val ExtraSmall = new ButtonSize(Some("btn-xs"))
}