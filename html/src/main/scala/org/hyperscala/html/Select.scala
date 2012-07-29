package org.hyperscala.html

import org.hyperscala._
import org.hyperscala.html.attributes._
import org.hyperscala.html.constraints._

/**
 * @author Matt Hicks <mhicks@hyperscala.org>
 */
class Select extends BodyChild with HTMLTag {
  protected lazy val xmlLabel = "select"

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
           disabled: String = null,
           form: String = null,
           multiple: String = null,
           size: String = null) = {
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
    up(this.disabled, disabled)
    up(this.form, form)
    up(this.multiple, multiple)
    up(this.size, size)
  }

  val autoFocus = PropertyAttribute[String]("autofocus", null)
  val disabled = PropertyAttribute[String]("disabled", null)
  val form = PropertyAttribute[String]("form", null)
  val multiple = PropertyAttribute[String]("multiple", null)
  val size = PropertyAttribute[String]("size", null)
}