package org.hyperscala.html

import org.hyperscala.css.StyleSheet

/**
 * @author Matt Hicks <matt@outr.com>
 */
class StyleSpaces extends tag.Style(id = "style_spaces") with Spaces[StyleSheet, StyleSheet] {
  override protected def before() = {
    super.before()

    content := map.values.map(ss => ss.toString).mkString
  }
}