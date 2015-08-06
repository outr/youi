package org.hyperscala.bootstrap.component

import org.hyperscala.html._
import org.hyperscala.html.extension.{ClassProperty, ClassName}
import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Panel extends tag.Div {
  val panelType = new ClassProperty[PanelType](this, PanelType.Default)
  clazz += "panel"
}

class PanelHeading extends tag.Div(clazz = List("panel-heading"))
class PanelTitle extends tag.H3(clazz = List("panel-title"))
class PanelBody extends tag.Div(clazz = List("panel-body"))

sealed abstract class PanelType(val className: Option[String]) extends EnumEntry with ClassName

object PanelType extends Enumerated[PanelType] {
  case object Default extends PanelType(Option("panel-default"))
  case object Primary extends PanelType(Option("panel-primary"))
  case object Success extends PanelType(Option("panel-success"))
  case object Info extends PanelType(Option("panel-info"))
  case object Warning extends PanelType(Option("panel-warning"))
  case object Danger extends PanelType(Option("panel-danger"))

  val values = findValues.toVector
}