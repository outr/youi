package org.hyperscala.html.tag

import org.hyperscala._
import css.StyleSheet
import html.HTMLTag
import org.hyperscala.html.attributes._
import org.hyperscala.html.constraints._
import java.util.Calendar

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
class Time extends Container[BodyChild] with BodyChild with HTMLTag {
  lazy val xmlLabel = "time"

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
           dateTime: String = null,
           content: BodyChild = null) = {
    this()
    init(name, accessKey, clazz, contentEditable, contextMenu, dir, draggable, dropZone, hidden, id, lang, role, spellCheck, style, tabIndex, titleText)
    up(this.dateTime, dateTime)
    if (content != null) contents += content
  }

  lazy val dateTime = PropertyAttribute[String]("datetime", null)
}

object Time {
  def Format = "%1$tFT%1$tT%1$tz"

  def apply(l: Long) = Format.format(l)
  def apply(c: Calendar) = Format.format(c)
}