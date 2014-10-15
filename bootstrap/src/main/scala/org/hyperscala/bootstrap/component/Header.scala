package org.hyperscala.bootstrap.component

import org.hyperscala.html._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Header(text: String, subtext: String = null) extends tag.Div(clazz = List("page-header")) {
  contents += new tag.H1 {
    contents += text
    if (subtext != null) {
      contents += new tag.Small(content = subtext)
    }
  }
}
