package youi.component.support

import youi.component.Component
import youi.component.feature.ContainerFeature

trait InternalContainerSupport[Child <: Component] {
  this: Component =>

  protected lazy val children: ContainerFeature[Child] = new ContainerFeature[Child](this)
}