package io.youi

object AnimationFrame extends UpdateSupport {
  override protected def run(): Unit = window.requestAnimationFrame(updateFunction)
}