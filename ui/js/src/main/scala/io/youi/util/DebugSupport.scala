package io.youi.util

import io.youi._
import io.youi.component.{AbstractContainer, Component, Container}
import io.youi.drawable.Context
import org.scalajs.dom._
import reactify.Var

import scala.concurrent.ExecutionContext.Implicits.global

class DebugSupport(renderer: Renderer = Renderer) {
  val enabled: Var[Boolean] = Var(false)

  ui.event.key.down.attach { evt =>
    if (enabled()) {
      evt.key match {
        case Key.F2 => renderer.drawable() match {
          case container: Container => DebugWindow.toggle(container)
          case _ => // Ignore
        }
        case _ => // Ignore
      }
    }
  }
}

object DebugWindow {
  private lazy val root = dom.create[html.Div]("div")

  root.style.display = "none"

  def toggle(container: Container): Unit = if (root.style.display != "block") {
    showFor(container: Container)
  } else {
    close()
  }

  def showFor(container: Container): Unit = {
    root.innerHTML = ""
    root.style.position = "absolute"
    root.style.width = s"${ui.width().toInt}px"
    root.style.height = s"${ui.height().toInt}px"
    root.style.zIndex = "999999"
    root.style.left = "0px"
    root.style.top = "0px"
    root.style.overflow = "auto"
    root.style.display = "block"
    root.style.backgroundColor = "white"
    document.body.appendChild(root)
    val parent = root
    container.children().foreach(drawChild(_, parent, container))
  }

  def close(): Unit = root.style.display = "none"

  private def drawChild(component: Component, parent: html.Element, container: Container): Unit = {
    val canvas = CanvasPool(component.size.width() * ui.ratio, component.size.height() * ui.ratio)
    val context = new Context(canvas, ui.ratio)

    val heading = dom.create[html.Element]("h3")
    heading.innerHTML = s"${component.toString} (size: ${canvas.width}x${canvas.height}, parent: ${component.parent()})"
    parent.appendChild(heading)

    val actions = dom.create[html.Div]("div")
    val invalidate = dom.create[html.Button]("button")
    invalidate.innerHTML = "Invalidate"
    invalidate.addEventListener("click", (_: Event) => {
      close()
      component.invalidate().foreach { _ =>
        showFor(container)
      }
    })
    actions.appendChild(invalidate)
    parent.appendChild(actions)

    canvas.style.border = "1px solid black"
    canvas.style.width = s"${math.ceil(component.size.width())}px"
    canvas.style.height = s"${math.ceil(component.size.height())}px"
    canvas.width = math.ceil(component.size.width * ui.ratio).toInt
    canvas.height = math.ceil(component.size.height * ui.ratio).toInt
    component.draw(context, translate = false)
    parent.appendChild(canvas)

    component match {
      case c: AbstractContainer => {
        val div = dom.create[html.Div]("div")
        div.style.marginLeft = "20px"
        div.style.border = "1px solid red"
        parent.appendChild(div)
        AbstractContainer.children(c).foreach(drawChild(_, div, container))
      }
      case _ => // Not a container
    }
  }
}