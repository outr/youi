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
           content: String = null) = {
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
    // TODO: add tag-specific attributes
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
