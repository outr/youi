package org.hyperscala.bootstrap.component

import org.hyperscala.html._
import org.hyperscala.html.constraints.BodyChild
import org.hyperscala.html.extension.{ClassProperty, ClassName}
import org.powerscala.enum.{EnumEntry, Enumerated}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Label extends tag.Span {
  def this(labelStyle: LabelStyle, content: BodyChild) = {
    this()

    this.labelStyle := labelStyle
    contents += content
  }

  clazz += "label"

  val labelStyle = new ClassProperty[LabelStyle](this, LabelStyle.Default)
}

sealed abstract class LabelStyle(val className: Option[String]) extends EnumEntry with ClassName

object LabelStyle extends Enumerated[LabelStyle] {
  case object Default extends LabelStyle(Some("label-default"))
  case object Primary extends LabelStyle(Some("label-primary"))
  case object Success extends LabelStyle(Some("label-success"))
  case object Info extends LabelStyle(Some("label-info"))
  case object Warning extends LabelStyle(Some("label-warning"))
  case object Danger extends LabelStyle(Some("label-danger"))

  val values = findValues.toVector
}