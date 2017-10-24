package io.youi.component.mixins

import io.youi.component.Component
import io.youi.event.{Events, HitResult}
import io.youi.spatial.{Matrix3, Point}

trait Interactivity {
  this: Component =>

  lazy val event: Events = new Events(this)

  def hitTest(global: Point, adjustX: Double, adjustY: Double): HitResult = if (interactive() && visible()) {
    updateMatrix()      // TODO: this seems expensive to do every hit test
    val local = localize(global)
    local.set(local.x - adjustX, local.y - adjustY)
    if (isHit(local)) {
      HitResult.Hit(local, this)
    } else {
      HitResult.Miss
    }
  } else {
    HitResult.Miss
  }

  def localize(global: Point): Point = {
    Interactivity.tempPoint.set(global.x, global.y)
    val matrix = Interactivity.tempMatrix.set(this.matrix.world).inv()
    matrix.localize(Interactivity.tempPoint)
  }

  def isHit(local: Point): Boolean = local.x >= 0.0 && local.y >= 0.0 && local.x <= size.width() && local.y <= size.height()
}

object Interactivity {
  private val tempMatrix = Matrix3.Identity.mutable
  private val tempPoint = Point.mutable()
}