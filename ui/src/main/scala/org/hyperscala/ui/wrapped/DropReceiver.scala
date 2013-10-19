package org.hyperscala.ui.wrapped

import org.hyperscala.module.Module
import org.powerscala.{Version, StorageComponent}
import org.hyperscala.html._
import org.hyperscala.jquery.jQuery
import org.hyperscala.realtime.Realtime
import org.hyperscala.web.{Webpage, Website}
import org.hyperscala.web.WrappedComponent
import org.powerscala.event.processor.UnitProcessor
import org.powerscala.event.{Intercept, Listenable}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class DropReceiver private(val wrapped: HTMLTag) extends WrappedComponent[HTMLTag] with Listenable {
  init()

  private def init() = {
    wrapped.eventReceived.on {
      case evt if evt.event == "dropped" => {
        val types = evt.message[List[String]]("types")
        val data = evt.message[Map[String, String]]("data").map {
          case (key, value) => DropData(key, value.toString)
        }.toList
        dropped.fire(DropReceived(types, data))

        Intercept.Stop
      }
      case _ => Intercept.Continue
    }
  }

  protected def initializeComponent(values: Map[String, Any]) = {
    Realtime.sendJavaScript(s"createDropReceiver('${wrapped.identity}');", onlyRealtime = false)
    values.foreach {
      case (key, value) => modify(key, value)
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
  def name = "DropReceiver"
  def version = Version(1)

  override def dependencies = List(jQuery.LatestWithDefault, Realtime)

  def init() = {
    Website().register("/js/drop_receiver.js", "drop_receiver.js")
  }

  def load() = {
    Webpage().head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/drop_receiver.js")
  }

  override def apply(t: HTMLTag) = {
    Webpage().require(this)
    super.apply(t)
  }

  protected def create(t: HTMLTag) = new DropReceiver(t)
}

case class DropReceived(types: List[String], data: List[DropData]) {
  def get(mimeType: String) = data.find(dd => dd.mimeType == mimeType).map(dd => dd.data)
}

case class DropData(mimeType: String, data: String)