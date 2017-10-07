package io.youi.drawable

import reactify._

import scala.annotation.tailrec

trait Group extends Drawable {
  override lazy val modified: Var[Long] = Var(if (elements.nonEmpty) elements.map(_.modified()).max else 0L)

  protected def elements: List[Drawable]

  override def draw(context: Context, x: Double, y: Double): Unit = drawRecursive(context, x, y, elements)

  @tailrec
  private def drawRecursive(context: Context, x: Double, y: Double, elements: List[Drawable]): Unit = if (elements.nonEmpty) {
    elements.head.draw(context, x, y)
    drawRecursive(context, x, y, elements.tail)
  }
}

object Group {
  def apply(children: Drawable*): Group = new Group {
    override protected val elements: List[Drawable] = children.toList
  }
}