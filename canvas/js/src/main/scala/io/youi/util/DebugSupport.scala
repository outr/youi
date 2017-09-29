package io.youi.util

import io.youi._
import io.youi.component.{AbstractContainer, Component, Renderer}
import reactify.Var
import org.scalajs.dom._

import scala.concurrent.ExecutionContext.Implicits.global

class DebugSupport(renderer: Renderer = ui.renderer) {
  val enabled: Var[Boolean] = Var(false)

  ui.event.key.down.attach { evt =>
    if (enabled()) {
      evt.key match {
        case Key.F2 => {
          DebugWindow.toggle(renderer)
        }
        case _ => // Ignore
      }
    }
  }
}

object DebugWindow {
  private lazy val root = dom.create[html.Div]("div")

  root.style.display = "none"

  def toggle(renderer: Renderer): Unit = if (root.style.display != "block") {
    showFor(renderer)
  } else {
    close()
  }

  def showFor(renderer: Renderer): Unit = {
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
    renderer.children().foreach(drawChild(_, parent, renderer))
  }

  def close(): Unit = root.style.display = "none"

  private def drawChild(component: Component, parent: html.Element, renderer: Renderer): Unit = {
    val canvas = component.drawer.context.canvas

    val heading = dom.create[html.Element]("h3")
    heading.innerHTML = s"${component.toString} (size: ${canvas.width}x${canvas.height}, parent: ${component.parent()})"
    parent.appendChild(heading)

    val actions = dom.create[html.Div]("div")
    val invalidate = dom.create[html.Button]("button")
    invalidate.innerHTML = "Invalidate"
    invalidate.addEventListener("click", (_: Event) => {
      close()
      component.invalidate().foreach { _ =>
        showFor(renderer)
      }
    })
    actions.appendChild(invalidate)
    parent.appendChild(actions)

    canvas.style.border = "1px solid black"
    parent.appendChild(canvas)

    component match {
      case c: AbstractContainer => {
        val container = dom.create[html.Div]("div")
        container.style.marginLeft = "20px"
        container.style.border = "1px solid red"
        parent.appendChild(container)
        AbstractContainer.children(c).foreach(drawChild(_, container, renderer))
      }
      case _ => // Not a container
    }
  }
}