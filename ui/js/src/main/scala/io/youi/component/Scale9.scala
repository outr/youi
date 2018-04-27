package io.youi.component

import io.youi.LazyUpdate
import io.youi.component.extras.HTMLComponent
import io.youi.image.Image
import io.youi.theme.ContainerTheme
import org.scalajs.dom.html
import reactify._

import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

class Scale9(element: html.Element = HTMLComponent.create[html.Div]("div")) extends HTMLContainer[ImageView](element) { self =>
  override lazy val theme: Var[_ <: ContainerTheme] = Var(Container)
  override def `type`: String = "Scale9"

  def this(image: Image) = {
    this()
    this.image := image
  }

  val image: Var[Image] = Var(Image.empty)
  val x1: Var[Double] = Var(0.0)
  val y1: Var[Double] = Var(0.0)
  val x2: Var[Double] = Var(0.0)
  val y2: Var[Double] = Var(0.0)

  val leftWidth: Val[Double] = x1.asVal
  val rightWidth: Val[Double] = Val(image.width - x2)
  val centerWidth: Val[Double] = Val(size.width - (leftWidth + rightWidth))
  val topHeight: Val[Double] = y1.asVal
  val bottomHeight: Val[Double] = Val(image.height - y2)
  val middleHeight: Val[Double] = Val(size.height - (topHeight + bottomHeight))

  size.measured.width := image.width
  size.measured.height := image.height

  private val topLeft = new ImageView {
    position.left := 0.0
    position.top := 0.0
    size.width := x1
    size.height := y1
  }
  private val top = new ImageView {
    position.left := x1
    position.top := 0.0
    size.width := centerWidth
    size.height := y1
  }
  private val topRight = new ImageView {
    position.right := self.size.width
    position.top := 0.0
    size.width := rightWidth
    size.height := y1
  }
  private val left = new ImageView {
    position.left := 0.0
    position.top := y1
    size.width := x1
    size.height := middleHeight
  }
  private val center = new ImageView {
    position.left := x1
    position.top := y1
    size.width := centerWidth
    size.height := middleHeight
  }
  private val right = new ImageView {
    position.right := self.size.width
    position.top := y1
    size.width := rightWidth
    size.height := middleHeight
  }
  private val bottomLeft = new ImageView {
    position.left := 0.0
    position.bottom := self.size.height
    size.width := x1
    size.height := bottomHeight
  }
  private val bottom = new ImageView {
    position.left := x1
    position.bottom := self.size.height
    size.width := centerWidth
    size.height := bottomHeight
  }
  private val bottomRight = new ImageView {
    position.right := self.size.width
    position.bottom := self.size.height
    size.width := rightWidth
    size.height := bottomHeight
  }

  private lazy val reClip = LazyUpdate {
    if (x1 + y1 + x2 + y2 > 0.0) {
      def update(view: ImageView, x1: Double, y1: Double, x2: Double, y2: Double): Unit = {
        image().clip(x1, y1, x2, y2).onComplete {
          case Success(clipped) => view.image := clipped
          case Failure(t) => scribe.error(t)
        }
      }

      update(topLeft, 0.0, 0.0, x1, y1)
      update(top, x1, 0.0, x2, y1)
      update(topRight, x2, 0.0, image.width, y1)
      update(left, 0.0, y1, x1, y2)
      update(center, x1, y1, x2, y2)
      update(right, x2, y1, image.width, y2)
      update(bottomLeft, 0.0, y2, x1, image.height)
      update(bottom, x1, y2, x2, image.height)
      update(bottomRight, x2, y2, image.width, image.height)
    }
  }

  image.on(reClip.flag())
  (x1 and y1 and x2 and y2).on(reClip.flag())

  children ++= List(topLeft, top, topRight, left, center, right, bottomLeft, bottom, bottomRight)

  override def update(delta: Double): Unit = {
    super.update(delta)

    reClip.update()
  }
}
