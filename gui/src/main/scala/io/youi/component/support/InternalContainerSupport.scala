package io.youi.component.support

import io.youi.component.Component
import io.youi.component.feature.ContainerFeature

trait InternalContainerSupport[Child <: Component] {
  this: Component =>

  protected lazy val children: ContainerFeature[Child] = new ContainerFeature[Child](this)
}