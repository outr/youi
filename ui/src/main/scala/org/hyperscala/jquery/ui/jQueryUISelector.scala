package org.hyperscala.jquery.ui

import org.hyperscala.jquery.dsl.jQuerySelector
import org.hyperscala.web.Webpage

/**
 * @author Matt Hicks <matt@outr.com>
 */
class jQueryUISelector(selector: jQuerySelector) {
  Webpage().require(jQueryUI.LatestWithDefault)

  def tooltip() = selector.call("tooltip()")
}