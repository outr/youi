package org.hyperscala.examples.basic

import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.module.EncodedImages
import org.hyperscala.realtime.Realtime
import org.hyperscala.web._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class EncodedImageExample extends Webpage with Example {
  require(EncodedImages)
  require(Realtime)

  body.contents += new tag.P {
    contents += new tag.H3(content = "Relative URL:")
    contents += new tag.Img(src = "../../images/slide1.jpg")
  }
}