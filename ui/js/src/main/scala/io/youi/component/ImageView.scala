package io.youi.component

import io.youi.component.extras.{HTMLComponent, HTMLImageViewImplementation, ImageViewImplementation}
import io.youi.image.Image
import io.youi.theme.ImageViewTheme
import org.scalajs.dom.html
import reactify.Var

class ImageView(implementation: ImageViewImplementation = HTMLImageViewImplementation)
    extends HTMLComponent[html.Element] with ImageViewTheme {
  override protected lazy val element: html.Element = implementation.createElement()

  lazy val image: Var[Image] = connect(Var(Image.empty), None, implementation.apply(this, _))

  private val modified: Var[Long] = Var(image().modified)

  modified.attach { _ =>
    implementation(this, image())
  }

  override lazy val theme: Var[ImageViewTheme] = Var(ImageView)
  override def componentType: String = "ImageView"

  size.width.attach(d => implementation.updateSize(this, d, size.height()))
  size.height.attach(d => implementation.updateSize(this, size.width(), d))

  override protected def measuredWidth: Double = image().width

  override protected def measuredHeight: Double = image().height
}

object ImageView extends ImageViewTheme