package org.hyperscala.html.tag

import org.hyperscala._
import css.StyleSheet
import html.HTMLTag
import org.hyperscala.html.attributes._
import org.hyperscala.html.constraints._

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <mhicks@hyperscala.org>
 */
class Button extends Container[BodyChild] with BodyChild with HTMLTag {
  lazy val xmlLabel = "button"

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
           title: String = null,
           autoFocus: java.lang.Boolean = null,
           buttonType: ButtonType = null,
           disabled: java.lang.Boolean = null,
           form: String = null,
           formAction: String = null,
           formEncType: String = null,
           formMethod: Method = null,
           formNoValidate: java.lang.Boolean = null,
           formTarget: Target = null,
           value: String = null,
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
    up(this.title, title)
    up(this.autoFocus, autoFocus)
    up(this.buttonType, buttonType)
    up(this.disabled, disabled)
    up(this.form, form)
    up(this.formAction, formAction)
    up(this.formEncType, formEncType)
    up(this.formMethod, formMethod)
    up(this.formNoValidate, formNoValidate)
    up(this.formTarget, formTarget)
    up(this.value, value)
    if (content != null) contents += content
  }

  val autoFocus = PropertyAttribute[Boolean]("autofocus", false)
  val buttonType = PropertyAttribute[ButtonType]("type", null)
  val disabled = PropertyAttribute[Boolean]("disabled", false)
  val form = PropertyAttribute[String]("form", null)
  val formAction = PropertyAttribute[String]("formaction", null)
  val formEncType = PropertyAttribute[String]("formenctype", null)
  val formMethod = PropertyAttribute[Method]("formmethod", null)
  val formNoValidate = PropertyAttribute[Boolean]("formnovalidate", false)
  val formTarget = PropertyAttribute[Target]("formtarget", null)
  val value = PropertyAttribute[String]("value", null)
}