package io.youi.component.extras

class CanvasImageViewImplementation(ratio: Var[Double] = ui.ratio) extends ImageViewImplementation {
  override def createElement(): html.Element = dom.create[html.Canvas]("canvas")

  def apply(component: ImageView, image: Image): Unit = if (component.size.width() > 0.0 && component.size.height() > 0.0) {
    val element = HTMLComponent.element(component).asInstanceOf[html.Canvas]
    val context = component.store.getOrSet("canvasContext", new Context(element, ratio()))
    image.draw(context, 0.0, 0.0, component.size.width(), component.size.height())
  }

  override def updateSize(component: ImageView, width: Double, height: Double): Unit = {
    val element = HTMLComponent.element(component).asInstanceOf[html.Image]
    element.width  = math.round(width * ratio()).toInt
    element.height = math.round(height * ratio()).toInt
    apply(component, component.image())
  }
}
