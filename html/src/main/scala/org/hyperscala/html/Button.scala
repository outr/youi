package org.hyperscala.html

import org.hyperscala._
import org.hyperscala.html.attributes._
import org.hyperscala.html.constraints._

/**
 * @author Matt Hicks <mhicks@hyperscala.org>
 */
class Button extends BodyChild with HTMLTag {
  protected lazy val xmlLabel = "button"

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
           autoFocus: String = null,
           buttonType: String = null,
           disabled: java.lang.Boolean = null,
           form: String = null,
           formAction: String = null,
           formEncType: String = null,
           formMethod: String = null,
           formNoValidate: String = null,
           formTarget: String = null,
           value: String = null) = {
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
  }

  val autoFocus = PropertyAttribute[String]("autofocus", null)
  val buttonType = PropertyAttribute[String]("type", null)
  val disabled = PropertyAttribute[Boolean]("disabled", false)
  val form = PropertyAttribute[String]("form", null)
  val formAction = PropertyAttribute[String]("formaction", null)
  val formEncType = PropertyAttribute[String]("formenctype", null)
  val formMethod = PropertyAttribute[String]("formmethod", null)
  val formNoValidate = PropertyAttribute[String]("formnovalidate", null)
  val formTarget = PropertyAttribute[String]("formtarget", null)
  val value = PropertyAttribute[String]("value", null)
}