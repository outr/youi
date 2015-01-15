package org.hyperscala.html.tag

import org.hyperscala._
import css.StyleSheet
import html.HTMLTag
import org.hyperscala.html.attributes._
import org.hyperscala.html.constraints._

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
class Video extends Container[Source] with BodyChild with HTMLTag {
  lazy val xmlLabel = "video"
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
           autoPlay: java.lang.Boolean = null,
           controls: java.lang.Boolean = null,
           crossOrigin: String = null,
           height: java.lang.Integer = null,
           loop: java.lang.Boolean = null,
           muted: java.lang.Boolean = null,
           preLoad: String = null,
           poster: String = null,
           src: String = null,
           width: java.lang.Integer = null,
           content: Source = null) = {
    this()
    init(name, accessKey, clazz, contentEditable, contextMenu, dir, draggable, dropZone, hidden, id, lang, role, spellCheck, style, tabIndex, titleText)
    up(this.autoPlay, autoPlay)
    up(this.controls, controls)
    up(this.crossOrigin, crossOrigin)
    up(this.height, height)
    up(this.loop, loop)
    up(this.muted, muted)
    up(this.preLoad, preLoad)
    up(this.poster, poster)
    up(this.src, src)
    up(this.width, width)
    if (content != null) contents += content
  }

  lazy val autoPlay = PropertyAttribute[Boolean]("autoplay", false)
  lazy val controls = PropertyAttribute[Boolean]("controls", false)
  lazy val crossOrigin = PropertyAttribute[String]("crossorigin", null)
  lazy val height = PropertyAttribute[Int]("height", 0)
  lazy val loop = PropertyAttribute[Boolean]("loop", false)
  lazy val muted = PropertyAttribute[Boolean]("muted", false)
  lazy val preLoad = PropertyAttribute[String]("preload", "auto")
  lazy val poster = PropertyAttribute[String]("poster", null)
  lazy val src = PropertyAttribute[String]("src", null)
  lazy val width = PropertyAttribute[Int]("width", 0)
}