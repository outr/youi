package org.hyperscala.html.tag

import org.hyperscala._
import org.hyperscala.css.StyleSheet
import html.{FormField, HTMLTag}
import org.hyperscala.html.attributes._
import org.hyperscala.html.constraints._

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <mhicks@hyperscala.org>
 */
class TextArea extends Textual with BodyChild with HTMLTag with FormField {
  def value = content

  lazy val xmlLabel = "textarea"
  override def xmlExpanded = true

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
           cols: java.lang.Integer = null,
           disabled: java.lang.Boolean = null,
           form: String = null,
           maxLength: java.lang.Integer = null,
           placeHolder: String = null,
           readOnly: String = null,
           required: String = null,
           rows: java.lang.Integer = null,
           wrap: TextAreaWrap = null,
           content: String = null) = {
    this()
    init(name, accessKey, clazz, contentEditable, contextMenu, dir, draggable, dropZone, hidden, id, lang, role, spellCheck, style, tabIndex, titleText)
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
    up(this.content, content)
  }

  lazy val autoFocus = PropertyAttribute[Boolean]("autofocus", false)
  lazy val cols = PropertyAttribute[Int]("cols", -1)
  lazy val disabled = PropertyAttribute[Boolean]("disabled", false)
  lazy val form = PropertyAttribute[String]("form", null)
  lazy val maxLength = PropertyAttribute[Int]("maxlength", -1)
  lazy val placeHolder = PropertyAttribute[String]("placeholder", null)
  lazy val readOnly = PropertyAttribute[String]("readonly", null)
  lazy val required = PropertyAttribute[String]("required", null)
  lazy val rows = PropertyAttribute[Int]("rows", -1)
  lazy val wrap = PropertyAttribute[TextAreaWrap]("wrap", null)

  override def formValue = content
}