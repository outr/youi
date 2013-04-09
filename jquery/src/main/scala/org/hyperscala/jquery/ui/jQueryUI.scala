package org.hyperscala.jquery.ui

import org.hyperscala.module._
import org.hyperscala.html.HTMLTag
import org.hyperscala.jquery.jQuery

/**
 * @author Matt Hicks <matt@outr.com>
 */
object jQueryUI extends Interface {
  def Latest = jQueryUI1101
  lazy val LatestWithDefault = InterfaceWithDefault(jQueryUI, Latest)

  def name = "jquery-ui"

  def tabs(t: HTMLTag) = {
    jQuery.call(t, "tabs()")
  }

  def menu(t: HTMLTag) = {
    jQuery.call(t, "menu()")
  }
}
