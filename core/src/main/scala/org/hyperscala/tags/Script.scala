package org.hyperscala.tags

import org.hyperscala.WebAttribute
import org.hyperscala.js.JavaScriptContent

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class Script extends Tag {
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
           content: JavaScriptContent = null,
           async: String = null,
           defer: String = null,
           mimeType: String = null,
           charSet: String = null,
           src: String = null) = {
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
    up(this.async, async)
    up(this.defer, defer)
    up(this.mimeType, mimeType)
    up(this.charSet, charSet)
    up(this.src, src)
  }

  def tag = "script"

  val async = WebAttribute[String]("async")
  val defer = WebAttribute[String]("defer")
  val mimeType = WebAttribute[String]("type")
  val charSet = WebAttribute[String]("charset")
  val src = WebAttribute[String]("src")
}
