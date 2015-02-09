package org.hyperscala.examples.basic

import org.hyperscala.examples.Example
import org.hyperscala.module.EncodedImages
import org.hyperscala.realtime.Realtime
import org.hyperscala.html._
import org.hyperscala.web._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class EncodedImageExample extends Example {
  this.require(EncodedImages)
  this.require(Realtime)

  contents += new tag.P {
    contents += new tag.H3(content = "Relative URL:")
    contents += new tag.Img(src = "../../images/slide1.jpg")
  }
}