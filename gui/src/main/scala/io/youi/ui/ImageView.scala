package io.youi.ui

import io.youi.dom
import io.youi.ui.types.Prop
import org.scalajs.dom.html

class ImageView(val img: html.Image = dom.create.image) extends Component(img) {
  lazy val src: Prop[String] = new Prop[String](img.src, img.src_=, measure)
}
