//package io.youi.util
//
//import io.youi.component.AbstractContainer
//import io.youi.hypertext.Label
//import io.youi.{AnimationFrame, Key, Renderer, ui}
//import org.scalajs.dom._
//import reactify.Var
//
//class DebugSupport {
//  val renderer: Var[Renderer] = Var(Renderer)
//  val enabled: Var[Boolean] = Var(false)
//
//  private val stats = new Label {
//    text := renderer.stats.toString
//    font.size := 12.0
//    font.family := "sans-serif"
//    position.right := ui.width - 10.0
//    position.top := 10.0
//    element.style.zIndex = "1000"
//
//    AnimationFrame.delta.attach(update)
//    document.body.appendChild(element)
//  }
//
//  renderer.stats.renders.on {
//    stats.text := renderer.stats.toString
//  }
//
//  stats.visible := enabled
//
//  ui.event.key.down.attach { evt =>
//    if (enabled()) {
//      evt.key match {
//        case Key.F2 => renderer.drawable() match {
//          case container: AbstractContainer => DebugWindow.toggle(container)
//          case _ => scribe.warn(s"Renderer's drawable is not a Container!") // Ignore
//        }
//        case _ => // Ignore
//      }
//    }
//  }
//}