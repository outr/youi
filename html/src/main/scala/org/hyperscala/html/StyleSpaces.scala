package org.hyperscala.html

import org.hyperscala.css.StyleSheet

/**
 * @author Matt Hicks <matt@outr.com>
 */
class StyleSpaces extends tag.Style with Spaces[StyleSheet] {
  override protected def before() = {
    super.before()

    content := map.values.map(ss => ss.toString).mkString("\n\n")
  }
}
