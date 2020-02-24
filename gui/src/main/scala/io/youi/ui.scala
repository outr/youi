package io.youi

import io.youi.component.Container
import io.youi.component.support.SizeSupport
import io.youi.component.types.Prop
import io.youi.event.EventSupport
import io.youi.task.TaskSupport
import org.scalajs.dom._

object ui extends Container(document.body) with SizeSupport with EventSupport with TaskSupport {
  lazy val title: Prop[String] = new Prop(document.title, document.title_=)

  measure()
  window.addEventListener("resize", (_: Event) => {
    measure()
  })

  AnimationFrame.delta.attach { d =>
    update(d)
  }

  override protected def measure(): Unit = {
    super.measure()

    size.width @= window.innerWidth
    size.height @= window.innerHeight
  }
}