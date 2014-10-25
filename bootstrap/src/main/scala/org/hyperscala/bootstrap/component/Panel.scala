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
  val body = new tag.Div(clazz = List("panel-body"))

  heading.contents += panelTitle
  contents.addAll(heading, body)
}

class PanelType(val className: Option[String]) extends EnumEntry with ClassName

object PanelType extends Enumerated[PanelType] {
  val Default = new PanelType(Option("panel-default"))
  val Primary = new PanelType(Option("panel-primary"))
  val Success = new PanelType(Option("panel-success"))
  val Info = new PanelType(Option("panel-info"))
  val Warning = new PanelType(Option("panel-warning"))
  val Danger = new PanelType(Option("panel-danger"))
}