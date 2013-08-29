package org.hyperscala.html

import org.hyperscala.css.StyleSheet
import org.hyperscala.html.tag.{Script, Head}
import org.hyperscala.javascript.JavaScriptString
import org.powerscala.event.processor.UnitProcessor

/**
 * @author Matt Hicks <matt@outr.com>
 */
class StyleSpaces(head: Head) extends tag.Style(id = "style_spaces") with Spaces[String, StyleSheet] {
  val removedEvent = new UnitProcessor[StyleSheetRemoved]("removedEvent")

  override protected def before() = {
    super.before()

    content := map.values.map(ss => ss.toString).mkString
  }

  override def remove(key: String): Option[StyleSheet] = {
    val option = super.remove(key)

    option match {
      case Some(ss) => removedEvent.fire(StyleSheetRemoved(ss))
      case _ => // Didn't remove anything
    }
    option
  }
}

case class StyleSheetRemoved(styleSheet: StyleSheet)