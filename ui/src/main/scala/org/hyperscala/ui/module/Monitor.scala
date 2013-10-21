package org.hyperscala.ui.module

import org.hyperscala.module.Module
import org.powerscala.Version
import org.hyperscala.realtime.{RealtimePage, Realtime}
import org.hyperscala.web.{Webpage, Website}
import org.hyperscala.html.{HTMLTag, tag}
import org.hyperscala.javascript.JSFunction0
import org.powerscala.property.Property
import org.hyperscala.{PropertyAttribute, Message, Unique}
import org.hyperscala.persistence.ValuePersistence
import org.hyperscala.css.StyleSheetAttribute

import org.hyperscala.realtime.dsl._

/**
 * Monitor allows arbitrary JavaScript to be monitored for a changing result at a specific interval and to send that data
 * back to the server when it changes. This utilizes batch sends to avoid extraneous information but causes some
 * intermediate data to be potentially lost.
 *
 * @author Matt Hicks <matt@outr.com>
 */
object Monitor extends Module {
  val name = "monitor"
  val version = Version(1)

  override def dependencies = List(Realtime)

  def init() = {
    Website().register("/js/monitor.js", "monitor.js")
  }

  def load() = {
    Webpage().head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/monitor.js")
    val div = apply()
    Webpage().body.contents += div
  }

  private def apply() = {
    Webpage().store.getOrSet("monitor_div", new MonitorDiv)
  }

  def create[T](frequency: Double, evaluator: JSFunction0[T])
               (implicit manifest: Manifest[T], converter: ValuePersistence[T]) = {
    apply().create[T](frequency, evaluator)
  }

  def remove[T](monitor: Monitor[T]) = {
    apply().remove[T](monitor)
  }

  def sync[T](attribute: PropertyAttribute[T], frequency: Double) = {
    val function = attribute match {
      case ssa: StyleSheetAttribute[T] => {
        val t = ssa.ss.hierarchicalParent.asInstanceOf[HTMLTag]
        onCSS(t, ssa)
      }
      case _ => {
        val t = attribute.parent.asInstanceOf[HTMLTag]
        onAttribute(t, attribute)
      }
    }
    val monitor = create[T](frequency, function)(attribute.manifest, attribute.persister)
    val property = monitor.property
    property.change.on {
      case evt => RealtimePage.ignoringChange(attribute, property())
    }
    monitor
  }

  private class MonitorDiv extends tag.Div(id = "realtime_monitor") {
    private var map = Map.empty[String, Monitor[_]]

    def create[T](frequency: Double, evaluator: JSFunction0[T])
                 (implicit manifest: Manifest[T], converter: ValuePersistence[T]) = {
      val id = Unique()
      val monitor = new Monitor[T](id, frequency, evaluator)
      synchronized {
        map += id -> monitor
      }
      val f = math.round(frequency * 1000.0)
      Realtime.sendJavaScript(s"window.monitor.createMonitor('$id', $f, ${evaluator.content});", onlyRealtime = false)
      monitor
    }

    def remove[T](monitor: Monitor[T]) = synchronized {
      val id = monitor.id
      Realtime.sendJavaScript(s"window.monitor.removeMonitor('$id');", onlyRealtime = false)
      map -= id
    }

    override def receive(event: String, message: Message) = event match {
      case "monitored" => {
        val id = message[String]("id")
        map.get(id) match {
          case Some(m) => m.receive(message)
          case None => warn("Monitor $id doesn't exist!")
        }
      }
      case _ => super.receive(event, message)
    }
  }
}

class Monitor[T] private(val id: String, val frequency: Double, val evaluator: JSFunction0[T])
                        (implicit manifest: Manifest[T], converter: ValuePersistence[T]) {
  val property = Property[T](default = None)

  def receive(message: Message) = {
    val value = message[String]("value")
    property := converter.fromString(value, null, manifest.runtimeClass)
  }
}