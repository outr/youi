package org.hyperscala.jquery.ui

import org.hyperscala.event.BrowserEvent
import org.hyperscala.html._
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.jquery.{MappedEvent, JavaScriptCaller, jQueryComponent}
import org.hyperscala.realtime.Realtime
import org.hyperscala.web._
import org.powerscala.StorageComponent
import org.powerscala.concurrent.AtomicBoolean
import org.powerscala.property.Property

import scala.language.implicitConversions

/**
 * @author Matt Hicks <matt@outr.com>
 */
object Spinner extends JavaScriptCaller with StorageComponent[Spinner, tag.Input] {
  implicit def tag2Spinner(input: tag.Input): Spinner = apply(input)

  override def apply(input: tag.Input) = {
    input.require(jQueryUI)
    input.require(Realtime)
    super.apply(input)
  }

  protected def create(input: tag.Input) = new Spinner(input)
}

class Spinner private(val wrapped: tag.Input, val autoInit: Boolean = true) extends jQueryComponent {
  def functionName = "spinner"

  val value = Property[Double]()
  private val valueChanging = new AtomicBoolean

  val disabled = property("disabled", false)
  val icons = property[Map[String, String]]("icons", Map("down" -> "ui-icon-triangle-1-s", "up" -> "ui-icon-triangle-1-n"))
  val incremental = property("incremental", true)
  val max = property[java.lang.Double]("max", null)
  val min = property[java.lang.Double]("min", null)
  val numberFormat = property[String]("numberFormat", null)
  val page = property("page", 10.0)
  val step = property("step", 1.0)

  val changeEvent = event(ChangeEvent)
  val spinEvent = event(SpinEvent)

  changeEvent.on {
    case evt => valueChanging.attempt {
      value := evt.value
    }
  }

  spinEvent.on {
    case evt => valueChanging.attempt {
      value := evt.value
    }
  }

  override protected def initializeComponent(values: Map[String, Any]) = {
    value.change.on {
      case evt => valueChanging.attempt {
        call("value", value())
      }
    }

    super.initializeComponent(values)

    call("value", value())
  }
}

case class ChangeEvent(tag: HTMLTag, value: Double) extends BrowserEvent

object ChangeEvent extends MappedEvent[ChangeEvent](Map("value" -> JavaScriptString("$(this).spinner('value')")))

case class SpinEvent(tag: HTMLTag, value: Double) extends BrowserEvent

object SpinEvent extends MappedEvent[SpinEvent](Map("value" -> JavaScriptString("ui.value")))