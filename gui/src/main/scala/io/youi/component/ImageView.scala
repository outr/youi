package io.youi.component

import io.youi.dom
import io.youi.component.types.Prop
import io.youi.image.{CanvasImage, EmptyImage, HTMLImage, Image}
import org.scalajs.dom.html
import reactify.Var

import scribe.Execution.global

class ImageView(img: html.Image = dom.create.image) extends Component(img) {
  lazy val src: Prop[String] = new Prop[String](img.src, img.src_=, measure.trigger)
  lazy val image: Var[Image] = {
    val v = Var[Image](Image.empty)
    v.attach {
      case i: HTMLImage => src @= i.source
      case EmptyImage => src @= ""
      //      case i: SVGImage => element.src = s"data:image/svg+xml;charset=utf-8,${i.toXML}"
      case i: CanvasImage => i.toDataURL.foreach { url =>
        src @= url
      }
      case _ => throw new RuntimeException(s"Unsupported Image type: $image")
    }
    v
  }
  lazy val width: Prop[Int] = new Prop[Int](img.width, img.width_=, measure.trigger)
  lazy val height: Prop[Int] = new Prop[Int](img.height, img.height_=, measure.trigger)
}