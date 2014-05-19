package org.hyperscala.jquery.ui

import org.hyperscala.html._
import org.hyperscala.web._
import org.hyperscala.jquery.dsl._
import org.hyperscala.realtime.Realtime
import org.hyperscala.selector.Selector
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
trait DatePicker extends HTMLTag {
  identity        // Make sure it has an id

  this.require(jQueryUI, jQueryUI.Latest)

  override protected def initialize() {
    super.initialize()

    DatePicker(this)
  }
}

object DatePicker {
  def apply(t: HTMLTag) = {
    t.require(jQueryUI, jQueryUI.Latest)

    t.connected[Webpage[_ <: Session]] {
      case webpage => Realtime.send(webpage, $(t).call("datepicker()"), Some(Selector.id(t)))
    }
  }
}