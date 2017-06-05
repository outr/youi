package io.youi.component.draw

import com.outr.CanvgImplicits._
import io.youi.component.Component
import io.youi.dom
import io.youi.dom._
import org.scalajs.dom.html
import org.scalajs.dom.raw.{CanvasRenderingContext2D, SVGSVGElement}

class SVGDrawable(val svg: SVGSVGElement, x: Double, y: Double) extends Drawable {
  override def draw(component: Component, context: CanvasRenderingContext2D): Unit = {
    context.drawSvg(svg.outerHTML, x, y, width, height)
  }

  def width: Double = svg.width.animVal.value
  def height: Double = svg.height.animVal.value

  override def boundingBox: BoundingBox = BoundingBox(x, y, x + width, y + height)
}

object SVGDrawable {
  def apply(svg: String, x: Double = 0.0, y: Double = 0.0): SVGDrawable = {
    val div = dom.create[html.Div]("div")
    div.innerHTML = svg
    val tag = div.oneByTag[SVGSVGElement]("svg")
    new SVGDrawable(tag, x, y)
  }
}