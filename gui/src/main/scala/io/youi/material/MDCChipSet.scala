package io.youi.material

import io.youi.component.Component
import io.youi.component.support.TypedContainerSupport
import io.youi.dom
import io.youi.material.impl.MDCChipSetImplementation

class MDCChipSet extends Component(dom.create.div) with TypedContainerSupport[MDCChip] {
  classes := Set("mdc-chip-set")
  element.setAttribute("role", "grid")

  private val adapter: MDCChipSetImplementation = MaterialComponents.verified(MDCChipSetImplementation.attachTo(element))
}