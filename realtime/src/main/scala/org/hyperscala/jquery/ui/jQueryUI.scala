package org.hyperscala.jquery.ui

import org.hyperscala.module._
import org.hyperscala.html.HTMLTag
import org.hyperscala.web._
import org.hyperscala.realtime.Realtime
import org.hyperscala.jquery.dsl._
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <matt@outr.com>
 */
object jQueryUI extends Interface {
  def Latest = jQueryUI1103
  lazy val LatestWithDefault = InterfaceWithDefault(jQueryUI, Latest)

  def name = "jquery-ui"

  def tabs(t: HTMLTag) = {
    t.require(LatestWithDefault)
    t.connected[Webpage[_ <: Session]] {
      case webpage => Realtime.send(webpage, $(t).call("tabs()"))
    }
  }

  def menu(t: HTMLTag) = {
    t.require(LatestWithDefault)
    t.connected[Webpage[_ <: Session]] {
      case webpage => Realtime.send(webpage, $(t).call("menu()"))
    }
  }

  def datepicker(t: HTMLTag) = {
    t.require(LatestWithDefault)
    t.connected[Webpage[_ <: Session]] {
      case webpage => Realtime.send(webpage, $(t).call("datepicker()"))
    }
  }
}
