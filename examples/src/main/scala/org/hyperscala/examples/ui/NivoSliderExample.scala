package org.hyperscala.examples.ui

import org.hyperscala.ui.widgets.{NivoEffect, NivoSlider}

import org.hyperscala.html._
import org.hyperscala.examples.Example

/**
 * @author Matt Hicks <matt@outr.com>
 */
class NivoSliderExample extends Example {
  contents += new NivoSlider("example", effect = NivoEffect.Random) {
    slider.contents += new tag.Img(src = "/images/slide1.jpg")
    slider.contents += new tag.Img(src = "/images/slide2.jpg")
    slider.contents += new tag.Img(src = "/images/slide3.jpg")
    slider.contents += new tag.Img(src = "/images/slide4.jpg")
  }
}
