package org.hyperscala.svg

import traits.Shape
import org.hyperscala.{XMLContent, Container}

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class G extends Container[SVGTag] with Shape {
  lazy val xmlLabel = "g"

  protected def generateChildFromTagName(name: String): XMLContent = {
    throw new UnsupportedOperationException("Loading SVG currently not supported")
  }

  protected def processText(text: String): Unit = {
    if (text != null && text.trim.length > 0) {
      contents += new Text {
        content := text
      }
    }
  }
}
