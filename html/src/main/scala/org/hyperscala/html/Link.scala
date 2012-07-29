package org.hyperscala.html

import org.hyperscala._
import org.hyperscala.html.attributes._
import org.hyperscala.html.constraints._

/**
 * @author Matt Hicks <mhicks@hyperscala.org>
 */
class Link extends BodyChild with HTMLTag {
  protected lazy val xmlLabel = "link"

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
           href: String = null,
           hrefLang: String = null,
           media: String = null,
           mimeType: String = null,
           rel: String = null,
           sizes: String = null) = {
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
    up(this.href, href)
    up(this.hrefLang, hrefLang)
    up(this.media, media)
    up(this.mimeType, mimeType)
    up(this.rel, rel)
    up(this.sizes, sizes)
  }

  val href = PropertyAttribute[String]("href", null)
  val hrefLang = PropertyAttribute[String]("hreflang", null)
  val media = PropertyAttribute[String]("media", null)
  val mimeType = PropertyAttribute[String]("type", null)
  val rel = PropertyAttribute[String]("rel", null)
  val sizes = PropertyAttribute[String]("sizes", null)
}