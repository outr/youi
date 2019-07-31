package io.youi.component

import io.youi.component.extras.{HTMLComponent, HTMLImageViewImplementation, ImageViewImplementation}
import io.youi.image.Image
import io.youi.spatial.Size
import io.youi.theme.{ImageViewTheme, StyleProp, Theme}
import org.scalajs.dom.html
import reactify.Var

class ImageView(implementation: ImageViewImplementation = HTMLImageViewImplementation)
    extends HTMLComponent[html.Element] with ImageViewTheme {
  override protected lazy val element: html.Element = implementation.createElement()

  override def existing: Boolean = false

  override protected def defaultParentTheme: Theme = ImageView

  lazy val image: StyleProp[Image] = {
    val i = style[Image]("src", Image.empty, None, updatesTransform = true)
    i.attach { img =>
      implementation(this, img)
    }
    i
  }

  private val modified: Var[Long] = Var(image().modified)

  modified.attach { _ =>
    implementation(this, image())
  }

  override def componentType: String = "ImageView"

  size.width.attach(d => implementation.updateSize(this, d, size.height()))
  size.height.attach(d => implementation.updateSize(this, size.width(), d))

  override protected def measure(s: Size): Size = s.set(image().width, image().height)
}

object ImageView extends ImageViewTheme {
  override protected def defaultParentTheme: Theme = HTMLComponent
}