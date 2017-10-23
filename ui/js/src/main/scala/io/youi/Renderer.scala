package io.youi

import io.youi.component.Component
import io.youi.drawable.{Context, Drawable}
import io.youi.event._
import io.youi.spatial.Point
import io.youi.util.CanvasPool
import org.scalajs.dom.html
import org.scalajs.{dom => jsdom}
import reactify.{Channel, Var}

class Renderer(val canvas: html.Canvas = CanvasPool(1.0, 1.0), renderWidth: => Double, renderHeight: => Double) extends Updates {
  private lazy val context: Context = new Context(canvas, ratio())

  val ratio: Var[Double] = Var(ui.ratio)
  val width: Var[Double] = Var(renderWidth)
  val height: Var[Double] = Var(renderHeight)
  val visible: Var[Boolean] = Var(true)
  val drawable: Var[Drawable] = Var(Drawable.None)
  val modified: Var[Long] = Var(drawable.modified)
  val htmlEvents: HTMLEvents = new HTMLEvents(jsdom.document.body)
  val pointerTarget: Var[Option[Component]] = Var(None)
  val cursor: Var[Cursor] = Var(Cursor.Default)

  lazy val render: LazyUpdate = LazyUpdate {
    if (visible()) {
      context.clear()
      drawable.draw(context, 0.5, 0.5)
    }
  }

  protected def init(): Unit = {
    ratio.and(width).and(height).on(updateSize())
    visible.attach {
      case true => canvas.style.display = "block"
      case false => canvas.style.display = "none"
    }
    AnimationFrame.delta.attach(update)

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
    Mouse.wheel.attach(wheelEvent)
    cursor := pointerTarget().map(_.cursor()).getOrElse(Cursor.Auto)      // Renderer's cursor should reflect the pointer target's cursor
    cursor.attach(c => canvas.style.cursor = c.value)

    drawable.on(render.flag())
    visible.on(render.flag())
    modified.on(render.flag())

    updateSize()
  }

  override def update(delta: Double): Unit = {
    super.update(delta)

    drawable() match {
      case u: Updatable => u.update(delta)
      case _ => // Not updatable
    }

    render.update()
  }

  private def updateSize(): Unit = {
    canvas.width = math.ceil(width * ratio).toInt
    canvas.height = math.ceil(height * ratio).toInt
    canvas.style.width = s"${math.ceil(width)}px"
    canvas.style.height = s"${math.ceil(height)}px"
    render.flag()
  }

  private val globalPoint = Point.mutable()
  private def pointerEvent(evt: jsdom.MouseEvent, `type`: PointerEvent.Type): Unit = if (visible()) {
    drawable() match {
      case c: Component => {
        val rect = canvas.getBoundingClientRect()
        globalPoint.set(evt.clientX - rect.left, evt.clientY - rect.top)
        val result = c.hitTest(globalPoint) match {
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
      case _ => // Not a Component, no hit test possible
    }
  }
  private def touchEvent(evt: jsdom.TouchEvent, `type`: PointerEvent.Type): Unit = if (visible()) {
    drawable() match {
      case c: Component => {
        val rect = canvas.getBoundingClientRect()
        if (`type` == PointerEvent.Type.TouchMove) {
          val latest = evt.changedTouches.item(evt.changedTouches.length - 1)
          globalPoint.set(latest.clientX - rect.left, latest.clientY - rect.top)
        }
        val result = c.hitTest(globalPoint) match {
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
      case _ => // Not a Component, no hit test possible
    }
  }
  private def wheelEvent(delta: WheelDelta): Unit = if (visible()) {
    drawable() match {
      case c: Component => {
        val rect = canvas.getBoundingClientRect()
        globalPoint.set(delta.htmlEvent.clientX - rect.left, delta.htmlEvent.clientY - rect.top)
        c.hitTest(globalPoint) match {
          case HitResult.Miss => // Nothing
          case HitResult.Hit(local, component) => {
            val event = WheelEvent(component, local.x, local.y, globalPoint.x, globalPoint.y, delta)
            hierarchicalEvent(component, _.event.pointer, event)
          }
        }
      }
      case _ => // Not a Component, no hit test possible
    }
  }

  def hierarchicalEvent[E <: Event](component: Component, handler: Component => Channel[E], event: E): Unit = {
    val channel = handler(component)
    channel := event
    if (event.propagate) {
      component.parent().foreach(hierarchicalEvent(_, handler, event))
    }
  }

  init()
}

object Renderer extends Renderer(CanvasPool(1.0, 1.0), ui.width, ui.height) {
  override protected def init(): Unit = {
    super.init()

    canvas.style.position = "absolute"
    canvas.style.left = "0px"
    canvas.style.top = "0px"
    visible := false
    jsdom.document.body.appendChild(canvas)
  }
}