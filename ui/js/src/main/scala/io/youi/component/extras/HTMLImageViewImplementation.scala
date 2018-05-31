package io.youi.component.extras

import io.youi.component.ImageView
import io.youi.dom
import io.youi.image._
import org.scalajs.dom.html

import scala.concurrent.ExecutionContext.Implicits.global

object HTMLImageViewImplementation extends ImageViewImplementation {
  override def createElement(): html.Element = dom.create[html.Image]("img")

  override def apply(component: ImageView, image: Image): Unit = {
    val element = HTMLComponent.element(component).asInstanceOf[html.Image]
    image match {
      case i: HTMLImage => element.src = i.source
      case EmptyImage => element.src = ""
      case i: CanvasImage => i.toDataURL.foreach { url =>
        element.src = url
      }
      case _ => throw new RuntimeException(s"Unsupported Image type: $image")
    }
  }

  override def updateSize(component: ImageView, width: Double, height: Double): Unit = {
    val element = HTMLComponent.element(component).asInstanceOf[html.Image]
    element.width  = math.round(width).toInt
    element.height = math.round(height).toInt
  }
}
