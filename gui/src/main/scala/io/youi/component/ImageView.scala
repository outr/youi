package io.youi.component

import cats.effect.unsafe.implicits.global
import io.youi.component.feature.{FeatureParent, HeightFeature, WidthFeature}
import io.youi.component.types.Prop
import io.youi.dom
import io.youi.image.{CanvasImage, EmptyImage, HTMLImage, Image}
import org.scalajs.dom.html
import reactify.Var

class ImageView(img: html.Image = dom.create.image) extends Component(img) with WidthFeature with HeightFeature {
  override protected def parent: FeatureParent = this

  lazy val src: Prop[String] = new Prop[String](img.src, img.src_=, measureComponent)
  lazy val image: Var[Image] = {
    val v = Var[Image](Image.empty)
    v.attach {
      case i: HTMLImage => src @= i.source
      case EmptyImage => src @= ""
      //      case i: SVGImage => element.src = s"data:image/svg+xml;charset=utf-8,${i.toXML}"
      case i: CanvasImage => i.toDataURL.map { url =>
        src @= url
      }.unsafeRunAndForget()
      case _ => throw new RuntimeException(s"Unsupported Image type: $image")
    }
    v
  }
  override lazy val width: Prop[Double] = new Prop[Double](img.width.toDouble, d => img.width = math.ceil(d).toInt, measureComponent)
  override lazy val height: Prop[Double] = new Prop[Double](img.height.toDouble, d => img.height = math.ceil(d).toInt, measureComponent)
}