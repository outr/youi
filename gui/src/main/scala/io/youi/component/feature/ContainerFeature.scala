package io.youi.component.feature

import io.youi.component.Component
import io.youi.dom._
import io.youi.theme.Theme
import org.scalajs.dom.html
import reactify.{Priority, Val, Var}

import scala.scalajs.js.|

class ContainerFeature[Child <: Component](val component: Component) extends Feature {
  override protected def parent: FeatureParent = component

  private val _entries = Var(List.empty[Child])

  def entries: Val[List[Child]] = _entries

  def apply(): List[Child] = entries

  def isEmpty: Boolean = entries.isEmpty
  def nonEmpty: Boolean = entries.nonEmpty

  def clear(): Unit = {
    component.element.innerHTML = ""
    _entries @= Nil
  }

  def length: Int = component.element.childElementCount
  def prepend(child: Child): Unit = {
    _entries @= child :: entries
    prepend(child.element)
  }
  def prepend(child: html.Element): Unit = {
    child.insertFirst(component.element)
    component.measure.trigger()
  }
  def replace(current: Child, replacement: Child): Unit = {
    _entries @= entries.map {
      case c if c eq current => replacement
      case c => c
    }
    replace(current.element, replacement.element)
  }
  def replace(current: html.Element, replacement: html.Element): Unit = {
    component.element.replaceChild(replacement, current)
    component.measure.trigger()
  }
  def +=(component: Child): Unit = {
    _entries @= entries ::: List(component)
    +=(component.element)
  }
  def +=(child: html.Element): Unit = {
    component.element.appendChild(child)
    component.measure.trigger()
  }
  def -=(component: Child): Unit = {
    _entries @= entries.filterNot(_ eq component)
    -=(component.element)
  }
  def -=(child: html.Element): Unit = {
    component.element.removeChild(child)
    component.measure.trigger()
  }
  def ++=(seq: Seq[Child]): Unit = seq.foreach(+=)
  def --=(seq: Seq[Child]): Unit = seq.foreach(-=)

  component.measure.on({
    entries.foreach(_.measure.trigger())
  }, Priority.High)
}