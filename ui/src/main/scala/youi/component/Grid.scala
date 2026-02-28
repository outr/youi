package youi.component

import youi.component.support.{GridSupport, TypedContainerSupport}
import youi.dom
import org.scalajs.dom.html

class Grid[Child <: Component](element: html.Element = dom.create.div) extends Component(element) with TypedContainerSupport[Child] with GridSupport[Child]