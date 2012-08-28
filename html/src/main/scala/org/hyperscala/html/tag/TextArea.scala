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
class TextArea extends Container[BodyChild] with BodyChild with HTMLTag {
  lazy val xmlLabel = "textarea"

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
           cols: java.lang.Integer = null,
           disabled: java.lang.Boolean = null,
           form: String = null,
           maxLength: java.lang.Integer = null,
           placeHolder: String = null,
           readOnly: String = null,
           required: String = null,
           rows: java.lang.Integer = null,
           wrap: TextAreaWrap = null,
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
    up(this.cols, cols)
    up(this.disabled, disabled)
    up(this.form, form)
    up(this.maxLength, maxLength)
    up(this.placeHolder, placeHolder)
    up(this.readOnly, readOnly)
    up(this.required, required)
    up(this.rows, rows)
    up(this.wrap, wrap)
    if (content != null) contents += content
  }

  val autoFocus = PropertyAttribute[Boolean]("autofocus", false)
  val cols = PropertyAttribute[Int]("cols", -1)
  val disabled = PropertyAttribute[Boolean]("disabled", false)
  val form = PropertyAttribute[String]("form", null)
  val maxLength = PropertyAttribute[Int]("maxlength", -1)
  val placeHolder = PropertyAttribute[String]("placeholder", null)
  val readOnly = PropertyAttribute[String]("readonly", null)
  val required = PropertyAttribute[String]("required", null)
  val rows = PropertyAttribute[Int]("rows", -1)
  val wrap = PropertyAttribute[TextAreaWrap]("wrap", null)
}