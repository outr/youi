package io.youi.component.feature

import io.youi.component.Component
import org.scalajs.dom.html
import reactify.{Priority, Val, Var}

class ContainerFeature[Child <: Component](component: Component) extends Feature(component) {
  private val _entries = Var(List.empty[Child])

  def entries: Val[List[Child]] = _entries

  def apply(): List[Child] = entries

  def isEmpty: Boolean = entries.isEmpty
  def nonEmpty: Boolean = entries.nonEmpty

  def clear(): Unit = {
    element.innerHTML = ""
    _entries @= Nil
  }

  def length: Int = element.childElementCount
  def +=(component: Child): Unit = {
    _entries @= entries ::: List(component)
    +=(component.element)
  }
  def +=(child: html.Element): Unit = {
    element.appendChild(child)
    component.measure.trigger()
  }
  def -=(component: Child): Unit = {
    _entries @= entries.filterNot(_ eq component)
    -=(component.element)
  }
  def -=(child: html.Element): Unit = {
    element.removeChild(child)
    component.measure.trigger()
  }
  def ++=(seq: Seq[Child]): Unit = seq.foreach(+=)
  def --=(seq: Seq[Child]): Unit = seq.foreach(-=)

  component.measure.on({
    entries.foreach(_.measure.trigger())
  }, Priority.High)
}