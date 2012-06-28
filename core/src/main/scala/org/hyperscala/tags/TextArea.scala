package org.hyperscala.tags

import org.hyperscala.WebAttribute

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
class TextArea extends Tag {
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
           content: String = null,
           autoFocus: String = null,
           cols: String = null,
           disabled: String = null,
           form: String = null,
           maxLength: String = null,
           name: String = null,
           placeHolder: String = null,
           readOnly: String = null,
           required: String = null,
           rows: java.lang.Integer = null,
           wrap: String = null) = {
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
    if (content != null) {
      contents += content
    }
    up(this.autoFocus, autoFocus)
    up(this.cols, cols)
    up(this.disabled, disabled)
    up(this.form, form)
    up(this.maxLength, maxLength)
    up(this.name, name)
    up(this.placeHolder, placeHolder)
    up(this.readOnly, readOnly)
    up(this.required, required)
    if (rows != null) {
      up(this.rows, rows.intValue())
    }
    up(this.wrap, wrap)
  }

  def tag = "textarea"

  val autoFocus = WebAttribute[String]("autofocus")
  val cols = WebAttribute[String]("cols")
  val disabled = WebAttribute[String]("disabled")
  val form = WebAttribute[String]("form")
  val maxLength = WebAttribute[String]("maxlength")
  val name = WebAttribute[String]("name")
  val placeHolder = WebAttribute[String]("placeholder")
  val readOnly = WebAttribute[String]("readonly")
  val required = WebAttribute[String]("required")
  val rows = WebAttribute[Int]("rows")
  val wrap = WebAttribute[String]("wrap")
}
