package io.youi.component

import io.youi._
import io.youi.event._
import io.youi.spatial.Point
import io.youi.theme.RendererTheme
import io.youi.{AnimationFrame, Cursor, Drawer, HTMLEvents}
import org.scalajs.dom.raw.{MouseEvent, TouchEvent}
import org.scalajs.dom.{document, html, raw}
import reactify._

class Renderer(canvas: html.Canvas) extends Container with RendererTheme {
  override protected[youi] lazy val drawer: Drawer = new Drawer(canvas, swapCanvases = false)

  override lazy val theme: Var[_ <: RendererTheme] = Var(Renderer)
  val htmlEvents: HTMLEvents = new HTMLEvents(document.body)
  override lazy val renderer: Val[Option[Renderer]] = Val(Some(this))
  val pointerTarget: Var[Option[Component]] = Var(None)
  val reDrawOnUpdate: Var[Boolean] = Var(false)

  htmlEvents.click.attach(pointerEvent(_, PointerEvent.Type.Click))
  htmlEvents.doubleClick.attach(pointerEvent(_, PointerEvent.Type.DoubleClick))
  htmlEvents.pointer.down.attach(pointerEvent(_, PointerEvent.Type.Down))
  htmlEvents.pointer.up.attach(pointerEvent(_, PointerEvent.Type.Up))
  htmlEvents.pointer.move.attach(pointerEvent(_, PointerEvent.Type.Move))
  htmlEvents.pointer.cancel.attach(pointerEvent(_, PointerEvent.Type.Cancel))
  htmlEvents.touch.start.attach(touchEvent(_, PointerEvent.Type.TouchStart))
  htmlEvents.touch.move.attach(touchEvent(_, PointerEvent.Type.TouchMove))
  htmlEvents.touch.cancel.attach(touchEvent(_, PointerEvent.Type.TouchCancel))
  htmlEvents.touch.end.attach(touchEvent(_, PointerEvent.Type.TouchEnd))
  ui.mouse.wheel.attach(wheelEvent)
  cursor := pointerTarget().map(_.cursor()).getOrElse(Cursor.Auto)      // Renderer's cursor should reflect the pointer target's cursor
  cursor.attach(c => canvas.style.cursor = c.value)

  override protected def determineActualVisibility = visible

  private val globalPoint = Point.mutable()
  private def pointerEvent(evt: MouseEvent, `type`: PointerEvent.Type): Unit = if (visible()) {
    val rect = canvas.getBoundingClientRect()
    globalPoint.set(evt.clientX - rect.left, evt.clientY - rect.top)
    val result = hitTest(globalPoint) match {
      case HitResult.Miss => None
      case HitResult.Hit(l, c) => Some(l -> c)
    }
    val eventType = if (HTMLEvents.hasPointerSupport) HTMLEventType.Pointer else HTMLEventType.Mouse
    val newTarget = result.map(_._2)
    if (pointerTarget() != newTarget) {
      pointerTarget().foreach { component =>
        val local = component.localize(globalPoint)
        val event = PointerEvent(component, PointerEvent.Type.Exit, local.x, local.y, globalPoint.x, globalPoint.y, evt, eventType)
        hierarchicalEvent(component, _.event.pointer, event)
      }
      pointerTarget := newTarget
      pointerTarget().foreach { component =>
        val local = component.localize(globalPoint)
        val event = PointerEvent(component, PointerEvent.Type.Enter, local.x, local.y, globalPoint.x, globalPoint.y, evt, eventType)
        hierarchicalEvent(component, _.event.pointer, event)
      }
    }
    result.foreach {
      case (local, component) => {
        val event = PointerEvent(component, `type`, local.x, local.y, globalPoint.x, globalPoint.y, evt, eventType)
        hierarchicalEvent(component, _.event.pointer, event)
      }
    }
  }
  private def touchEvent(evt: TouchEvent, `type`: PointerEvent.Type): Unit = if (visible()) {
    val rect = canvas.getBoundingClientRect()
    if (`type` == PointerEvent.Type.TouchMove) {
      val latest = evt.changedTouches.item(evt.changedTouches.length - 1)
      globalPoint.set(latest.clientX - rect.left, latest.clientY - rect.top)
    }
    val result = hitTest(globalPoint) match {
      case HitResult.Miss => None
      case HitResult.Hit(l, c) => Some(l -> c)
    }
    val eventType = HTMLEventType.Touch
    val newTarget = result.map(_._2)
    if (pointerTarget() != newTarget) {
      pointerTarget().foreach { component =>
        val local = component.localize(globalPoint)
        val event = PointerEvent(component, PointerEvent.Type.Exit, local.x, local.y, globalPoint.x, globalPoint.y, evt, eventType)
        hierarchicalEvent(component, _.event.pointer, event)
      }
      pointerTarget := newTarget
      pointerTarget().foreach { component =>
        val local = component.localize(globalPoint)
        val event = PointerEvent(component, PointerEvent.Type.Enter, local.x, local.y, globalPoint.x, globalPoint.y, evt, eventType)
        hierarchicalEvent(component, _.event.pointer, event)
      }
    }
    result.foreach {
      case (local, component) => {
        val event = PointerEvent(component, `type`, local.x, local.y, globalPoint.x, globalPoint.y, evt, eventType)
        hierarchicalEvent(component, _.event.pointer, event)
      }
    }
  }
  private def wheelEvent(delta: WheelDelta): Unit = if (visible()) {
    val rect = canvas.getBoundingClientRect()
    globalPoint.set(delta.htmlEvent.clientX - rect.left, delta.htmlEvent.clientY - rect.top)
    hitTest(globalPoint) match {
      case HitResult.Miss => // Nothing
      case HitResult.Hit(local, component) => {
        val event = WheelEvent(component, local.x, local.y, globalPoint.x, globalPoint.y, delta)
        hierarchicalEvent(component, _.event.pointer, event)
      }
    }
  }

  def hierarchicalEvent[E <: Event](component: Component, handler: Component => Channel[E], event: E): Unit = {
    val channel = handler(component)
    channel := event
    if (event.propagate) {
      component.parent().foreach(hierarchicalEvent(_, handler, event))
    }
  }

  override protected def defaultThemeParent = Some(theme)

  visible.attach {
    case true => canvas.style.display = "block"
    case false => canvas.style.display = "none"
  }

  if (Option(canvas.parentElement).isEmpty) {
    canvas.style.position = "absolute"
    canvas.style.left = "0px"
    canvas.style.top = "0px"
    visible := false

    document.body.appendChild(canvas)

  }
  AnimationFrame.delta.attach(update)

  def apply(): html.Canvas = canvas

  override def update(delta: Double): Unit = {
    if (reDrawOnUpdate()) reDraw.flag()

    super.update(delta)
  }
}

object Renderer extends RendererTheme