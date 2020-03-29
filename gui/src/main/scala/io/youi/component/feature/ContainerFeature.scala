package io.youi.component.feature

import io.youi.component.Component
import org.scalajs.dom.html
import reactify.{Priority, Val, Var}

class ContainerFeature(component: Component) extends Feature(component) {
  private val _entries = Var(List.empty[Component])

  def entries: Val[List[Component]] = _entries

  def apply(): List[Component] = entries

  def isEmpty: Boolean = entries.isEmpty
  def nonEmpty: Boolean = entries.nonEmpty

  def length: Int = element.childElementCount
  def +=(component: Component): Unit = {
    _entries @= entries ::: List(component)
    +=(component.element)
  }
  def +=(child: html.Element): Unit = {
    element.appendChild(child)
    component.measure.trigger()
  }
  def -=(component: Component): Unit = {
    _entries @= entries.filterNot(_ eq component)
    -=(component.element)
  }
  def -=(child: html.Element): Unit = {
    element.removeChild(child)
    component.measure.trigger()
  }
  def ++=(seq: Seq[Component]): Unit = seq.foreach(+=)
  def --=(seq: Seq[Component]): Unit = seq.foreach(-=)

  component.measure.on({
    entries.foreach(_.measure.trigger())
  }, Priority.High)
}