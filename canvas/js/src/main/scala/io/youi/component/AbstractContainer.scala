package io.youi.component

import io.youi.{BoundingBox, Compass, Context, WidgetContainer}
import io.youi.event.HitResult
import io.youi.ui
import io.youi.spatial.Point
import io.youi.theme.AbstractContainerTheme
import reactify._

import scala.annotation.tailrec

trait AbstractContainer extends Component with AbstractContainerTheme with WidgetContainer { self =>
  override type Child <: Component

  override lazy val theme: Var[_ <: AbstractContainerTheme] = Var(AbstractContainer)
  override protected lazy val childEntries: Var[Vector[Child]] = prop(Vector.empty, updatesTransform = true)

  override protected def defaultThemeParent = Some(theme)

  val drawOffscreenChildren: Var[Boolean] = Var(false)
  val cache: Var[Boolean] = Var(true)

  updateMeasured(
    width = if (childEntries().nonEmpty) childEntries().map(_.position.right()).max else 0.0,
    height = if (childEntries().nonEmpty) childEntries().map(_.position.bottom()).max else 0.0
  )

  override protected def shouldDraw: Boolean = super.shouldDraw && cache()

  override protected def preScale(context: Context): Unit = {}

  override def update(delta: Double): Unit = {
    super.update(delta)

    childEntries().foreach(_.update(delta))
  }

  override def drawToParent(parent: AbstractContainer, parentContext: Context): Unit = if (cache()) {
    super.drawToParent(parent, parentContext)
  } else {
    parentContext.save()
    parentContext.transform(this, multiply = true)
    drawInternal(parentContext)
    parentContext.restore()
  }

  override def draw(context: Context): Unit = {
    super.draw(context)

    // Draw cached canvases from each child
    val viewable = BoundingBox(-offset.x, -offset.y, -offset.x + size.width, -offset.y + size.height)
    childEntries.foreach { child =>
      if (child.visible()) {
        // TODO: replace bounding check with matrix point checks when figured out
        val drawable = if (drawOffscreenChildren()) {
          true
        } else {
          val bb = BoundingBox(child.position.left, child.position.top, child.position.right, child.position.bottom)
          bb.intersects(viewable)
        }
        if (drawable) {
          context.save()
          context.transform(child, multiply = !cache())
          context.translate(offset.x, offset.y)
          context.translate(padding.left, padding.top)
          context.translate(border.size(Compass.West), border.size(Compass.North))
          child.drawToParent(this, context)
          context.restore()
        }
      }
    }
  }

  override def hitTest(global: Point): HitResult = if (interactive() && visible()) {
    val children = childEntries()
    val lastIndex = children.length - 1
    val childResult = if (lastIndex >= 0) {
      childHitTest(global, lastIndex, children)
    } else {
      HitResult.Miss
    }
    childResult match {
      case HitResult.Miss => super.hitTest(global)
      case result => result
    }
  } else {
    HitResult.Miss
  }

  @tailrec
  private def childHitTest(global: Point, index: Int, children: Vector[Child]): HitResult = {
    val child = children(index)
    child.hitTest(global) match {
      case result: HitResult.Hit => result
      case HitResult.Miss if index == 0 => HitResult.Miss
      case HitResult.Miss => childHitTest(global, index - 1, children)
    }
  }
}

object AbstractContainer extends AbstractContainerTheme {
  def children(container: AbstractContainer): Val[Vector[Component]] = container.childEntries.asInstanceOf[Val[Vector[Component]]]
}