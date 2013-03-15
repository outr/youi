package org.hyperscala.jquery.ui

import org.hyperscala.html._
import org.hyperscala.web.site.Webpage
import org.hyperscala.jquery.jQuery

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait Tooltip extends HTMLTag {
  identity        // Make sure it has an id

  Webpage().require(jQueryUI, jQueryUI.Latest)

  override protected def initialize() {
    super.initialize()

    Tooltip(this)
  }
}

object Tooltip {
  def apply(t: HTMLTag) = {
    Webpage().require(jQueryUI, jQueryUI.Latest)
    jQuery.call(t, "tooltip()")
  }
}