package io.youi.component

import io.youi.component.extras.{CanvasImageViewImplementation, HTMLComponent, ImageViewImplementation}
import io.youi.image.Image
import io.youi.theme.ImageViewTheme
import org.scalajs.dom.html
import reactify.Var

class ImageView(implementation: ImageViewImplementation = new CanvasImageViewImplementation)
    extends HTMLComponent[html.Element] with ImageViewTheme {
  override protected lazy val element: html.Element = implementation.createElement()

  lazy val image: Var[Image] = connect(Var(Image.empty), implementation.apply(this, _))

  override lazy val theme: Var[ImageViewTheme] = Var(ImageView)
  override def `type`: String = "ImageView"

  size.width.attach(d => implementation.updateSize(this, d, size.height()))
  size.height.attach(d => implementation.updateSize(this, size.width(), d))

  override protected def measuredWidth: Double = image().width

  override protected def measuredHeight: Double = image().height
}

object ImageView extends ImageViewTheme