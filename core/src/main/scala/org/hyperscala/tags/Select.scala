package org.hyperscala.tags

import org.hyperscala.WebAttribute

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
class Select extends Tag {
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
           disabled: String = null,
           form: String = null,
           multiple: String = null,
           name: String = null,
           size: String = null) = {
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
    up(this.disabled, disabled)
    up(this.form, form)
    up(this.multiple, multiple)
    up(this.name, name)
    up(this.size, size)
  }

  def tag = "select"

  val autoFocus = WebAttribute[String]("autofocus")
  val disabled = WebAttribute[String]("disabled")
  val form = WebAttribute[String]("form")
  val multiple = WebAttribute[String]("multiple")
  val name = WebAttribute[String]("name")
  val size = WebAttribute[String]("size")
}
