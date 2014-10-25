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

class LabelStyle(val className: Option[String]) extends EnumEntry with ClassName

object LabelStyle extends Enumerated[LabelStyle] {
  val Default = new LabelStyle(Some("label-default"))
  val Primary = new LabelStyle(Some("label-primary"))
  val Success = new LabelStyle(Some("label-success"))
  val Info = new LabelStyle(Some("label-info"))
  val Warning = new LabelStyle(Some("label-warning"))
  val Danger = new LabelStyle(Some("label-danger"))
}