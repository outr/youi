package io.youi.component.support

import io.youi.component.Component
import org.scalajs.dom.html
import reactify.{Val, Var}

trait ContainerSupport extends Component {
  object children {
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
      measure()
    }
    def -=(component: Component): Unit = {
      _entries @= entries.filterNot(_ eq component)
      -=(component.element)
    }
    def -=(child: html.Element): Unit = {
      element.removeChild(child)
      measure()
    }
    def ++=(seq: Seq[Component]): Unit = seq.foreach(+=)
    def --=(seq: Seq[Component]): Unit = seq.foreach(-=)
  }

  override def measure(): Unit = {
    children().foreach(_.measure())
    super.measure()
  }
}