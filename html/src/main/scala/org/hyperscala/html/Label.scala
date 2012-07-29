package org.hyperscala.html

import org.hyperscala._
import org.hyperscala.html.attributes._
import org.hyperscala.html.constraints._

/**
 * @author Matt Hicks <mhicks@hyperscala.org>
 */
class Label extends BodyChild with HTMLTag {
  protected lazy val xmlLabel = "label"

  def this(accessKey: java.lang.Character = null,
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
           style: String = null,
           tabIndex: java.lang.Integer = null,
           title: String = null,
           forElement: String = null,
           form: String = null) = {
    this()
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
    up(this.forElement, forElement)
    up(this.form, form)
  }

  val forElement = PropertyAttribute[String]("forelement", null)
  val form = PropertyAttribute[String]("form", null)
}