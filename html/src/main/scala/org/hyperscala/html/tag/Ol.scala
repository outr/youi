package org.hyperscala.html.tag

import org.hyperscala._
import css.StyleSheet
import html.HTMLTag
import org.hyperscala.css.attributes.ListStyleType
import org.hyperscala.html.attributes._
import org.hyperscala.html.constraints._

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
class Ol extends Container[BodyChild] with BodyChild with HTMLTag {
  lazy val xmlLabel = "ol"

  def this(name: String = null,
           accessKey: java.lang.Character = null,
           clazz: Seq[String] = null,
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
           reversed: java.lang.Boolean = null,
           start: java.lang.Integer = null,
           listType: ListStyleType = null,
           content: BodyChild = null) = {
    this()
    init(name, accessKey, clazz, contentEditable, contextMenu, dir, draggable, dropZone, hidden, id, lang, role, spellCheck, style, tabIndex, titleText)
    up(this.reversed, reversed)
    up(this.start, start)
    up(this.listType, listType)
    if (content != null) contents += content
  }

  lazy val reversed = PropertyAttribute[Boolean]("reversed", false)
  lazy val start = PropertyAttribute[Int]("start", 1)
  lazy val listType = PropertyAttribute[ListStyleType]("type", ListStyleType.Decimal)
}