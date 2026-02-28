package youi.component.support

import youi.component.Component
import youi.component.feature.ContainerFeature

trait TypedContainerSupport[Child <: Component] extends Component {
  lazy val children: ContainerFeature[Child] = new ContainerFeature[Child](this)
}