package org.hyperscala.html.tag

import org.hyperscala._
import css.StyleSheet
import html.HTMLTag
import javascript.JavaScriptContent
import org.hyperscala.html.attributes._
import org.hyperscala.html.constraints._
import scala.Some

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <mhicks@hyperscala.org>
 */
class Head extends Container[HeadChild] with HTMLChild with HTMLTag {
  lazy val xmlLabel = "head"
  override def xmlExpanded = true

  private var temporalScripts = List.empty[Script]

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
           titleText: String = null,
           content: HeadChild = null) = {
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
    up(this.titleText, titleText)
    if (content != null) contents += content
  }

  def title = byTag[Title].headOption match {
    case Some(t) => t.content
    case None => {
      val t = new Title
      contents += t
      t.content
    }
  }

  override protected def generateChildFromTagName(name: String) = {
    if (name == "title") {
      contents.find(c => c.xmlLabel == name) match {
        case Some(tag) => tag
        case None => super.generateChildFromTagName(name)
      }
    } else {
      super.generateChildFromTagName(name)
    }
  }

  override protected def before() {
    super.before()

    // Remove temporal scripts
    synchronized {
      temporalScripts.foreach {
        case script => contents -= script
      }
      temporalScripts = Nil
    }
  }

  def injectScript(content: JavaScriptContent, temporal: Boolean = false) = {
    val script = new Script {
      contents += content
    }
    if (temporal) {
      synchronized {
        temporalScripts = script :: temporalScripts
      }
    }
    contents += script
  }

  def meta(name: String, content: String) = {
    contents.collectFirst {
      case m: Meta if (m.name() == name) => m
    } match {
      case Some(m) => m.content := content
      case None => contents += new Meta(name = name, content = content)
    }
  }

  def charset(charset: String = "UTF-8") = {
    contents.collectFirst {
      case m: Meta if (m.charset() != null && m.charset().nonEmpty) => m
    } match {
      case Some(m) => m.charset := charset
      case None => contents += new Meta(charset = charset)
    }
  }
}