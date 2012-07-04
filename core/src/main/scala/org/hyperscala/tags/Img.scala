package org.hyperscala.tags

import org.hyperscala.WebAttribute

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class Img extends Tag {
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
           src: String = null,
           alt: String = null,
           height: String = null,
           isMap: String = null,
           useMap: String = null,
           width: String = null) = {
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
    up(this.src, src)
    up(this.alt, alt)
    up(this.height, height)
    up(this.isMap, isMap)
    up(this.useMap, useMap)
    up(this.width, width)
  }

  def tag = "img"

  val src = WebAttribute[String]("src")
  val alt = WebAttribute[String]("alt")
  val height = WebAttribute[String]("height")
  val isMap = WebAttribute[String]("ismap")
  val useMap = WebAttribute[String]("usemap")
  val width = WebAttribute[String]("width")
}
