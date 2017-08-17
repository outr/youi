package io.youi.hypertext.border

import io.youi.Color
import io.youi.hypertext.Component
import reactify.Var

class Border(component: Component,
             updateColor: String => Unit,
             updateStyle: String => Unit,
             updateWidth: String => Unit) {
  lazy val color: Var[Option[Color]] = component.prop(None, o => updateColor(o.map(_.toRGBA).getOrElse("")), mayCauseResize = false)
  lazy val style: Var[Option[BorderStyle]] = component.prop(None, o => updateStyle(o.map(_.value).getOrElse("")), mayCauseResize = true)
  lazy val size: Var[Option[Double]] = component.prop(None, o => updateWidth(o.map(d => s"${d}px").getOrElse("")), mayCauseResize = true)
}
