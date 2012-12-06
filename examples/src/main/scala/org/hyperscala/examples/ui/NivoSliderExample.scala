package org.hyperscala.examples.ui

import org.hyperscala.web.site.Webpage
import org.hyperscala.ui.widgets.{NivoEffect, NivoSlider}

import org.hyperscala.html._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class NivoSliderExample extends Webpage {
  body.contents += new NivoSlider("example", effect = NivoEffect.Random) {
    slider.contents += new tag.Img(src = "/images/slide1.jpg")
    slider.contents += new tag.Img(src = "/images/slide2.jpg")
    slider.contents += new tag.Img(src = "/images/slide3.jpg")
    slider.contents += new tag.Img(src = "/images/slide4.jpg")
  }
}
