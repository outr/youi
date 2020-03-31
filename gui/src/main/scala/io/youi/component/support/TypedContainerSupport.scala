package io.youi.component.support

import io.youi.component.Component
import io.youi.component.feature.ContainerFeature

trait TypedContainerSupport[Child <: Component] extends Component {
  lazy val children: ContainerFeature[Child] = new ContainerFeature[Child](this)
}