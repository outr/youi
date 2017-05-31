package io.youi.component.extra

import io.youi.component.DrawableComponent
import io.youi.component.draw.{Drawable, Group}
import io.youi.component.draw.path.Path
import io.youi.style.Cursor
import reactify.Var

class RectangularSelection extends DrawableComponent {
  val enabled: Var[Boolean] = Var(true)
  val x1: Var[Double] = Var[Double](0.0)
  val y1: Var[Double] = Var[Double](0.0)
  val x2: Var[Double] = Var[Double](0.0)
  val y2: Var[Double] = Var[Double](0.0)

  def width: Double = x2 - x1
  def height: Double = y2 - y1

  // TODO: change the cursor if near a corner or edge
//  cursor := {
//    if ()
//  }

  size.measured.width := x2() + math.max(0.0, lineWidth() - 1.0)
  size.measured.height := y2() + math.max(0.0, lineWidth() - 1.0)

  drawable := {
    if (enabled() && width != 0.0 && height != 0.0) {
      Group(List(
        Some(createRectangle()),
        createEdge(x1, y1),
        createEdge(x2, y1),
        createEdge(x2, y2),
        createEdge(x1, y2)
      ).flatten)
    } else {
      Drawable.empty
    }
  }

//  private def near(x: Double, y: )

  protected def createRectangle(): Drawable = Path.begin.rect(x1(), y1(), width, height).close
  protected def createEdge(x: Double, y: Double): Option[Drawable] = None
}