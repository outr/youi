package org.hyperscala.ui.module

import org.hyperscala.module.Module
import org.powerscala.json.TypedSupport
import org.powerscala.log.Logging
import org.powerscala.{Unique, Version}
import org.hyperscala.realtime.{RealtimePage, Realtime}
import org.hyperscala.web.{Webpage, Website}
import org.hyperscala.html._
import org.powerscala.property.Property
import org.hyperscala.PropertyAttribute
import org.hyperscala.persistence.ValuePersistence
import org.hyperscala.css.StyleSheetAttribute

import org.hyperscala.javascript.dsl._
import com.outr.net.http.session.Session

/**
 * Monitor allows arbitrary JavaScript to be monitored for a changing result at a specific interval and to send that data
 * back to the server when it changes. This utilizes batch sends to avoid extraneous information but causes some
 * intermediate data to be potentially lost.
 *
 * @author Matt Hicks <matt@outr.com>
 */
object Monitor extends Module with Logging {
  TypedSupport.register("monitor", classOf[MonitorEvent])

  val name = "monitor"
  val version = Version(1)

  override def dependencies = List(Realtime)

  override def init[S <: Session](website: Website[S]) = {
    website.register("/js/monitor.js", "monitor.js")
  }

  override def load[S <: Session](webpage: Webpage[S]) = {
    webpage.head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/monitor.js")
    apply(webpage)
  }

  private def apply[S <: Session](webpage: Webpage[S]) = {
    webpage.store.getOrSet("monitor", new MonitoredPage(webpage))
  }

  def create[T, S <: Session](webpage: Webpage[S], frequency: Double, evaluator: JSFunction0[T])
               (implicit manifest: Manifest[T], converter: ValuePersistence[T]) = {
    apply(webpage).create[T](frequency, evaluator)
  }

  def remove[T, S <: Session](webpage: Webpage[S], monitor: Monitor[T]) = {
    apply(webpage).remove[T](monitor)
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

  private class MonitoredPage[S <: Session](webpage: Webpage[S]) {
    private var map = Map.empty[String, Monitor[_]]

    webpage.jsonEvent.partial(Unit) {
      case evt: MonitorEvent => map.get(evt.monitorId) match {
        case Some(monitor) => monitor.receive(evt)
        case None => warn(s"Monitor ${evt.monitorId} doesn't exist!")
      }
    }

    def create[T](frequency: Double, evaluator: JSFunction0[T])
                 (implicit manifest: Manifest[T], converter: ValuePersistence[T]) = {
      val id = Unique()
      val monitor = new Monitor[T](id, frequency, evaluator)
      synchronized {
        map += id -> monitor
      }
      val f = math.round(frequency * 1000.0)
      webpage.eval(s"window.monitor.createMonitor('$id', $f, ${evaluator.content});")
      monitor
    }

    def remove[T](monitor: Monitor[T]) = synchronized {
      val id = monitor.id
      webpage.eval(s"window.monitor.removeMonitor('$id');")
      map -= id
    }
  }
}

case class MonitorEvent(monitorId: String, value: String)

class Monitor[T] private(val id: String, val frequency: Double, val evaluator: JSFunction0[T])
                        (implicit manifest: Manifest[T], converter: ValuePersistence[T]) {
  val property = Property[T](default = None)

  def receive(evt: MonitorEvent) = {
    property := converter.fromString(evt.value, null, manifest.runtimeClass)
  }
}