package youi.example.ui

import rapid.Task
import youi._
import youi.component.ImageView
import youi.event.EventSupport
import youi.example.screen.UIExampleScreen
import youi.image.{Image, SVGImage}
import org.scalajs.dom.SVGCircleElement
import spice.net._

class SVGImageExample extends UIExampleScreen {
  override def title: String = "SVG Image"
  override def path: URLPath = path"/examples/svg-image.html"

  private val svgString =
    """
      |<svg height="100" width="100">
      |  <circle id="circle" cx="50" cy="50" r="40" stroke="black" stroke-width="3" fill="red" />
      |</svg>
    """.stripMargin

  override def createUI(): Task[Unit] = for {
    tiger  <- Image("/images/tiger.svg")
    circle <- SVGImage(svgString)
  } yield {
    container.children += new ImageView {
      image @= tiger
      position.left @= 10.0
      position.top @= 10.0
    }

    val circleView = new ImageView with EventSupport {
      image @= circle
      position.center := container.size.center
      position.middle := container.size.middle

      event.click.on {
        circle.modify { svg =>
          val c = svg.getElementById("circle").asInstanceOf[SVGCircleElement]
          if (c.style.fill == "blue") c.style.fill = "red" else c.style.fill = "blue"
        }.flatMap { _ =>
          // Force ImageView to re-read the updated canvas by cycling through empty
          image @= Image.empty
          circle.resize(circle.width, circle.height)
        }.map { refreshed =>
          image @= refreshed
        }.startUnit()
      }
    }
    container.children += circleView
  }
}
