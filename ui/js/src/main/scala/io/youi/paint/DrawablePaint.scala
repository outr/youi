package io.youi.paint

import io.youi.{Updates, ui}
import io.youi.drawable.{Context, Drawable}
import io.youi.util.CanvasPool
import org.scalajs.dom.CanvasPattern

class DrawablePaint[D <: Drawable](drawable: D,
                                   repetition: Repetition,
                                   width: => Double,
                                   height: => Double,
                                   x: => Double,
                                   y: => Double,
                                   ratio: => Double) extends PatternPaint {
  modified := drawable.modified()

  override def update(delta: Double): Unit = {
    super.update(delta: Double)

    drawable match {
      case u: Updates => u.update(delta)
      case _ => // Not updatable
    }
  }

  override def createPattern(): CanvasPattern = {
    val r = ratio
    CanvasPool.withCanvas(width * r, height * r) { canvas =>
      val context = new Context(canvas, r)
      drawable.draw(context, x, y)
      context.createPattern(repetition)
    }
  }
}