package org.hyperscala.html.tag

import org.hyperscala._
import css.StyleSheet
import html.{FormField, HTMLTag}
import io.HTMLWriter
import org.hyperscala.html.attributes._
import org.hyperscala.html.constraints._

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <mhicks@hyperscala.org>
 */
class Input extends Container[BodyChild] with BodyChild with HTMLTag with FormField {
  lazy val xmlLabel = "input"

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
    up(this.name, name)
    up(this.accessKey, accessKey)
    up(this.clazz, clazz)
    up(this.contentEditable, contentEditable)
    up(this.contextMenu, contextMenu)
    up(this.dir, dir)
    up(this.draggable, draggable)
    up(this.dropZone, dropZone)
    up(this.hidden, hidden)
    up(this.id, id)
    up(this.lang, lang)
    up(this.spellCheck, spellCheck)
    up(this.style, style)
    up(this.tabIndex, tabIndex)
    up(this.titleText, titleText)
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
    if (content != null) contents += content
  }

  val accept = PropertyAttribute[String]("accept", null)
  val alt = PropertyAttribute[String]("alt", null)
  val autoComplete = PropertyAttribute[AutoComplete]("autocomplete", null)
  val autoFocus = PropertyAttribute[Boolean]("autofocus", false)
  val checked = PropertyAttribute[Boolean]("checked", false)
  val disabled = PropertyAttribute[Boolean]("disabled", false)
  val form = PropertyAttribute[String]("form", null)
  val formAction = PropertyAttribute[String]("formaction", null)
  val formEncType = PropertyAttribute[String]("formenctype", null)
  val formMethod = PropertyAttribute[Method]("formmethod", null)
  val formNoValidate = PropertyAttribute[Boolean]("formnovalidate", false)
  val formTarget = PropertyAttribute[Target]("formtarget", null)
  val height = PropertyAttribute[Int]("height", -1)
  val inputType = PropertyAttribute[InputType]("type", null)
  val list = PropertyAttribute[String]("list", null)
  val max = PropertyAttribute[String]("max", null)
  val maxLength = PropertyAttribute[Int]("maxlength", -1)
  val min = PropertyAttribute[String]("min", null)
  val multiple = PropertyAttribute[Boolean]("multiple", false)
  val pattern = PropertyAttribute[String]("pattern", null)
  val placeHolder = PropertyAttribute[String]("placeholder", null)
  val readOnly = PropertyAttribute[Boolean]("readonly", false)
  val required = PropertyAttribute[Boolean]("required", false)
  val size = PropertyAttribute[Int]("size", -1)
  val src = PropertyAttribute[String]("src", null)
  val step = PropertyAttribute[Int]("step", -1)
  val value = PropertyAttribute[String]("value", null, inclusion = InclusionMode.Always)
  val width = PropertyAttribute[Int]("width", -1)

  override def write(writer: HTMLWriter) = {
    if (inputType() == InputType.Password) {
      value := ""   // Password cannot be carried over between reloads for security reasons
    }

    super.write(writer)
  }

  override def formValue = value
}