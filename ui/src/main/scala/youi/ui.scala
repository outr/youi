package youi

import youi.component.Container
import youi.component.feature.SizeFeature
import youi.component.support.MarginSupport
import youi.component.types.Prop
import youi.event.{EventSupport, Swipe}
import youi.spatial.Size
import youi.task.TaskSupport
import org.scalajs.dom._
import reactify._

import scala.scalajs.js

object ui extends Container(document.body) with EventSupport with TaskSupport with MarginSupport {
  override lazy val title: Prop[String] = new Prop(document.title, document.title_=)
  lazy val swipe: Swipe = new Swipe(ui, ui.event, onlyMobile = true)
  val windowMeasurer: Var[Size => Size] = Var(_.set(measuredWidth, measuredHeight))

  private val mutableSize = Size.mutable()

  override val size: SizeFeature = new SizeFeature(this)(setWidth = _ => (), setHeight = _ => ())

  document.documentElement.asInstanceOf[html.Element].style.overflow = "hidden"
  element.style.overflow = "hidden"

  measure.on {
    try {
      val measured = windowMeasurer()(mutableSize)
      size.width @= measured.width
      size.height @= measured.height
    } catch {
      case t: Throwable => ErrorSupport.error @= t
    }
  }

  measure.trigger()
  window.addEventListener("resize", (_: Event) => {
    measure.trigger()
  })

  // ResizeObserver catches viewport changes that window.resize misses
  // (e.g. Chrome DevTools device toolbar toggles)
  if (js.typeOf(js.Dynamic.global.ResizeObserver) != "undefined") {
    val observer = new ResizeObserver((_, _) => {
      measure.trigger()
    })
    observer.observe(document.documentElement.asInstanceOf[html.Element])
  }

  AnimationFrame.delta.attach { d =>
    update(d)
  }

  def measuredWidth: Double = window.innerWidth - (margin.left + margin.right)
  def measuredHeight: Double = window.innerHeight - (margin.top + margin.bottom)
}