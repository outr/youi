package org.hyperscala.html.tag

import org.hyperscala._
import css.StyleSheet
import html.HTMLTag
import org.hyperscala.html.attributes._
import org.hyperscala.html.constraints._
import javascript.{JavaScriptString, JavaScriptContent}

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
class Script extends Container[JavaScriptContent] with BodyChild with HeadChild with HTMLTag {
  lazy val xmlLabel = "script"
  override def xmlExpanded = true

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
           async: String = null,
           charSet: String = null,
           defer: String = null,
           mimeType: String = null,
           src: String = null,
           content: JavaScriptContent = null) = {
    this()
    init(name, accessKey, clazz, contentEditable, contextMenu, dir, draggable, dropZone, hidden, id, lang, role, spellCheck, style, tabIndex, titleText)
    up(this.async, async)
    up(this.charSet, charSet)
    up(this.defer, defer)
    up(this.mimeType, mimeType)
    up(this.src, src)
    if (content != null) contents += content
  }

  lazy val async = PropertyAttribute[String]("async", null)
  lazy val charSet = PropertyAttribute[String]("charset", null)
  lazy val defer = PropertyAttribute[String]("defer", null)
  lazy val mimeType = PropertyAttribute[String]("type", "text/javascript", inclusion = InclusionMode.Always)
  lazy val src = PropertyAttribute[String]("src", null)

  override protected def processText(text: String) = {
    contents += new JavaScriptString(text)
  }
}