package io.youi.component.feature

import io.youi.component.Component
import io.youi.dom._
import org.scalajs.dom.html
import reactify.{Priority, Var}

class ContainerFeature[Child <: Component](val component: Component) extends Var[Vector[Child]](Vector.empty) with Feature {
  override protected def parent: FeatureParent = component

  changes {
    case (old, current) => {
      val removed = old.diff(current)
      removed.foreach(_.element.remove())
      component.element.verifyChildrenInOrder(get.map(_.element): _*)
      component.measure.trigger()
    }
  }

  def clear(): Unit = {
    static(Vector.empty)
    component.element.innerHTML = ""
  }

  def isEmpty: Boolean = get.isEmpty
  def nonEmpty: Boolean = get.nonEmpty
  def length: Int = get.length

  def prepend(child: Child): Unit = static(child +: get)
  def prepend(child: html.Element): Unit = {
    child.insertFirst(component.element)
    component.measure.trigger()
  }
  def replace(current: Child, replacement: Child): Unit = {
    static(get.map {
      case c if c eq current => replacement
      case c => c
    })
  }
  def replace(current: html.Element, replacement: html.Element): Unit = {
    component.element.replaceChild(replacement, current)
    component.measure.trigger()
  }
  def +=(component: Child): Unit = static(get :+ component)
  def +=(child: html.Element): Unit = {
    component.element.appendChild(child)
    component.measure.trigger()
  }
  def -=(component: Child): Unit = static(get.filterNot(_ eq component))
  def -=(child: html.Element): Unit = {
    component.element.removeChild(child)
    component.measure.trigger()
  }
  def ++=(seq: Seq[Child]): Unit = static(get ++: seq.toVector)
  def --=(seq: Seq[Child]): Unit = static(get.filterNot(seq.contains))

  component.measure.on({
    get.foreach(_.measure.trigger())
  }, Priority.High)
}