package youi.component

import youi.component.support.ContainerSupport
import youi.dom
import org.scalajs.dom.html

class Container(element: html.Element = dom.create.div) extends Component(element) with ContainerSupport

object Container {
  def apply(children: Component*): Container = {
    val container = new Container
    container.children ++= children
    container
  }
}