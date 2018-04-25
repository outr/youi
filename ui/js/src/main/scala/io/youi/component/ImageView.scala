package io.youi.component

import io.youi.component.extras.HTMLComponent
import io.youi.dom
import io.youi.image.{EmptyImage, HTMLImage, Image}
import io.youi.theme.{ComponentTheme, ImageViewTheme}
import org.scalajs.dom.html
import reactify.Var

class ImageView(protected val element: html.Image) extends HTMLComponent[html.Image] with ImageViewTheme {
  def this() = {
    this(dom.create[html.Image]("img"))
  }

  lazy val image: Var[Image] = connect(Var(Image.empty), applyImage, updateSizeFromElement())

  override lazy val theme: Var[ImageViewTheme] = Var(ImageView)
  override def `type`: String = "ImageView"

  size.width.attach(d => element.width = math.round(d).toInt)
  size.height.attach(d => element.height = math.round(d).toInt)

  private def applyImage(image: Image): Unit = image match {
    case i: HTMLImage => element.src = i.source
    case EmptyImage => element.src = ""
    case _ => throw new RuntimeException(s"Unsupported Image type: $image")
  }

  override protected def determineActualWidth: Double = image().width

  override protected def determineActualHeight: Double = image().height
}

object ImageView extends ImageViewTheme