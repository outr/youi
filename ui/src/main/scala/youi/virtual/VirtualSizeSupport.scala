package youi.virtual

import youi.component.Component
import reactify.{Val, Var}

trait VirtualSizeSupport {
  this: Component =>

  val virtualWidth: Var[Double] = Var(1024.0)
  val virtualHeight: Var[Double] = Var(768.0)
  val virtualMode: Var[VirtualMode] = Var(VirtualMode.Bars)

  object actual {
    val width: Val[Double] = Val(size.width())
    val height: Val[Double] = Val(size.height())
  }

  lazy val virtual: VirtualSize = VirtualSize.withActualSize(
    virtualWidth(), virtualHeight(),
    actual.width, actual.height,
    virtualMode()
  )

  implicit class VirtualDouble(d: Double) {
    def vx: Val[Double] = virtual.vx(d)
    def vy: Val[Double] = virtual.vy(d)
    def vw: Val[Double] = virtual.vw(d)
    def vh: Val[Double] = virtual.vh(d)
    def vf: Val[Double] = virtual.vw(d)
  }
}
