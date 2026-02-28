package youi.material

import youi.component.Component
import youi.component.support.TypedContainerSupport
import youi.dom
import youi.material.impl.MDCChipSetImplementation

class MDCChipSet extends Component(dom.create.div) with TypedContainerSupport[MDCChip] {
  classes := List("mdc-chip-set")
  element.setAttribute("role", "grid")

  private val adapter: MDCChipSetImplementation = MaterialComponents.verified(MDCChipSetImplementation.attachTo(element))
}