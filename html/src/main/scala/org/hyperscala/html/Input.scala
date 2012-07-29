package org.hyperscala.html

import org.hyperscala._
import org.hyperscala.html.attributes._
import org.hyperscala.html.constraints._

/**
 * @author Matt Hicks <mhicks@hyperscala.org>
 */
class Input extends BodyChild with HTMLTag {
  protected lazy val xmlLabel = "input"

  def this(accessKey: java.lang.Character = null,
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
           style: String = null,
           tabIndex: java.lang.Integer = null,
           title: String = null,
           accept: String = null,
           alt: String = null,
           autoComplete: String = null,
           autoFocus: String = null,
           checked: String = null,
           disabled: java.lang.Boolean = null,
           form: String = null,
           formAction: String = null,
           formEncType: String = null,
           formMethod: String = null,
           formNoValidate: String = null,
           formTarget: String = null,
           height: String = null,
           inputType: InputType = null,
           list: String = null,
           max: String = null,
           maxLength: String = null,
           min: String = null,
           multiple: String = null,
           pattern: String = null,
           placeHolder: String = null,
           readOnly: String = null,
           required: String = null,
           size: String = null,
           src: String = null,
           step: String = null,
           value: String = null,
           width: String = null) = {
    this()
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
    up(this.title, title)
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

  implicit val inputTypePersistence = InputType

  val accept = PropertyAttribute[String]("accept", null)
  val alt = PropertyAttribute[String]("alt", null)
  val autoComplete = PropertyAttribute[String]("autocomplete", null)
  val autoFocus = PropertyAttribute[String]("autofocus", null)
  val checked = PropertyAttribute[String]("checked", null)
  val disabled = PropertyAttribute[Boolean]("disabled", false)
  val form = PropertyAttribute[String]("form", null)
  val formAction = PropertyAttribute[String]("formaction", null)
  val formEncType = PropertyAttribute[String]("formenctype", null)
  val formMethod = PropertyAttribute[String]("formmethod", null)
  val formNoValidate = PropertyAttribute[String]("formnovalidate", null)
  val formTarget = PropertyAttribute[String]("formtarget", null)
  val height = PropertyAttribute[String]("height", null)
  val inputType = PropertyAttribute[InputType]("type", null)
  val list = PropertyAttribute[String]("list", null)
  val max = PropertyAttribute[String]("max", null)
  val maxLength = PropertyAttribute[String]("maxlength", null)
  val min = PropertyAttribute[String]("min", null)
  val multiple = PropertyAttribute[String]("multiple", null)
  val pattern = PropertyAttribute[String]("pattern", null)
  val placeHolder = PropertyAttribute[String]("placeholder", null)
  val readOnly = PropertyAttribute[String]("readonly", null)
  val required = PropertyAttribute[String]("required", null)
  val size = PropertyAttribute[String]("size", null)
  val src = PropertyAttribute[String]("src", null)
  val step = PropertyAttribute[String]("step", null)
  val value = PropertyAttribute[String]("value", null)
  val width = PropertyAttribute[String]("width", null)
}