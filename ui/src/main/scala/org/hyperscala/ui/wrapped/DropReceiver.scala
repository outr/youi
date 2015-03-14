package org.hyperscala.ui.wrapped

import com.outr.net.http.session.Session
import org.hyperscala.event.BrowserEvent
import org.hyperscala.html._
import org.hyperscala.jquery.jQuery
import org.hyperscala.module.Module
import org.hyperscala.realtime.Realtime
import org.hyperscala.javascript.dsl._
import org.hyperscala.web.{WrappedComponent, _}
import org.powerscala.event.processor.UnitProcessor
import org.powerscala.event.{Intercept, Listenable}
import org.powerscala.json.TypedSupport
import org.powerscala.{StorageComponent, Version}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class DropReceiver private(val wrapped: HTMLTag, val autoInit: Boolean = true) extends WrappedComponent[HTMLTag] with Listenable {
  initReceiver()

  private def initReceiver() = {
    wrapped.handle[DropReceived] {
      case evt => dropped.fire(evt)
    }
  }

  protected def initializeComponent(values: Map[String, Any]) = wrapped.connected[Webpage] {
    case webpage => {
      webpage.eval(s"createDropReceiver('${wrapped.identity}');")
      values.foreach {
        case (key, value) => modify(key, value)
      }
    }
  }

  /**
   * Invoked when a a drop is received in the browser.
   */
  val dropped = new UnitProcessor[DropReceived]("dropped")

  /**
   * Receive Types defines the types of data that is acceptable during a drop.
   *
   * Defaults to List("text/plain", "text/html", "text/uri-list")
   */
  val receiveTypes = wrapped.dataWrapper[List[String]]("receive-types", List("text/plain", "text/html", "text/uri-list")) {
    case list => list.mkString(", ")
  }

  protected def modify(key: String, value: Any) = println(s"*** Modify: $key = $value")
}

object DropReceiver extends Module with StorageComponent[DropReceiver, HTMLTag] {
  TypedSupport.register("dropReceived", classOf[DropReceived])
  TypedSupport.register("dropData", classOf[DropData])

  def name = "DropReceiver"
  def version = Version(1)

  override def dependencies = List(jQuery, Realtime)

  override def init(website: Website) = {
    website.register("/js/drop_receiver.js", "drop_receiver.js")
  }

  override def load(webpage: Webpage) = {
    webpage.head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/drop_receiver.js")
  }

  override def apply(t: HTMLTag) = {
    t.require(this)
    super.apply(t)
  }

  protected def create(t: HTMLTag) = new DropReceiver(t)
}

case class DropReceived(tag: HTMLTag, types: List[String], data: List[DropData]) extends BrowserEvent {
  def get(mimeType: String) = data.find(dd => dd.mimeType == mimeType).map(dd => dd.data)
}

case class DropData(mimeType: String, data: String)