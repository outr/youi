package org.hyperscala.ui.module

import org.hyperscala.module.Module
import org.powerscala.{Unique, Version}
import org.hyperscala.realtime.{RealtimePage, Realtime}
import org.hyperscala.web.{Webpage, Website}
import org.hyperscala.html._
import org.powerscala.property.Property
import org.hyperscala.PropertyAttribute
import org.hyperscala.persistence.ValuePersistence
import org.hyperscala.css.StyleSheetAttribute

import org.hyperscala.realtime.dsl._
import org.hyperscala.javascript.dsl.JSFunction0
import argonaut.JsonObject
import com.outr.net.http.session.Session

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

  override def init[S <: Session](website: Website[S]) = {
    website.register("/js/monitor.js", "monitor.js")
  }

  override def load[S <: Session](webpage: Webpage[S]) = {
    webpage.head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/monitor.js")
    val div = apply(webpage)
    webpage.body.contents += div
  }

  private def apply[S <: Session](webpage: Webpage[S]) = {
    webpage.store.getOrSet("monitor_div", new MonitorDiv)
  }

  def create[T, S <: Session](webpage: Webpage[S], frequency: Double, evaluator: JSFunction0[T])
               (implicit manifest: Manifest[T], converter: ValuePersistence[T]) = {
    apply(webpage).create[T, S](webpage, frequency, evaluator)
  }

  def remove[T, S <: Session](webpage: Webpage[S], monitor: Monitor[T]) = {
    apply(webpage).remove[T, S](webpage, monitor)
  }

  def sync[T, S <: Session](webpage: Webpage[S], attribute: PropertyAttribute[T], frequency: Double) = {
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
    val monitor = create[T, S](webpage, frequency, function)(attribute.manifest, attribute.persister)
    val property = monitor.property
    property.change.on {
      case evt => RealtimePage.ignoringChange(attribute, property())
    }
    monitor
  }

  private class MonitorDiv extends tag.Div(id = "realtime_monitor") {
    private var map = Map.empty[String, Monitor[_]]

    def create[T, S <: Session](webpage: Webpage[S], frequency: Double, evaluator: JSFunction0[T])
                 (implicit manifest: Manifest[T], converter: ValuePersistence[T]) = {
      val id = Unique()
      val monitor = new Monitor[T](id, frequency, evaluator)
      synchronized {
        map += id -> monitor
      }
      val f = math.round(frequency * 1000.0)
      Realtime.sendJavaScript(webpage, s"window.monitor.createMonitor('$id', $f, ${evaluator.content});", onlyRealtime = false)
      monitor
    }

    def remove[T, S <: Session](webpage: Webpage[S], monitor: Monitor[T]) = synchronized {
      val id = monitor.id
      Realtime.sendJavaScript(webpage, s"window.monitor.removeMonitor('$id');", onlyRealtime = false)
      map -= id
    }

    override def receive(event: String, json: JsonObject) = event match {
      case "monitored" => {
        val id = json.string("elementId")
        map.get(id) match {
          case Some(m) => m.receive(json)
          case None => warn(s"Monitor $id doesn't exist!")
        }
      }
      case _ => super.receive(event, json)
    }
  }
}

class Monitor[T] private(val id: String, val frequency: Double, val evaluator: JSFunction0[T])
                        (implicit manifest: Manifest[T], converter: ValuePersistence[T]) {
  val property = Property[T](default = None)

  def receive(json: JsonObject) = {
    val value = json.string("value")
    property := converter.fromString(value, null, manifest.runtimeClass)
  }
}