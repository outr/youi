package org.hyperscala.jquery.ui

import org.hyperscala.module._
import org.hyperscala.html.HTMLTag
import org.hyperscala.web.Webpage
import org.hyperscala.realtime.Realtime
import org.hyperscala.jquery.dsl._

/**
 * @author Matt Hicks <matt@outr.com>
 */
object jQueryUI extends Interface {
  def Latest = jQueryUI1103
  lazy val LatestWithDefault = InterfaceWithDefault(jQueryUI, Latest)

  def name = "jquery-ui"

  def tabs(t: HTMLTag) = {
    Webpage().require(LatestWithDefault)
    Realtime.send($(t).call("tabs()"))
  }

  def menu(t: HTMLTag) = {
    Webpage().require(LatestWithDefault)
    Realtime.send($(t).call("menu()"))
  }

  def datepicker(t: HTMLTag) = {
    Webpage().require(LatestWithDefault)
    Realtime.send($(t).call("datepicker()"))
  }
}
