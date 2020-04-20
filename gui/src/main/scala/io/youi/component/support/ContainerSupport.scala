package io.youi.component.support

import io.youi.component.Component
import io.youi.component.feature.ContainerFeature

trait ContainerSupport extends Component {
  lazy val children: ContainerFeature[Component] = new ContainerFeature[Component](this)
}