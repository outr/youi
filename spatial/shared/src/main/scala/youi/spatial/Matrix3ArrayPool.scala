package youi.spatial

import youi.util.ObjectPool

object Matrix3ArrayPool extends ObjectPool[Array[Double]] {
  override protected def create(): Array[Double] = new Array[Double](9)

  override def use[R](f: (Array[Double]) => R): R = super.use(f)
}
