package org.hyperscala.bootstrap.component.extra

import org.hyperscala.html._
import org.hyperscala.html.constraints.BodyChild
import org.hyperscala.bootstrap.component._

class PanelFactory(title: BodyChild,
            content: Option[BodyChild] = None,
            thatPanelType: PanelType = PanelType.Default) extends Panel {
  panelType := thatPanelType

  panelTitle.contents += title
  content.foreach(body.contents += _)

  val heading = new PanelHeading
  val panelTitle = new PanelTitle
  val outer = new tag.Div
  val body = new PanelBody

  heading.contents += panelTitle
  outer.contents += body
  contents.addAll(heading, outer)
}
