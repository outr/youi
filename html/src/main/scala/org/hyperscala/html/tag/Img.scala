package org.hyperscala.html.tag

import org.hyperscala._
import css.StyleSheet
import html.HTMLTag
import org.hyperscala.html.attributes._
import org.hyperscala.html.constraints._
import org.jdom2.Attribute
import org.hyperscala.css.attributes._

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
class Img extends Container[BodyChild] with BodyChild with HTMLTag {
  lazy val xmlLabel = "img"

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
           alt: String = null,
           height: Length = null,
           isMap: String = null,
           src: String = null,
           useMap: String = null,
           width: Length = null,
           content: BodyChild = null) = {
    this()
    init(name, accessKey, clazz, contentEditable, contextMenu, dir, draggable, dropZone, hidden, id, lang, role, spellCheck, style, tabIndex, titleText)
    up(this.alt, alt)
    up(this.height, height)
    up(this.isMap, isMap)
    up(this.src, src)
    up(this.useMap, useMap)
    up(this.width, width)
    if (content != null) contents += content
  }

  lazy val alt = PropertyAttribute[String]("alt", null)
  lazy val height = PropertyAttribute[Length]("height", null)
  lazy val isMap = PropertyAttribute[String]("ismap", null)
  lazy val src = PropertyAttribute[String]("src", null)
  lazy val useMap = PropertyAttribute[String]("usemap", null)
  lazy val width = PropertyAttribute[Length]("width", null)

  override protected def attributeFromXML(a: Attribute) = {
    if (a.getName == "align") {
      a.getValue.trim.toLowerCase match {
        case "left" => style.float := Float.Left
        case "right" => style.float := Float.Right
      }
      true
    } else {
      super.attributeFromXML(a)
    }
  }
}