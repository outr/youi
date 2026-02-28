package youi.component.feature

import youi.component.Component
import youi.util.Measurer
import reactify.{Val, Var}

class PreferredSizeFeature(val component: Component) extends Feature with WidthFeature with HeightFeature {
  override protected def parent: FeatureParent = component

  protected val w: Var[Double] = Var(0.0)
  protected val h: Var[Double] = Var(0.0)

  override lazy val width: Val[Double] = w
  override lazy val height: Val[Double] = h

  component.measure.on(Measurer.measureHTML(component.element.outerHTML, component.element.style.width, component.element.style.height, w, h))
}