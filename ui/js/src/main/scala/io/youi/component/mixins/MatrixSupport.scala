package io.youi.component.mixins

import io.youi.component.Component
import io.youi.spatial.{Matrix3, MutableMatrix3}

trait MatrixSupport {
  this: Component =>

  object matrix {
    val local: MutableMatrix3 = Matrix3.Identity.mutable
    val world: MutableMatrix3 = Matrix3.Identity.mutable
  }

  protected def updateMatrix(): Unit = {
    // Prepare local matrix
    updateLocalMatrix()

    // Prepare the world matrix
    matrix.world.set(parent().map(_.matrix.world).getOrElse(Matrix3.Identity))
    matrix.world *= matrix.local

    invalidate()
  }

  protected def updateLocalMatrix(): Unit = {
    matrix.local.set(Matrix3.Identity)
    matrix.local.translate(position.x(), position.y())
    matrix.local.translate(pivot.x(), pivot.y())
    matrix.local.rotate(rotation())
    matrix.local.translate(-pivot.x(), -pivot.y())
  }
}