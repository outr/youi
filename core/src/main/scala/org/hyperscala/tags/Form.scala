package com.outr.webframework.tags

import com.outr.webframework.WebAttribute

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
class Form extends Tag {
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

  def tag = "form"

  val acceptCharset = WebAttribute[String]("accept-charset")
  val action = WebAttribute[String]("action")
  val autoComplete = WebAttribute[String]("autocomplete")
  val encType = WebAttribute[String]("enctype")
  val method = WebAttribute[String]("method")
  val name = WebAttribute[String]("name")
  val noValidate = WebAttribute[String]("novalidate")
  val target = WebAttribute[String]("target")
}
