package org.hyperscala.jquery.ui

import org.hyperscala.html._
import org.hyperscala.web.Webpage
import org.hyperscala.jquery.dsl._
import org.hyperscala.realtime.Realtime

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
    Realtime.send($(t).call("tooltip()"))
  }
}