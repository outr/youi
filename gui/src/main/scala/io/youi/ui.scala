package io.youi

import io.youi.component.Container
import io.youi.component.support.SizeSupport
import io.youi.component.types.Prop
import io.youi.event.{EventSupport, Swipe}
import io.youi.spatial.Size
import io.youi.task.TaskSupport
import org.scalajs.dom._
import reactify.Var

object ui extends Container(document.body) with SizeSupport with EventSupport with TaskSupport {
  override lazy val title: Prop[String] = new Prop(document.title, document.title_=)
  lazy val swipe: Swipe = new Swipe(ui, ui.event, onlyTouch = true)
  val windowMeasurer: Var[Size => Size] = Var(_.set(window.innerWidth, window.innerHeight))

  private val mutableSize = Size.mutable()

  measure.on {
    val measured = windowMeasurer()(mutableSize)
    size.width @= measured.width
    size.height @= measured.height
  }

  measure.trigger()
  window.addEventListener("resize", (_: Event) => {
    measure.trigger()
  })

  AnimationFrame.delta.attach { d =>
    update(d)
  }
}