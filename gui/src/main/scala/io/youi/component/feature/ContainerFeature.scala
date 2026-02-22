package io.youi.component.feature

import io.youi.component.Component
import io.youi.dom._
import org.scalajs.dom.{document, html}
import reactify.{Priority, Var}

class ContainerFeature[Child <: Component](val component: Component) extends Var[Vector[Child]](Vector.empty) with Feature {
  override protected def parent: FeatureParent = component

  private var verifyChanges = true

  changes {
    case (old, current) => if (verifyChanges) {
      val removed = old.diff(current)
      removed.foreach(_.element.remove())
      component.element.verifyChildrenInOrder(get.map(_.element)*)
      component.measure.trigger()
    }
  }

  private def unverified[Return](f: => Return): Return = {
    val previousState = verifyChanges
    verifyChanges = false
    try {
      f
    } finally {
      verifyChanges = previousState
    }
  }

  def clear(): Unit = unverified {
    static(Vector.empty)
    component.element.innerHTML = ""
    component.measure.trigger()
  }

  def isEmpty: Boolean = get.isEmpty
  def nonEmpty: Boolean = get.nonEmpty
  def length: Int = get.length

  def prependMultiple(seq: Seq[Child]): Unit = unverified {
    static(seq.toVector ++: get)

    // Optimization to improve insertion of many elements
    val f = document.createDocumentFragment()
    seq.foreach { c =>
      f.appendChild(c.element)
    }
    component.element.insertBefore(f, component.element.firstChild)

    component.measure.trigger()
  }
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
  def ++=(seq: Seq[Child]): Unit = unverified {
    static(get ++: seq.toVector)

    // Optimization to improve insertion of many elements
    val f = document.createDocumentFragment()
    seq.foreach { c =>
      f.appendChild(c.element)
    }
    component.element.appendChild(f)

    component.measure.trigger()
  }
  def --=(seq: Seq[Child]): Unit = static(get.filterNot(seq.contains))

  component.measure.on({
    get.foreach(_.measure.trigger())
  }, Priority.High)
}