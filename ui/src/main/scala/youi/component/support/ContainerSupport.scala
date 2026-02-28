package youi.component.support

import youi.component.Component
import youi.component.feature.ContainerFeature

trait ContainerSupport extends Component {
  lazy val children: ContainerFeature[Component] = new ContainerFeature[Component](this)
}