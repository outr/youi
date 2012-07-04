package org.hyperscala.tags

import org.hyperscala.WebAttribute


/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class A extends Tag {
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
           tabIndex: String = null,
           title: String = null,
           content: String = null,
           href: String = null,
           hrefLang: String = null,
           media: String = null,
           rel: String = null,
           target: String = null,
           mimeType: String = null) = {
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
    if (content != null) {
      contents += content
    }
    up(this.href, href)
    up(this.hrefLang, hrefLang)
    up(this.media, media)
    up(this.rel, rel)
    up(this.target, target)
    up(this.mimeType, mimeType)
  }

  val href = WebAttribute[String]("href")
  val hrefLang = WebAttribute[String]("hreflang")
  val media = WebAttribute[String]("media")
  val rel = WebAttribute[String]("rel")
  val target = WebAttribute[String]("target")
  val mimeType = WebAttribute[String]("type")

  def tag = "a"
}

object A {
  def apply(href: String = null, target: String = null)(f: => String = href) = {
    val a = new A()
    a.href := href
    a.target := target
    a.contents += Text(f)
    a
  }
}