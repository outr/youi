package org.hyperscala.jquery.ui

import org.hyperscala.html._
import org.hyperscala.jquery.{jQueryComponent, JavaScriptCaller}
import org.hyperscala.web._
import org.hyperscala.realtime.Realtime
import org.powerscala.StorageComponent
import scala.language.implicitConversions

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
object DatePicker extends JavaScriptCaller with StorageComponent[DatePicker, tag.Input] {
  implicit def tag2Picker(t: tag.Input) = apply(t)

  override def apply(t: tag.Input) = {
    t.require(jQueryUI.LatestWithDefault)
    t.require(Realtime)
    super.apply(t)
  }

  override protected def create(t: tag.Input) = new DatePicker(t)
}

class DatePicker private(val wrapped: tag.Input) extends jQueryComponent {
  def functionName = "datepicker"
  override protected def autoInit = true

  val dateFormat = property("dateFormat", "mm/dd/yy")

  def destroy() = call("destroy")
  def hide() = call("hide")
  def refresh() = call("refresh")
  def setDate(date: String) = call("setDate", date)
  def show() = call("show")
}
