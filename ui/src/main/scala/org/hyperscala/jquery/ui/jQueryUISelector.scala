package org.hyperscala.jquery.ui

import org.hyperscala.jquery.dsl.jQuerySelector

/**
 * @author Matt Hicks <matt@outr.com>
 */
class jQueryUISelector(selector: jQuerySelector) {
  def tooltip() = selector.call("tooltip()")
}