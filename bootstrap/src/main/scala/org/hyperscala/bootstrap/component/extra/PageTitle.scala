package org.hyperscala.bootstrap.component.extra

import org.hyperscala.html.tag
import org.hyperscala.bootstrap.component.PageHeader

class PageTitle(text: String, subtext: Option[String] = None) extends PageHeader {
  contents += new tag.H1 {
    contents += text
    subtext.foreach { st =>
      contents += new tag.Small(content = st)
    }
  }
}
