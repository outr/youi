package io.youi.material

import io.youi.component.Component
import io.youi.dom
import io.youi.material.impl.MDCChipSetImplementation

class MDCChipSet extends Component(dom.create.div) {
  classes := Set("mdc-chip-set")
  element.setAttribute("role", "grid")

  object children {
    def length: Int = element.childElementCount
    def +=(chip: MDCChip): Unit = {
      element.appendChild(chip.element)
      measure.trigger()
    }
    def -=(chip: MDCChip): Unit = {
      element.removeChild(chip.element)
      measure.trigger()
    }
    def ++=(seq: Seq[MDCChip]): Unit = seq.foreach(+=)
    def --=(seq: Seq[MDCChip]): Unit = seq.foreach(-=)
  }

  private val adapter: MDCChipSetImplementation = MaterialComponents.verified(MDCChipSetImplementation.attachTo(element))
}
