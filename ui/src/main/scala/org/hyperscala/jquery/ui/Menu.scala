package org.hyperscala.jquery.ui

import org.hyperscala.html.HTMLTag

/**
 * @author Matt Hicks <matt@outr.com>
 */
object Menu {
  def apply(t: HTMLTag) = jQueryUI.menu(t)
}
