package youi

import org.scalajs.dom._

object AnimationFrame extends UpdateSupport {
  override protected def run(): Unit = window.requestAnimationFrame(updateFunction)
}