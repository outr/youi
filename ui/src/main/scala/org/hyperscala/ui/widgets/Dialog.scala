package org.hyperscala.ui.widgets

import org.hyperscala.html._
import org.hyperscala.Unique
import org.hyperscala.web.site.Webpage
import org.hyperscala.web.module.jQueryUI191

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Dialog(title: String) extends tag.Div(id = Unique(), titleText = title) {

  Webpage().require(jQueryUI191)
}
