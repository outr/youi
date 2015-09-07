package org.hyperscala.html.tag

import org.hyperscala._
import org.hyperscala.css.StyleSheet
import org.hyperscala.html.HTMLTag
import org.hyperscala.html.attributes._
import org.hyperscala.html.constraints._

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
class Param extends Container[BodyChild] with BodyChild with HTMLTag {
  lazy val xmlLabel = "param"

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
           value: String = null,
           content: BodyChild = null) = {
    this()
    init(name, accessKey, clazz, contentEditable, contextMenu, dir, draggable, dropZone, hidden, id, lang, role, spellCheck, style, tabIndex, titleText)
    up(this.value, value)
    if (content != null) contents += content
  }

  lazy val value = PropertyAttribute[String]("value", null)
}