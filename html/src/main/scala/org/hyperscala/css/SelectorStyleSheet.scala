package org.hyperscala.css

import org.hyperscala.html.HTMLTag
import org.hyperscala.html.tag.HTML
import org.hyperscala.selector.Selector

/**
 * @author Matt Hicks <matt@outr.com>
 */
class SelectorStyleSheet(selector: Selector)(implicit tag: HTMLTag) extends StyleSheet(null, null) {
  tag.connected[HTML] {
    case html => html.head.selector(selector)(this, append = true)
  }

  def this(selector: String)(implicit tag: HTMLTag) = this(Selector(selector))
}