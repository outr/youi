package org.hyperscala.html

import org.hyperscala.PropertyAttribute

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait AriaSupport {
  this: HTMLTag =>

  lazy val ariaActiveDescendant = PropertyAttribute[String]("aria-activedescendant", null)
  lazy val ariaAtomic = PropertyAttribute[String]("aria-atomic", null)
  lazy val ariaAutoComplete = PropertyAttribute[String]("aria-autocomplete", null)
  lazy val ariaBusy = PropertyAttribute[String]("aria-busy", null)
  lazy val ariaChecked = PropertyAttribute[String]("aria-checked", null)
  lazy val ariaControls = PropertyAttribute[String]("aria-controls", null)
  lazy val ariaDescribedBy = PropertyAttribute[String]("aria-describedby", null)
  lazy val ariaDisabled = PropertyAttribute[String]("aria-disabled", null)
  lazy val ariaDragged = PropertyAttribute[String]("aria-dragged", null)
  lazy val ariaDropEffect = PropertyAttribute[String]("aria-dropeffect", null)
  lazy val ariaExpanded = PropertyAttribute[String]("aria-expanded", null)
  lazy val ariaFlowTo = PropertyAttribute[String]("aria-flowto", null)
  lazy val ariaHasPopup = PropertyAttribute[String]("aria-haspopup", null)
  lazy val ariaHidden = PropertyAttribute[String]("aria-hidden", null)
  lazy val ariaInvalid = PropertyAttribute[String]("aria-invalid", null)
  lazy val ariaLabel = PropertyAttribute[String]("aria-label", null)
  lazy val ariaLabelledBy = PropertyAttribute[String]("aria-labelledby", null)
  lazy val ariaLevel = PropertyAttribute[String]("aria-level", null)
  lazy val ariaLive = PropertyAttribute[String]("aria-live", null)
  lazy val ariaMultiLine = PropertyAttribute[String]("aria-multiline", null)
  lazy val ariaMultiSelectable = PropertyAttribute[String]("aria-multiselectable", null)
  lazy val ariaOrientation = PropertyAttribute[String]("aria-orientation", null)
  lazy val ariaOwns = PropertyAttribute[String]("aria-owns", null)
  lazy val ariaPosInset = PropertyAttribute[String]("aria-posinset", null)
  lazy val ariaPressed = PropertyAttribute[String]("aria-pressed", null)
  lazy val ariaReadOnly = PropertyAttribute[String]("aria-readonly", null)
  lazy val ariaRelevant = PropertyAttribute[String]("aria-relevant", null)
  lazy val ariaRequired = PropertyAttribute[String]("aria-required", null)
  lazy val ariaSelected = PropertyAttribute[String]("aria-selected", null)
  lazy val ariaSetSize = PropertyAttribute[String]("aria-setsize", null)
  lazy val ariaSort = PropertyAttribute[String]("aria-sort", null)
  lazy val ariaValueMax = PropertyAttribute[String]("aria-valuemax", null)
  lazy val ariaValueMin = PropertyAttribute[String]("aria-valuemin", null)
  lazy val ariaValueNow = PropertyAttribute[String]("aria-valuenow", null)
  lazy val ariaValueText = PropertyAttribute[String]("aria-valuetext", null)
}
