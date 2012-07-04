package org.hyperscala.tags

import org.hyperscala.WebAttribute

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class Meta extends Tag {
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
           charSet: String = null,
           content: String = null,
           httpEquiv: String = null,
           name: String = null) = {
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
    up(this.charSet, charSet)
    up(this.content, content)
    up(this.httpEquiv, httpEquiv)
    up(this.name, name)
  }

  def tag = "meta"

  val charSet = WebAttribute[String]("charset")
  val content = WebAttribute[String]("content")
  val httpEquiv = WebAttribute[String]("http-equiv")
  val name = WebAttribute[String]("name")
}
