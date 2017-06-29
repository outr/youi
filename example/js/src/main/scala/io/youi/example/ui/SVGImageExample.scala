package io.youi.example.ui

import io.youi.app.screen.UIScreen
import io.youi.component.{ImageView, RenderMode, Renderer}
import io.youi.dom._
import io.youi.image.SVGImage
import org.scalajs.dom.raw.SVGCircleElement
import org.scalajs.dom._

object SVGImageExample extends UIExampleScreen with UIScreen {
  override def name: String = "SVG Image"
  override def path: String = "/examples/svg-image.html"

  private val svgString =
    """
      |<svg height="100" width="100">
      |  <circle id="circle" cx="50" cy="50" r="40" stroke="black" stroke-width="3" fill="red" />
      |</svg>
    """.stripMargin

  override def createUI(): Unit = {
    container.children += new ImageView("/images/test2.svg") {
      position.left := 10.0
      position.top := 10.0

      event.click.on {
        size.width.static(size.width * 1.1)
        size.height.static(size.height * 1.1)
      }
    }

    /*container.children += new ImageView(svgString) {
      position.center := renderer.position.center
      position.middle := renderer.position.middle

      def toggleColor(): Unit = {
        val current = image().asInstanceOf[SVGImage]
        val circle = current.svg.byId[SVGCircleElement]("circle")
        if (circle.style.fill == "blue") {
          circle.style.fill = "red"
        } else {
          circle.style.fill = "blue"
        }
        reDraw.flag()
      }

      event.click.on(toggleColor())
    }*/
  }
}