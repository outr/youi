package org.hyperscala.bootstrap.component

import org.hyperscala.html._
import org.hyperscala.html.constraints.BodyChild
import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Panel(title: BodyChild, content: Option[BodyChild] = None, panelType: PanelType = PanelType.Default) extends tag.Div(clazz = List("panel", panelType.className)) {
  val heading = new tag.Div(clazz = List("panel-heading"))
  val body = new tag.Div(clazz = List("panel-body"))

  heading.contents += new tag.H3(clazz = List("panel-title"), content = title)
  content match {
    case Some(c) => body.contents += c
    case None => // Nothing
  }

  contents.addAll(heading, body)
}

class PanelType(val className: String) extends EnumEntry

object PanelType extends Enumerated[PanelType] {
  val Default = new PanelType("panel-default")
  val Primary = new PanelType("panel-primary")
  val Success = new PanelType("panel-success")
  val Info = new PanelType("panel-info")
  val Warning = new PanelType("panel-warning")
  val Danger = new PanelType("panel-danger")
}