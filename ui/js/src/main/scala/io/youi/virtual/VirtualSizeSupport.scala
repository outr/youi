package io.youi.virtual

trait VirtualSizeSupport {
  val virtualWidth: Var[Double] = Var[Double](1024.0)
  val virtualHeight: Var[Double] = Var[Double](768.0)
  val virtualMode: Var[VirtualMode] = Var[VirtualMode](VirtualMode.Bars)
  val virtual: VirtualSize = new VirtualSize(this)
  object actual {
    val width: Var[Double] = Var(ui.size.width)
    val height: Var[Double] = Var(ui.size.height)
  }

  implicit class DoubleVirtualPixels(d: Double) {
    def vx: Val[Double] = Val(vw() + virtual.xOffset())
    def vy: Val[Double] = Val(vh() + virtual.yOffset())
    def vw: Val[Double] = Val(d * virtual.widthMultiplier)
    def vh: Val[Double] = Val(d * virtual.heightMultiplier)
    def vf: Val[Double] = vw
  }

  implicit class IntVirtualPixels(i: Int) {
    def vx: Val[Double] = Val(vw() + virtual.xOffset())
    def vy: Val[Double] = Val(vh() + virtual.yOffset())
    def vw: Val[Double] = Val(i * virtual.widthMultiplier)
    def vh: Val[Double] = Val(i * virtual.heightMultiplier)
    def vf: Val[Double] = vw
  }
}