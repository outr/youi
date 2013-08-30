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

    content := map.values.map(ss => ss.toString).mkString
  }
}