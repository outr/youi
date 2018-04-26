package io.youi.component

import io.youi.component.extras.HTMLComponent
import io.youi.layout.Layout
import org.scalajs.dom.html
import reactify.Var

abstract class HTMLContainer[Child <: Component](override protected val element: html.Element) extends HTMLComponent[html.Element] with AbstractContainer[Child] {
  override lazy val children: Var[Vector[Child]] = Var(Vector.empty)
  override lazy val layout: Var[Layout] = Var(Layout.None)

  override protected def measuredWidth: Double = children.foldLeft(0.0)((max, child) => math.max(max, child.position.right()))

  override protected def measuredHeight: Double = children.foldLeft(0.0)((max, child) => math.max(max, child.position.bottom()))
}