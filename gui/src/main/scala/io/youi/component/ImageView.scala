package io.youi.component

import io.youi.dom
import io.youi.component.types.Prop
import org.scalajs.dom.html

class ImageView(val img: html.Image = dom.create.image) extends Component(img) {
  lazy val src: Prop[String] = new Prop[String](img.src, img.src_=, measure)
}
