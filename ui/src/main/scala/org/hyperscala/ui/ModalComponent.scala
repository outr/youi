package org.hyperscala.ui

import com.outr.net.http.session.Session
import org.hyperscala.event.ClickEvent
import org.hyperscala.html._
import org.hyperscala.jquery.dsl.jQuerySelector
import org.hyperscala.jquery.jQueryFracs
import org.hyperscala.module.Module
import org.hyperscala.realtime._
import org.hyperscala.selector.Selector
import org.hyperscala.web.{Webpage, Website}
import org.powerscala.Version
import org.powerscala.event.Listenable
import org.powerscala.event.processor.UnitProcessor
import org.powerscala.property.Property

/**
 * @author Matt Hicks <matt@outr.com>
 */
object ModalComponent extends Module {
  val name = "modalComponent"
  val version = Version(1)

  override def dependencies = List(Realtime, jQueryFracs)

  override def init[S <: Session](website: Website[S]) = {
    website.register("/js/modal_component.js", "modal_component.js")
    website.register("/css/modal_component.css", "modal_component.css")
  }

  override def load[S <: Session](webpage: Webpage[S]) = {
    webpage.head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/modal_component.js")
    webpage.head.contents += new tag.Link(href = "/css/modal_component.css")
    apply(webpage)
  }

  def apply[S <: Session](webpage: Webpage[S]) = webpage.synchronized {
    webpage.store.getOrSet(name, new ModalComponent(webpage))
  }
}

class ModalComponent(webpage: Webpage[_ <: Session]) extends Listenable {
  val left = new tag.Div(id = "modal_left", clazz = List("modal_component"))
  val right = new tag.Div(id = "modal_right", clazz = List("modal_component"))
  val top = new tag.Div(id = "modal_top", clazz = List("modal_component"))
  val bottom = new tag.Div(id = "modal_bottom", clazz = List("modal_component"))

  val modalClicked = new UnitProcessor[ClickEvent]("modalClicked")

  left.clickEvent.onRealtime {
    case evt => modalClicked.fire(evt)
  }
  right.clickEvent.onRealtime {
    case evt => modalClicked.fire(evt)
  }
  top.clickEvent.onRealtime {
    case evt => modalClicked.fire(evt)
  }
  bottom.clickEvent.onRealtime {
    case evt => modalClicked.fire(evt)
  }

//  webpage.body.contents.addAll(left, right, top, bottom)
  webpage.body.contents += left
  webpage.body.contents += right
  webpage.body.contents += top
  webpage.body.contents += bottom

  val selected = Property[Selector]()
  selected.change.on {
    case evt => {
      val selector = evt.newValue match {
        case null => null
        case s => new jQuerySelector(s).content
      }
      Realtime.sendJavaScript(webpage, s"modal($selector);")
    }
  }
}