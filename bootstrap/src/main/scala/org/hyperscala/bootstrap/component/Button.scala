package org.hyperscala.bootstrap.component

import org.hyperscala.html._
import org.powerscala.enum.{Enumerated, EnumEntry}
import org.powerscala.property.Property

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Button extends tag.Button {
  val buttonStyle = Property[ButtonStyle](default = Some(ButtonStyle.Default))
  val buttonSize = Property[ButtonSize](default = Some(ButtonSize.Default))
  val block = Property[Boolean](default = Some(false))
  val active = Property[Boolean](default = Some(false))

  buttonStyle.change.on {
    case evt => updateClasses()
  }
  buttonSize.change.on {
    case evt => updateClasses()
  }
  block.change.on {
    case evt => updateClasses()
  }
  active.change.on {
    case evt => updateClasses()
  }

  updateClasses()

  def this(label: String, buttonStyle: ButtonStyle = ButtonStyle.Default, buttonSize: ButtonSize = ButtonSize.Default, block: Boolean = false, active: Boolean = false) = {
    this()
    contents += label
    this.buttonStyle := buttonStyle
    this.buttonSize := buttonSize
    this.block := block
    this.active := active
  }

  def updateClasses() = {
    var classes = clazz().filterNot(s => s.startsWith("btn-") || s.startsWith("active")).toList
    classes = "btn" :: classes
    if (buttonStyle().className != "") classes = buttonStyle().className :: classes
    if (buttonSize().className != "") classes = buttonSize().className :: classes
    if (block()) classes = "btn-block" :: classes
    if (active()) classes = "active" :: classes
    clazz := classes
  }
}

class ButtonStyle(val className: String) extends EnumEntry

object ButtonStyle extends Enumerated[ButtonStyle] {
  val Default = new ButtonStyle("btn-default")
  val Primary = new ButtonStyle("btn-primary")
  val Success = new ButtonStyle("btn-success")
  val Info = new ButtonStyle("btn-info")
  val Warning = new ButtonStyle("btn-warning")
  val Danger = new ButtonStyle("btn-danger")
  val Link = new ButtonStyle("btn-link")
}

class ButtonSize(val className: String) extends EnumEntry

object ButtonSize extends Enumerated[ButtonSize] {
  val Default = new ButtonSize("")
  val Large = new ButtonSize("btn-lg")
  val Small = new ButtonSize("btn-sm")
  val ExtraSmall = new ButtonSize("btn-xs")
}