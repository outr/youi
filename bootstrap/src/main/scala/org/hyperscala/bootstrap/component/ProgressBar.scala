package org.hyperscala.bootstrap.component

import org.hyperscala.html._
import org.hyperscala.html.extension.ClassBooleanProperty
import org.powerscala.enum.{Enumerated, EnumEntry}
import org.powerscala.property.Property
import org.hyperscala.css.attributes.PercentLength

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ProgressBar extends tag.Div {
  clazz += "progress"

  val striped = new ClassBooleanProperty(this, enabled = Some("progress-striped"))
  val active = new ClassBooleanProperty(this, enabled = Some("progress-active"))

  private var _progress = 0.0

  def progress = _progress

  def percentage = math.floor(progress * 100.0).toInt

  def clearProgress() = {
    contents.clear()
    _progress = 0.0
  }

  def addProgress(value: Double, progressType: ProgressType = ProgressType.Default) = {
    val current = contents.lastOption.map(_.clazz().find(s => s.startsWith("progress-bar-")).get).getOrElse(null)
    val entry = if (current != progressType.className) {      // Add a new progress entry
      val d = new tag.Div(clazz = List("progress-bar", progressType.className), role = "progressbar") {
        style.width := 0.pct
        ariaValueMin := "0"
        ariaValueMax := "100"
      }
      contents += d
      d
    } else {
      contents.last.asInstanceOf[tag.Div]
    }
    val currentPercent = entry.style.width() match {
      case pl: PercentLength => pl.number
      case _ => 0.0
    }
    val newPercent = math.floor((value * 100.0) + currentPercent).toInt
    entry.style.width := newPercent.pct
    entry.ariaValueNow := newPercent.toString
    entry.contents.clear()
    entry.contents += new tag.Span(clazz = List("sr-only")) {
      contents += s"$newPercent% Complete"
    }
    _progress += value
  }
}

object ProgressBar {
  def apply(value: Double, progressType: ProgressType = ProgressType.Default, striped: Boolean = false, active: Boolean = false) = {
    val sb = striped
    val ab = active
    new ProgressBar {
      striped := sb
      active := ab

      addProgress(value, progressType)
    }
  }
}

sealed abstract class ProgressType(val className: String) extends EnumEntry

object ProgressType extends Enumerated[ProgressType] {
  case object Default extends ProgressType("progress-bar-default")
  case object Success extends ProgressType("progress-bar-success")
  case object Info extends ProgressType("progress-bar-info")
  case object Warning extends ProgressType("progress-bar-warning")
  case object Danger extends ProgressType("progress-bar-danger")

  val values = findValues.toVector
}