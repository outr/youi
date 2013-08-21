package org.hyperscala.ui.wrapped

import org.hyperscala.html._
import org.powerscala.property.{ListProperty, Property}
import org.powerscala.event.Listenable
import org.hyperscala.web.site.Webpage
import org.hyperscala.realtime.Realtime
import org.hyperscala.event.JavaScriptEvent

/**
 * @author Matt Hicks <matt@outr.com>
 */
abstract class SelectWrapper[T](val select: tag.Select)(implicit manifest: Manifest[T]) {
  Webpage().require(Realtime)
  if (select.changeEvent() == null) {
    select.changeEvent := JavaScriptEvent()
  }

  implicit val thisParent: Listenable = null

  val values = new Property[List[T]] with ListProperty[T]
  val selected = Property[T]()

  // Convert existing values in Select to List[T]
  values := select.contents.map(o => value2T(o.value())).toList

  // Determine selected value from Select
  selected := value2T(select.value())

  // Add listeners to synchronize changes between wrapper and Select
  select.value.change.on {      // Update the wrapper selected when Select changes value
    case evt => selected := value2T(evt.newValue)
  }
  selected.change.on {          // Update the Select when wrapper selected changes
    case evt => select.value := t2Value(evt.newValue)
  }
  values.change.on {            // Update the options list when values changed
    case evt => updateOptions()
  }

  private def updateOptions() = {   // Invoked when values is changed in order to update the Options on Select
    val previouslySelected = selected()
    select.contents.clear()
    values().foreach {
      case t => select.contents += new tag.Option(value = t2Value(t), content = t2Content(t))
    }
    select.value := t2Value(previouslySelected)
  }

  def value2T(value: String): T
  def t2Value(t: T): String
  def t2Content(t: T): String
}