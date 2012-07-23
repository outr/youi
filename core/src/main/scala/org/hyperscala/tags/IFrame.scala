package org.hyperscala.tags

import org.hyperscala.WebAttribute

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class IFrame extends Tag {
  def this(accessKey: String = null,
           clazz: String = null,
           contextEditable: String = null,
           contextMenu: String = null,
           dir: String = null,
           draggable: String = null,
           dropZone: String = null,
           hidden: String = null,
           id: String = null,
           lang: String = null,
           spellCheck: String = null,
           tabIndex: java.lang.Integer = null,
           title: String = null,
           height: String = null,
           name: String = null,
           sandbox: String = null,
           seamless: String = null,
           src: String = null,
           srcDoc: String = null,
           width: String = null) = {
    this()
    up(this.accessKey, accessKey)
    up(this.clazz, clazz)
    up(this.contextEditable, contextEditable)
    up(this.contextMenu, contextMenu)
    up(this.dir, dir)
    up(this.draggable, draggable)
    up(this.dropZone, dropZone)
    up(this.hidden, hidden)
    up(this.id, id)
    up(this.lang, lang)
    up(this.spellCheck, spellCheck)
    up(this.tabIndex, tabIndex)
    up(this.title, title)
    up(this.height, height)
    up(this.name, name)
    up(this.sandbox, sandbox)
    up(this.seamless, seamless)
    up(this.src, src)
    up(this.srcDoc, srcDoc)
    up(this.width, width)
  }

  def tag = "iframe"

  val height = WebAttribute[String]("height")
  val name = WebAttribute[String]("name")
  val sandbox = WebAttribute[String]("sandbox")
  val seamless = WebAttribute[String]("seamless")
  val src = WebAttribute[String]("src")
  val srcDoc = WebAttribute[String]("srcdoc")
  val width = WebAttribute[String]("width")
}
