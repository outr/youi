package org.hyperscala.bootstrap.component

import org.hyperscala.html._
import org.hyperscala.html.constraints.BodyChild
import org.hyperscala.html.extension.{ClassProperty, ClassName}
import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Panel extends tag.Div {
  def this(title: BodyChild, content: Option[BodyChild] = None, panelType: PanelType = PanelType.Default) = {
    this()

    this.panelType := panelType
    panelTitle.contents += title
    content match {
      case Some(c) => body.contents += c
      case None => // Ignore
    }
  }

  clazz += "panel"

  val panelType = new ClassProperty[PanelType](this, PanelType.Default)

  val heading = new tag.Div(clazz = List("panel-heading"))
  val panelTitle = new tag.H3(clazz = List("panel-title"))
  val outer = new tag.Div
  val body = new tag.Div(clazz = List("panel-body"))

  heading.contents += panelTitle
  outer.contents += body
  contents.addAll(heading, outer)
}

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