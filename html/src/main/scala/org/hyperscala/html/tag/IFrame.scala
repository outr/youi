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
class IFrame extends Container[BodyChild] with BodyChild with HTMLTag {
  lazy val xmlLabel = "iframe"

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
           height: String = null,
           sandbox: String = null,
           seamless: String = null,
           src: String = null,
           srcDoc: String = null,
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
    up(this.height, height)
    up(this.sandbox, sandbox)
    up(this.seamless, seamless)
    up(this.src, src)
    up(this.srcDoc, srcDoc)
    up(this.width, width)
    if (content != null) contents += content
  }

  val height = PropertyAttribute[String]("height", null)
  val sandbox = PropertyAttribute[String]("sandbox", null)
  val seamless = PropertyAttribute[String]("seamless", null)
  val src = PropertyAttribute[String]("src", null)
  val srcDoc = PropertyAttribute[String]("srcdoc", null)
  val width = PropertyAttribute[String]("width", null)
}