package org.hyperscala.jquery.ui

import org.hyperscala.jquery.{jQueryComponent, JavaScriptCaller}
import org.powerscala.StorageComponent
import org.hyperscala.html._
import org.hyperscala.web._
import org.hyperscala.realtime.Realtime
import scala.language.implicitConversions
import argonaut.CodecJson
import argonaut.Argonaut._
import org.hyperscala.jquery.JSMapper
import org.hyperscala.event.EventReceived
import org.hyperscala.javascript.JavaScriptString
import org.powerscala.property.Property
import org.powerscala.concurrent.AtomicBoolean

/**
 * @author Matt Hicks <matt@outr.com>
 */
object Spinner extends JavaScriptCaller with StorageComponent[Spinner, tag.Input] {
  implicit def tag2Spinner(input: tag.Input) = apply(input)

  override def apply(input: tag.Input) = {
    input.require(jQueryUI.LatestWithDefault)
    input.require(Realtime)
    super.apply(input)
  }

  protected def create(input: tag.Input) = new Spinner(input)
}

class Spinner private(val wrapped: tag.Input) extends jQueryComponent {
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

  val changeEvent = event("change", JSMapper[ChangeEvent](List("event", "ui"), JavaScriptString("{'value': $(this).spinner('value') }"), (evt: EventReceived) => {
    evt.json.as[ChangeEvent]
  }))

  val spinEvent = event("spin", JSMapper[SpinEvent](List("event", "ui"), JavaScriptString("{'value': ui.value}"), (evt: EventReceived) => {
    evt.json.as[SpinEvent]
  }))

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

case class ChangeEvent(value: Double)

object ChangeEvent {
  implicit def codec: CodecJson[ChangeEvent] = casecodec1(ChangeEvent.apply, ChangeEvent.unapply)("value")
}

case class SpinEvent(value: Double)

object SpinEvent {
  implicit def codec: CodecJson[SpinEvent] = casecodec1(SpinEvent.apply, SpinEvent.unapply)("value")
}