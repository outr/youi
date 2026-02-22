package io.youi.component.feature

import io.youi.component.Component
import io.youi.spatial.Size
import reactify.{Val, Var}

class MeasuredFeature(val component: Component) extends Feature with WidthFeature with HeightFeature {
  override protected def parent: FeatureParent = component

  protected val w: Var[Double] = Var(0.0)
  protected val h: Var[Double] = Var(0.0)

  override lazy val width: Val[Double] = w
  override lazy val height: Val[Double] = h

  component.measure.on {
    val rect = component.element.getBoundingClientRect()
    w @= rect.width
    h @= rect.height
  }

  // Always win the "width"/"height" slots regardless of trait mixin order.
  // SizeFeature uses addFeatureIfAbsent (first-wins), so if it ran first we
  // override it here; if MeasuredFeature ran first the no-op soft-register in
  // SizeFeature will leave us in place.
  FeatureParent.add("width", component, this)
  FeatureParent.add("height", component, this)
}

object MeasuredFeature {
  def apply(component: Component): Size = {
    val rect = component.element.getBoundingClientRect()
    Size(rect.width, rect.height)
  }
}