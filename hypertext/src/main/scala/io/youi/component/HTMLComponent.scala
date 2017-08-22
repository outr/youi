package io.youi.component

import io.youi._
import io.youi.theme.HTMLComponentTheme
import reactify.Var

import org.scalajs.dom._

class HTMLComponent[C <: hypertext.Component](val component: C) extends Component {
  override lazy val theme: Var[HTMLComponentTheme] = Var(HTMLComponent)

  size.measured.width := component.size.actual.width
  size.measured.height := component.size.actual.height
  component.visible := globalVisibility
  if (Option(component.element.parentElement).isEmpty) {
    document.body.appendChild(component.element)
    delta.attach(component.update)
  }

  override def update(delta: Double): Unit = {
    super.update(delta)

    if (globalVisibility()) {   // TODO: change this to on-demand
      val m = matrix.world
      component.element.style.transform = s"matrix(${m.m00}, ${m.m10}, ${m.m01}, ${m.m11}, ${m.m02}, ${m.m12})"
    }
  }
}

object HTMLComponent extends HTMLComponentTheme