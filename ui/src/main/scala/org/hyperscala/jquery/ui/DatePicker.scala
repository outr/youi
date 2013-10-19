package org.hyperscala.jquery.ui

import org.hyperscala.html._
import org.hyperscala.web.Webpage
import org.hyperscala.jquery.jQuery

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
trait DatePicker extends HTMLTag {
  identity        // Make sure it has an id

  Webpage().require(jQueryUI, jQueryUI.Latest)

  override protected def initialize() {
    super.initialize()

    DatePicker(this)
  }
}

object DatePicker {
  def apply(t: HTMLTag) = {
    Webpage().require(jQueryUI, jQueryUI.Latest)

    jQuery.call(t, "datepicker()")
  }
}
