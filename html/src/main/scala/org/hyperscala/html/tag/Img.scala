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
class Img extends Container[BodyChild] with BodyChild with HTMLTag {
  lazy val xmlLabel = "img"

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
           alt: String = null,
           height: String = null,
           isMap: String = null,
           src: String = null,
           useMap: String = null,
           width: String = null,
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
    up(this.alt, alt)
    up(this.height, height)
    up(this.isMap, isMap)
    up(this.src, src)
    up(this.useMap, useMap)
    up(this.width, width)
    if (content != null) contents += content
  }

  val alt = PropertyAttribute[String]("alt", null)
  val height = PropertyAttribute[String]("height", null)
  val isMap = PropertyAttribute[String]("ismap", null)
  val src = PropertyAttribute[String]("src", null)
  val useMap = PropertyAttribute[String]("usemap", null)
  val width = PropertyAttribute[String]("width", null)
}