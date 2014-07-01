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

  val altField = property("altField", "")
  val altFormat = property("altFormat", "")
  val appendText = property("appendText", "")
  val autoSize = property("autoSize", false)
  val buttonImage = property("buttonImage", "")
  val buttonImageOnly = property("buttonImageOnly", false)
  val buttonText = property("buttonText", "...")
  val changeMonth = property("changeMonth", false)
  val changeYear = property("changeYear", false)
  val closeText = property("closeText", "Done")
  val constrainInput = property("constrainInput", true)
  val currentText = property("currentText", "Today")
  val dateFormat = property("dateFormat", "mm/dd/yy")
  val dayNames = property("dayNames", List("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"))
  val dayNamesMin = property("dayNamesMin", List("Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"))
  val dayNamesShort = property("dayNamesShort", List("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"))
  val duration = property("duration", "normal")
  val firstDay = property("firstDay", 0)
  val gotoCurrent = property("gotoCurrent", false)
  val hideIfNoPrevNext = property("hideIfNoPrevNext", false)
  val isRTL = property("isRTL", false)
  val monthNames = property("monthNames", List("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"))
  val monthNamesShort = property("monthNamesShort", List("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"))
  val navigationAsDateFormat = property("navigationAsDateFormat", false)
  val nextText = property("nextText", "Next")
  val numberOfMonths = property("numberOfMonths", 1)
  val prevText = property("prevText", "Prev")
  val selectOtherMonths = property("selectOtherMonths", false)
  val shortYearCutoff = property("shortYearCutoff", "+10")
  val showAnim = property("showAnim", "show")
  val showButtonPanel = property("showButtonPanel", false)
  val showCurrentAtPos = property("showCurrentAtPos", 0)
  val showMonthAfterYear = property("showMonthAfterYear", false)
  val showOn = property("showOn", "focus")
  val showOtherMonths = property("showOtherMonths", false)
  val showWeek = property("showWeek", false)
  val stepMonths = property("stepMonths", 1)
  val weekHeader = property("weekHeader", "Wk")
  val yearRange = property("yearRange", "c-10:c+10")
  val yearSuffix = property("yearSuffix", "")

  def destroy() = call("destroy")
  def hide() = call("hide")
  def refresh() = call("refresh")
  def setDate(date: String) = call("setDate", date)
  def show() = call("show")
}
