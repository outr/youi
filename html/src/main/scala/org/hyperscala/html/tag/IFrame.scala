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
  override def xmlExpanded = true

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
           role: String = null,
           spellCheck: java.lang.Boolean = null,
           style: StyleSheet = null,
           tabIndex: java.lang.Integer = null,
           titleText: String = null,
           height: String = null,
           sandbox: String = null,
           seamless: String = null,
           src: String = null,
           srcDoc: String = null,
           width: String = null,
           content: BodyChild = null) = {
    this()
    init(name, accessKey, clazz, contentEditable, contextMenu, dir, draggable, dropZone, hidden, id, lang, role, spellCheck, style, tabIndex, titleText)
    up(this.height, height)
    up(this.sandbox, sandbox)
    up(this.seamless, seamless)
    up(this.src, src)
    up(this.srcDoc, srcDoc)
    up(this.width, width)
    if (content != null) contents += content
  }

  lazy val height = PropertyAttribute[String]("height", null)
  lazy val sandbox = PropertyAttribute[String]("sandbox", null)
  lazy val seamless = PropertyAttribute[String]("seamless", null)
  lazy val src = PropertyAttribute[String]("src", null)
  lazy val srcDoc = PropertyAttribute[String]("srcdoc", null)
  lazy val width = PropertyAttribute[String]("width", null)
}