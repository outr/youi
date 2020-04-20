package io.youi.component.extras

object HTMLImageViewImplementation extends ImageViewImplementation {
  override def createElement(): html.Element = dom.create[html.Image]("img")

  override def apply(component: ImageView, image: Image): Unit = {
    val element = HTMLComponent.element(component).asInstanceOf[html.Image]
    image match {
      case i: HTMLImage => element.src = i.source
      case EmptyImage => element.src = ""
//      case i: SVGImage => element.src = s"data:image/svg+xml;charset=utf-8,${i.toXML}"
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
