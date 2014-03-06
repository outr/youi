package org.hyperscala.html

import org.hyperscala.css.StyleSheet
import org.hyperscala.html.tag.Head

/**
 * @author Matt Hicks <matt@outr.com>
 */
class StyleSpaces(head: Head) extends tag.Style(id = "style_spaces") with Spaces[String, StyleSheet] {
  def keyManifest = implicitly[Manifest[String]]
  def valueManifest = implicitly[Manifest[StyleSheet]]

  override protected def before() = {
    super.before()

    updateContent()
  }

  def updateContent() = {
    val contentString = map.values.collect {
      case ss if ss.nonEmpty => ss.toString
    }.mkString
    content := contentString
  }

  def parse(css: String) = synchronized {
    val sheets = StyleSheet.parse(this, css)
    sheets.foreach {
      case ss => {
        val selectorString = ss.selectorString
        get(selectorString) match {
          case Some(styleSheet) => styleSheet(ss)     // Apply the parse CSS to the existing style sheet
          case None => {
            this(selectorString) = ss
          }
        }
      }
    }
  }
}