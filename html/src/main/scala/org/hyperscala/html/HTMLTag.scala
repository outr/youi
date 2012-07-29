package org.hyperscala.html

import attributes._
import org.hyperscala._

/**
 * @author Matt Hicks <mhicks@hyperscala.org>
 */
trait HTMLTag extends Tag {
  val accessKey = PropertyAttribute[Char]("accesskey", -1.toChar)
  val clazz = PropertyAttribute[List[String]]("class", Nil)
  val contentEditable = PropertyAttribute[ContentEditable]("contenteditable", null)
  val contextMenu = PropertyAttribute[String]("contextmenu", null)
  val dir = PropertyAttribute[Direction]("dir", null)
  val draggable = PropertyAttribute[Draggable]("draggable", null)
  val dropZone = PropertyAttribute[DropZone]("dropzone", null)
  val hidden = PropertyAttribute[Boolean]("hidden", false)
  val id = PropertyAttribute[String]("id", null)
  val lang = PropertyAttribute[String]("lang", null)
  val spellCheck = PropertyAttribute[Boolean]("spellcheck", false)
  val style = PropertyAttribute[String]("style", null)
  val tabIndex = PropertyAttribute[Int]("tabindex", -1)
  val title = PropertyAttribute[String]("title", null)
}