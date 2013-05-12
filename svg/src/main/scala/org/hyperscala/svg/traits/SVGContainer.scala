package org.hyperscala.svg.traits

import org.hyperscala.{XMLContent, Container}
import org.hyperscala.svg.{Text, SVGTag}

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
trait SVGContainer[C <: SVGTag] extends Container[C] {
  protected def generateChildFromTagName(name: String): XMLContent = {
    throw new UnsupportedOperationException("Loading SVG currently not supported")
  }

  protected def processText(text: String): Unit = {
    if (text != null && text.trim.length > 0) {
      this.asInstanceOf[SVGContainer[SVGTag]].contents += new Text {
        content := text
      }
    }
  }

  protected def processComment(text: String): Unit = {
    warn(s"Ignoring SVG comment - $text")     // TODO: support
  }
}
