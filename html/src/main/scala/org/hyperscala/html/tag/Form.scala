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
class Form extends Container[BodyChild] with BodyChild with HTMLTag {
  lazy val xmlLabel = "form"

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
           acceptCharset: String = null,
           action: String = null,
           autoComplete: AutoComplete = null,
           encType: String = null,
           method: String = null,
           noValidate: String = null,
           target: String = null,
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
    up(this.acceptCharset, acceptCharset)
    up(this.action, action)
    up(this.autoComplete, autoComplete)
    up(this.encType, encType)
    up(this.method, method)
    up(this.noValidate, noValidate)
    up(this.target, target)
    if (content != null) contents += content
  }

  val acceptCharset = PropertyAttribute[String]("acceptcharset", null)
  val action = PropertyAttribute[String]("action", null)
  val autoComplete = PropertyAttribute[AutoComplete]("autocomplete", null)
  val encType = PropertyAttribute[String]("enctype", null)
  val method = PropertyAttribute[String]("method", null)
  val noValidate = PropertyAttribute[String]("novalidate", null)
  val target = PropertyAttribute[String]("target", null)
}