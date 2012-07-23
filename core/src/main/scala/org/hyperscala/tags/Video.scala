package org.hyperscala.tags

import org.hyperscala.WebAttribute

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class Video extends Tag {
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
           tabIndex: java.lang.Integer = null,
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

  def tag = "video"

  val autoPlay = WebAttribute[String]("autoplay")
  val controls = WebAttribute[String]("controls")
  val height = WebAttribute[String]("height")
  val loop = WebAttribute[String]("loop")
  val muted = WebAttribute[String]("muted")
  val poster = WebAttribute[String]("poster")
  val preLoad = WebAttribute[String]("preload")
  val src = WebAttribute[String]("src")
  val width = WebAttribute[String]("width")
}
