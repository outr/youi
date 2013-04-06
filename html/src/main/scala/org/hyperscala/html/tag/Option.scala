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
class Option extends Textual with BodyChild with HTMLTag {
  lazy val xmlLabel = "option"

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
           role: String = null,
           spellCheck: java.lang.Boolean = null,
           style: StyleSheet = null,
           tabIndex: java.lang.Integer = null,
           titleText: String = null,
           disabled: java.lang.Boolean = null,
           label: String = null,
           selected: java.lang.Boolean = null,
           value: String = null,
           content: String = null) = {
    this()
    init(name, accessKey, clazz, contentEditable, contextMenu, dir, draggable, dropZone, hidden, id, lang, role, spellCheck, style, tabIndex, titleText)
    up(this.disabled, disabled)
    up(this.label, label)
    up(this.selected, selected)
    up(this.value, value)
    up(this.content, content)
  }

  val disabled = PropertyAttribute[Boolean]("disabled", false)
  val label = PropertyAttribute[String]("label", null)
  val selected = PropertyAttribute[Boolean]("selected", false)
  val value = PropertyAttribute[String]("value", null)

  selected.onChange {
    parent match {
      case select: Select => {
        if (selected()) {
          if (!select.selected().contains(value())) {
            select.selected += value()      // Add the value to the selection if it isn't already there
          }
        } else {
          if (select.selected().contains(value())) {
            select.selected -= value()      // Remove the value from the selection if it is there
          }
        }
      }
      case _ => // Parent isn't a SELECT
    }
  }
}