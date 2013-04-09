package org.hyperscala.jquery.ui

import org.hyperscala.module._
import org.hyperscala.html.HTMLTag
import org.hyperscala.jquery.jQuery
import org.hyperscala.web.site.Webpage

/**
 * @author Matt Hicks <matt@outr.com>
 */
object jQueryUI extends Interface {
  def Latest = jQueryUI1101
  lazy val LatestWithDefault = InterfaceWithDefault(jQueryUI, Latest)

  def name = "jquery-ui"

  def tabs(t: HTMLTag) = {
    Webpage().require(LatestWithDefault)
    jQuery.call(t, "tabs()")
  }

  def menu(t: HTMLTag) = {
    Webpage().require(LatestWithDefault)
    jQuery.call(t, "menu()")
  }

  def datepicker(t: HTMLTag) = {
    Webpage().require(LatestWithDefault)
    jQuery.call(t, "datepicker()")
  }
}
