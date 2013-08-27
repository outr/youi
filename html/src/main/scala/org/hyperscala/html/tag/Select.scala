package org.hyperscala.html.tag

import org.hyperscala._
import css.StyleSheet
import html.{FormField, HTMLTag}
import org.hyperscala.html.attributes._
import org.hyperscala.html.constraints._
import org.powerscala.property.{SetProperty, ListProperty, Property}
import java.util.concurrent.atomic.AtomicBoolean
import org.powerscala.property.event.PropertyChangeEvent
import org.powerscala.event.{ListenerWrapper, ListenMode}
import org.powerscala.Storage

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <mhicks@hyperscala.org>
 */
class Select extends Container[Option] with BodyChild with HTMLTag with FormField {
  implicit val thisSelect = this
  override def xmlExpanded = true

  lazy val xmlLabel = "select"

  def this(name: String = null,
           accessKey: java.lang.Character = null,
           clazz: List[String] = null,
           contentEditable: ContentEditable = null,
           contextMenu: String = null,
           dir: Direction = null,
           draggable: Draggable = null,
           dropZone: DropZone = null,
           hidden: java.lang.Boolean = null,
           id: String = null,
           lang: String = null,
           role: String = null,
           spellCheck: java.lang.Boolean = null,
           style: StyleSheet = null,
           tabIndex: java.lang.Integer = null,
           titleText: String = null,
           autoFocus: java.lang.Boolean = null,
           disabled: java.lang.Boolean = null,
           form: String = null,
           multiple: java.lang.Boolean = null,
           size: java.lang.Integer = null,
           content: Option = null) = {
    this()
    init(name, accessKey, clazz, contentEditable, contextMenu, dir, draggable, dropZone, hidden, id, lang, role, spellCheck, style, tabIndex, titleText)
    up(this.autoFocus, autoFocus)
    up(this.disabled, disabled)
    up(this.form, form)
    up(this.multiple, multiple)
    up(this.size, size)
    if (content != null) contents += content
  }

  lazy val autoFocus = PropertyAttribute[Boolean]("autofocus", false)
  lazy val disabled = PropertyAttribute[Boolean]("disabled", false)
  lazy val form = PropertyAttribute[String]("form", null)
  lazy val multiple = PropertyAttribute[Boolean]("multiple", false)
  lazy val size = PropertyAttribute[Int]("size", -1)

  val selectedOptions = new Property[List[Option]](default = Some(Nil)) with ListProperty[Option]
  val selected = new Property[List[String]](default = Some(Nil)) with ListProperty[String]
  val value = Property[String]()

  private val changeListener = (evt: PropertyChangeEvent[Boolean]) => {
    updateSelectionFromOptions()
  }

  private def tryUpdating(f: => Unit) = if (updating.compareAndSet(false, true)) {
    try {
      f
    } finally {
      updating.set(false)
    }
  }

  selectedOptions.change.on {
    case evt => {
      updateOptionsFromSelection()
      tryUpdating {
        selected := evt.newValue.map(o => o.value())
        value := selected().mkString("|")
      }
    }
  }
  selected.change.on {
    case evt => if (!updating.get()) {
      selectedOptions := evt.newValue.map(s => optionByValue(s)).flatten
    }
  }
  value.change.on {
    case evt => if (!updating.get()) {
      selected := evt.newValue.split('|').toList
    }
  }
  childAdded.on {
    case evt => evt.child match {
      case o: Option => {
//        Storage.set(o, "changeListener", o.selected.change.on(changeListener))
        updateSelectionFromOptions()
      }
    }
  }
  childRemoved.on {
    case evt => evt.child match {
      case o: Option => {
//        o.selected.change.remove(Storage.getAndSet[ListenerWrapper[PropertyChangeEvent[Boolean], Unit, Unit]](o, "changeListener", null).getOrElse(throw new RuntimeException("Unable to find change listener!")))
        updateSelectionFromOptions()
      }
    }
  }

  private val updating = new AtomicBoolean(false)

  private def updateSelectionFromOptions() = tryUpdating {
    println("updateSelectionFromOptions")
    val options = contents.collect {
      case o if o.selected() => o
    }.toList
    selectedOptions := options
    println("\tupdateSelecitonFromOptions finished")
  }

  private def updateOptionsFromSelection() = tryUpdating {
    println("updateOptionsFromSelection")
    val selected = selectedOptions()
    contents.foreach {
      case o => o.selected := selected.contains(o)
    }
    println("\tupdateOptionsFromSelection finished")
  }

  def optionByValue(value: String) = contents.find(o => o.value() == value)

//  selected.change.on {
//    case evt => {
//      val values = selectedValues
//      val selected = values.map {
//        case v => contents.find(o => o.value() == v || (o.value() == null && o.content == v))
//      }.flatten
//      contents.foreach {                // Select the values
//        case option => option.selected := selected.contains(option)
//      }
//      value := values.mkString("|")
//    }
//  }
//
//  value.change.on {
//    case evt => {
//      val values = selectedValues
//      val v = values.mkString("|")
//      if (value() != v) {
//        if (value() == null) {
//          selected := Nil
//        } else {
//          selected := value().split('|').toList // Set the selected values from value string
//        }
//      }
//    }
//  }
//  private def updateSelected() = if (contents.nonEmpty) {
//    val selection = contents.collect {
//      case o if o.selected() => o.value()
//    }
//    if (selection.nonEmpty) {   // Found selected items
//      val list = if (multiple()) {
//        selection.toList
//      } else {
//        List(selection.head)
//      }
//      selected := list
//    } else {                    // Nothing explicitly selected - set the first item selected
//      val o = contents.head
//      selected := List(o.value())
//    }
//  } else {
//    value := null
//  }

  // TODO: implement this to listen to selection change
//  listen[PropertyChangeEvent[_], Unit, Unit]("change", Descendants) {
//    case evt => evt.property match {
//      case pa: PropertyAttribute if (pa.name == "selected")
//    }
//  }
//
//  protected def selectedValues = selected() match {   // Support for multiple or single selection
//    case Nil => Nil
//    case v if !multiple() && v.size > 1 => List(v.head)
//    case v => v
//  }

  override def formValue = value
}