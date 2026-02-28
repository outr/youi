package youi.component

import youi.component.feature.ContainerFeature
import youi.dom
import org.scalajs.dom.html

class TypedContainer[Child <: Component](element: html.Element = dom.create.div) extends Component(element) {
  lazy val children: ContainerFeature[Child] = new ContainerFeature[Child](this)
}
