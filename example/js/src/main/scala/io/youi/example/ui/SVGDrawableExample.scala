package io.youi.example.ui

import io.youi.app.screen.UIScreen
import io.youi.component.DrawableComponent
import io.youi.component.draw.SVGDrawable
import io.youi.dom._
import org.scalajs.dom.raw.SVGCircleElement

object SVGDrawableExample extends UIExampleScreen with UIScreen {
  override def name: String = "SVG Drawable"
  override def path: String = "/examples/svg-drawable.html"

  private val svgString =
    """
      |<svg height="100" width="100">
      |  <circle id="circle" cx="50" cy="50" r="40" stroke="black" stroke-width="3" fill="red" />
      |</svg>
    """.stripMargin

  override def createUI(): Unit = {
    container.children += new DrawableComponent {
      SVGDrawable.fromPath("/images/tiger.svg").foreach { svg =>
        drawable := svg
      }

      position.left := 10.0
      position.top := 10.0
    }

    val svg = SVGDrawable(svgString)
    container.children += new DrawableComponent {
      drawable := svg
      position.center := renderer.position.center
      position.middle := renderer.position.middle

      val circle: SVGCircleElement = svg.svg.byId[SVGCircleElement]("circle")

      event.click.attach { _ =>
        if (circle.style.fill == "blue") {
          circle.style.fill = "red"
        } else {
          circle.style.fill = "blue"
        }
        reDraw.flag()
      }
    }
  }
}