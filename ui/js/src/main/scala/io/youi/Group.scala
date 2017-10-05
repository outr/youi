package io.youi

import reactify._

import scala.annotation.tailrec

trait Group extends Drawable {
  override lazy val modified: Var[Long] = Var(if (elements.nonEmpty) elements.map(_.modified()).max else 0L)

  protected def elements: List[Drawable]

  override def draw(context: Context): Unit = drawRecursive(context, elements)

  @tailrec
  private def drawRecursive(context: Context, elements: List[Drawable]): Unit = if (elements.nonEmpty) {
    elements.head.draw(context)
    drawRecursive(context, elements.tail)
  }
}

object Group {
  def apply(children: Drawable*): Group = new Group {
    override protected val elements: List[Drawable] = children.toList
  }
}