package org.hyperscala.html.tag

import org.hyperscala._
import css.StyleSheet
import html.{FormField, HTMLTag}
import org.hyperscala.html.attributes._
import org.hyperscala.html.constraints._
import org.powerscala.property.{ListProperty, Property}

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

  val autoFocus = PropertyAttribute[Boolean]("autofocus", false)
  val disabled = PropertyAttribute[Boolean]("disabled", false)
  val form = PropertyAttribute[String]("form", null)
  val multiple = PropertyAttribute[Boolean]("multiple", false)
  val size = PropertyAttribute[Int]("size", -1)

  val selected = new Property[List[String]](default = Some(Nil)) with ListProperty[String]
  val value = Property[String]()
  def selectedOptions = selected().map(value => optionByValue(value).getOrElse(throw new NullPointerException("Unable to find option by value: %s".format(value))))

  def optionByValue(value: String) = contents.find(o => o.value() == value)

  selected.change.on {
    case evt => {
      val values = selectedValues
      val selected = values.map {
        case v => contents.find(o => o.value() == v || (o.value() == null && o.content == v))
      }.flatten
      contents.foreach {                // Select the values
        case option => option.selected := selected.contains(option)
      }
      value := values.mkString("|")
    }
  }

  value.change.on {
    case evt => {
      val values = selectedValues
      val v = values.mkString("|")
      if (value() != v) {
        if (value() == null) {
          selected := Nil
        } else {
          selected := value().split('|').toList // Set the selected values from value string
        }
      }
    }
  }

  protected def selectedValues = selected() match {   // Support for multiple or single selection
    case Nil => Nil
    case v if (!multiple() && v.size > 1) => List(v.head)
    case v => v
  }

  override def formValue = value
}