package io.youi.component

import io.youi.component.support.{GridSupport, TypedContainerSupport}
import io.youi.dom
import org.scalajs.dom.html

class Grid[Child <: Component](element: html.Element = dom.create.div) extends Component(element) with TypedContainerSupport[Child] with GridSupport[Child]