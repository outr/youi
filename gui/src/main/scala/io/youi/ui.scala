package io.youi

import io.youi.component.Container
import io.youi.component.support.SizeSupport
import io.youi.component.types.Prop
import org.scalajs.dom._

object ui extends Container(document.body) with SizeSupport {
  lazy val title: Prop[String] = new Prop(document.title, document.title_=)

  measure()
  window.addEventListener("resize", (_: Event) => {
    measure()
  })

  override protected def measure(): Unit = {
    super.measure()

    size.width @= window.innerWidth
    size.height @= window.innerHeight
  }
}