package io.youi.component.extras

trait ImageViewImplementation {
  def createElement(): html.Element

  def apply(component: ImageView, image: Image): Unit

  def updateSize(component: ImageView, width: Double, height: Double): Unit
}