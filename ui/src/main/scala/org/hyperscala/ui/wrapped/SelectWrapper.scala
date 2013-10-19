package org.hyperscala.ui.wrapped

import org.hyperscala.html._
import org.powerscala.property.{ListProperty, Property}
import org.powerscala.event.Listenable
import org.hyperscala.web.Webpage
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

  val values = new Property[List[T]](default = Some(Nil)) with ListProperty[T]
  val selected = Property[T]()

  select.contents.clear()      // Clear out existing options

  values.change.on {
    case evt => {
      val previousSelected = selected()
      select.contents.clear()
      evt.newValue.map(t => new OptionWrapper(t)).foreach {
        case o => select.contents += o
      }
      selected := previousSelected
    }
  }
  selected.change.on {
    case evt => select.selectedOptions.value = optionByValue(evt.newValue) match {
      case Some(o) => List(o)
      case None => Nil
    }
  }
  select.selectedOptions.change.on {
    case evt => selected := evt.newValue.asInstanceOf[List[OptionWrapper]].map(o => o.t).headOption.getOrElse(null.asInstanceOf[T])
  }

  protected def t2Value(t: T): String
  protected def t2Content(t: T): String

  def optionByValue(value: T) = select.byTag[OptionWrapper].find(o => o.t == value)

  class OptionWrapper(val t: T) extends tag.Option {
    value := t2Value(t)
    content := t2Content(t)
  }
}