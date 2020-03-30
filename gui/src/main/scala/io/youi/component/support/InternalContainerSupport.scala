package io.youi.component.support

import io.youi.component.Component
import io.youi.component.feature.ContainerFeature

trait InternalContainerSupport {
  this: Component =>

  protected lazy val children: ContainerFeature[Component] = new ContainerFeature[Component](this)
}