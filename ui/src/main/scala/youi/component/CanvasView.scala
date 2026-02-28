package youi.component

import youi._
import reactify._
import youi.component.feature.{FeatureParent, HeightFeature, WidthFeature}
import youi.component.types.Prop
import youi.drawable.Context
import org.scalajs.dom.html

abstract class CanvasView(canvas: html.Canvas = dom.create.canvas) extends Component(canvas) with WidthFeature with HeightFeature {
  protected lazy val context: Context = new Context(canvas, ratio)

  override def parent: FeatureParent = this

  override lazy val width: Prop[Double] = new Prop[Double](0, d => canvas.style.width = s"${d}px", measureComponent, render)
  override lazy val height: Prop[Double] = new Prop[Double](0, d => canvas.style.height = s"${d}px", measureComponent, render)

  protected def draw(content: Context): Unit

  def render(): Unit = {
    context.reset()

    canvas.width = math.ceil(width * ratio).toInt
    canvas.height = math.ceil(height * ratio).toInt
    draw(context)
  }
}