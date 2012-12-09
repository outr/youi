package org.hyperscala.examples.basic

import org.hyperscala.web.site.Webpage
import org.hyperscala.html._

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class StaticHTMLExample extends Webpage {
  body.contents += new tag.Div {
    contents += "Static content below:"
    contents += new StaticHTML(getClass.getClassLoader.getResource("static.html"))
  }
}
