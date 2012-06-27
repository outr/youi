package org.hyperscala.tags

import attributes.InputType
import org.hyperscala.WebAttribute

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
class Input extends Tag {
  def this(accessKey: String = null,
           clazz: String = null,
           contextEditable: String = null,
           contextMenu: String = null,
           dir: String = null,
           draggable: String = null,
           dropZone: String = null,
           hidden: String = null,
           id: String = null,
           lang: String = null,
           spellCheck: String = null,
           tabIndex: String = null,
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
           list: String = null,
           max: String = null,
           maxLength: String = null,
           min: String = null,
           multiple: String = null,
           name: String = null,
           pattern: String = null,
           placeHolder: String = null,
           readOnly: String = null,
           required: String = null,
           size: String = null,
           src: String = null,
           step: String = null,
           inputType: InputType = null,
           value: String = null,
           width: String = null) = {
    this()
    up(this.accessKey, accessKey)
    up(this.clazz, clazz)
    up(this.contextEditable, contextEditable)
    up(this.contextMenu, contextMenu)
    up(this.dir, dir)
    up(this.draggable, draggable)
    up(this.dropZone, dropZone)
    up(this.hidden, hidden)
    up(this.id, id)
    up(this.lang, lang)
    up(this.spellCheck, spellCheck)
    up(this.tabIndex, tabIndex)
    up(this.title, title)
    up(this.accept, accept)
    up(this.alt, alt)
    up(this.autoComplete, autoComplete)
    up(this.autoFocus, autoFocus)
    up(this.checked, checked)
    if (disabled != null) {
      up(this.disabled, disabled.booleanValue())
    }
    up(this.form, form)
    up(this.formAction, formAction)
    up(this.formEncType, formEncType)
    up(this.formMethod, formMethod)
    up(this.formNoValidate, formNoValidate)
    up(this.formTarget, formTarget)
    up(this.height, height)
    up(this.list, list)
    up(this.max, max)
    up(this.maxLength, maxLength)
    up(this.min, min)
    up(this.multiple, multiple)
    up(this.name, name)
    up(this.pattern, pattern)
    up(this.placeHolder, placeHolder)
    up(this.readOnly, readOnly)
    up(this.required, required)
    up(this.size, size)
    up(this.src, src)
    up(this.step, step)
    up(this.inputType, inputType)
    up(this.value, value)
    up(this.width, width)
  }

  def tag = "input"

  val accept = WebAttribute[String]("accept")
  val alt = WebAttribute[String]("alt")
  val autoComplete = WebAttribute[String]("autocomplete")
  val autoFocus = WebAttribute[String]("autofocus")
  val checked = WebAttribute[String]("checked")
  val disabled = WebAttribute[Boolean]("disabled")((b: Boolean) => if (b) "disabled" else "", this)
  val form = WebAttribute[String]("form")
  val formAction = WebAttribute[String]("formaction")
  val formEncType = WebAttribute[String]("formenctype")
  val formMethod = WebAttribute[String]("formmethod")
  val formNoValidate = WebAttribute[String]("formnovalidate")
  val formTarget = WebAttribute[String]("formtarget")
  val height = WebAttribute[String]("height")
  val list = WebAttribute[String]("list")
  val max = WebAttribute[String]("max")
  val maxLength = WebAttribute[String]("maxlength")
  val min = WebAttribute[String]("min")
  val multiple = WebAttribute[String]("multiple")
  val name = WebAttribute[String]("name")
  val pattern = WebAttribute[String]("pattern")
  val placeHolder = WebAttribute[String]("placeholder")
  val readOnly = WebAttribute[String]("readOnly")
  val required = WebAttribute[String]("required")
  val size = WebAttribute[String]("size")
  val src = WebAttribute[String]("src")
  val step = WebAttribute[String]("step")
  val inputType = WebAttribute[InputType]("type")
  val value = WebAttribute[String]("value")
  val width = WebAttribute[String]("width")
}
