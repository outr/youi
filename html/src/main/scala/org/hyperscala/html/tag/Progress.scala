package org.hyperscala.html.tag

import org.hyperscala._
import css.StyleSheet
import html.HTMLTag
import org.hyperscala.html.attributes._
import org.hyperscala.html.constraints._
import org.hyperscala.persistence.ValuePersistence

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
class Progress extends Container[BodyChild] with BodyChild with HTMLTag {
  lazy val xmlLabel = "progress"

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
           max: java.lang.Double = null,
           value: java.lang.Double = null,
           content: BodyChild = null) = {
    this()
    init(name, accessKey, clazz, contentEditable, contextMenu, dir, draggable, dropZone, hidden, id, lang, role, spellCheck, style, tabIndex, titleText)
    up(this.max, max)
    up(this.value, value)
    if (content != null) contents += content
  }

  lazy val max = PropertyAttribute[Double]("max", 1)
  lazy val value = PropertyAttribute[Double]("value", -1.0)(new ValuePersistence[Double] {
    def fromString(s: String, name: String, clazz: Class[_]) = s.collect {
      case c if c.isDigit || c == '.' => c
    } match {
      case "" => -1.0
      case v => try {
        v.toDouble
      } catch {
        case t: Throwable => -1.0
      }
    }

    def toString(t: Double, name: String, clazz: Class[_]) = if (t >= 0.0) t.toString else ""
  }, implicitly[Markup], implicitly[Manifest[Double]])
}