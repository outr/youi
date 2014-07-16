package org.hyperscala.html.tag

import org.hyperscala._
import css.StyleSheet
import html._
import io.HTMLWriter
import org.hyperscala.html.attributes._
import org.hyperscala.html.constraints._
import com.outr.net.Method
import argonaut.{Json, JsonObject}

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
class Input extends BodyChild with HTMLTag with FormField {
  lazy val xmlLabel = "input"

  def this(name: String = null,
           accessKey: java.lang.Character = null,
           clazz: Seq[String] = null,
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
           accept: String = null,
           alt: String = null,
           autoComplete: AutoComplete = null,
           autoFocus: java.lang.Boolean = null,
           checked: java.lang.Boolean = null,
           disabled: java.lang.Boolean = null,
           form: String = null,
           formAction: String = null,
           formEncType: String = null,
           formMethod: Method = null,
           formNoValidate: java.lang.Boolean = null,
           formTarget: Target = null,
           height: java.lang.Integer = null,
           inputType: InputType = null,
           list: String = null,
           max: String = null,
           maxLength: java.lang.Integer = null,
           min: String = null,
           multiple: java.lang.Boolean = null,
           pattern: String = null,
           placeHolder: String = null,
           readOnly: java.lang.Boolean = null,
           required: java.lang.Boolean = null,
           size: java.lang.Integer = null,
           src: String = null,
           step: java.lang.Integer = null,
           value: String = null,
           width: java.lang.Integer = null,
           content: BodyChild = null) = {
    this()
    init(name, accessKey, clazz, contentEditable, contextMenu, dir, draggable, dropZone, hidden, id, lang, role, spellCheck, style, tabIndex, titleText)
    up(this.accept, accept)
    up(this.alt, alt)
    up(this.autoComplete, autoComplete)
    up(this.autoFocus, autoFocus)
    up(this.checked, checked)
    up(this.disabled, disabled)
    up(this.form, form)
    up(this.formAction, formAction)
    up(this.formEncType, formEncType)
    up(this.formMethod, formMethod)
    up(this.formNoValidate, formNoValidate)
    up(this.formTarget, formTarget)
    up(this.height, height)
    up(this.inputType, inputType)
    up(this.list, list)
    up(this.max, max)
    up(this.maxLength, maxLength)
    up(this.min, min)
    up(this.multiple, multiple)
    up(this.pattern, pattern)
    up(this.placeHolder, placeHolder)
    up(this.readOnly, readOnly)
    up(this.required, required)
    up(this.size, size)
    up(this.src, src)
    up(this.step, step)
    up(this.value, value)
    up(this.width, width)
  }

  lazy val accept = PropertyAttribute[String]("accept", null)
  lazy val alt = PropertyAttribute[String]("alt", null)
  lazy val autoComplete = PropertyAttribute[AutoComplete]("autocomplete", null)
  lazy val autoFocus = PropertyAttribute[Boolean]("autofocus", false)
  val checked = PropertyAttribute[Boolean]("checked", false)
  lazy val disabled = PropertyAttribute[Boolean]("disabled", false)
  lazy val form = PropertyAttribute[String]("form", null)
  lazy val formAction = PropertyAttribute[String]("formaction", null)
  lazy val formEncType = PropertyAttribute[String]("formenctype", null)
  lazy val formMethod = PropertyAttribute[Method]("formmethod", null)
  lazy val formNoValidate = PropertyAttribute[Boolean]("formnovalidate", false)
  lazy val formTarget = PropertyAttribute[Target]("formtarget", null)
  lazy val height = PropertyAttribute[Int]("height", -1)
  lazy val inputType = PropertyAttribute[InputType]("type", null)
  lazy val list = PropertyAttribute[String]("list", null)
  lazy val max = PropertyAttribute[String]("max", null)
  lazy val maxLength = PropertyAttribute[Int]("maxlength", -1)
  lazy val min = PropertyAttribute[String]("min", null)
  lazy val multiple = PropertyAttribute[Boolean]("multiple", false)
  lazy val pattern = PropertyAttribute[String]("pattern", null)
  lazy val placeHolder = PropertyAttribute[String]("placeholder", null)
  lazy val readOnly = PropertyAttribute[Boolean]("readonly", false)
  lazy val required = PropertyAttribute[Boolean]("required", false)
  lazy val size = PropertyAttribute[Int]("size", -1)
  lazy val src = PropertyAttribute[String]("src", null)
  lazy val step = PropertyAttribute[Int]("step", -1)
  val value = PropertyAttribute[String]("value", "", inclusion = InclusionMode.Always)
  lazy val width = PropertyAttribute[Int]("width", -1)

  override def write(writer: HTMLWriter) = {
    if (inputType() == InputType.Password) {
      value := ""   // Password cannot be carried over between reloads for security reasons
    }

    super.write(writer)
  }

  override def formValue = value

  override protected def processChange(value: Json) = {
    if (value.isBool && List(InputType.CheckBox, InputType.Radio).contains(inputType())) {
      val b = value.bool.getOrElse(false)
      FormField.ignorePropertyChange(checked, b) {
        checked := b
      }
    } else {
      super.processChange(value)
    }
  }
}