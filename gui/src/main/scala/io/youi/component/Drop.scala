package io.youi.component

import cats.effect.IO
import io.youi._
import io.youi.component.support._
import io.youi.component.types.{Display, DropType, Overflow, PositionType}
import org.scalajs.dom.html
import reactify._

class Drop extends Collapsible with PositionSupport with BorderSupport with Initializable {
  private var showing: Option[Component] = None
  private var `type`: DropType = DropType.Auto

  override lazy val container: Container with MeasuredSupport with PaddingSupport = new Container with MeasuredSupport with PaddingSupport
  val offsetDown: Var[Double] = Var(0.0)
  val offsetUp: Var[Double] = Var(0.0)

  display @= Display.None

  override protected def initialize(): Unit = {
    super.initialize()

    position.x @= 0.0
    position.y @= 0.0
    position.z @= 3000
    position.`type` @= PositionType.Absolute

    overflow @= Overflow.Hidden
    ui.children += this
  }

  def show(target: Component, `type`: DropType = DropType.Auto): IO[Unit] = chained {
    IO {
      init()
      showing = Some(target)
      this.`type` = `type`
      Drop.opening(this)
      updatePosition()
      collapsed @= false
    }
  }

  def updatePosition(): Unit = showing.foreach { target =>
    val rect = target.absoluteBounding
    position.x @= math.min(rect.left, ui.size.width - width)
    position.y @= (rect.bottom + offsetDown) - ui.margin.top

    val distanceToTop = rect.top
    val distanceToBottom = ui.size.height - rect.bottom
    val down = `type` == DropType.Down || (`type` == DropType.Auto && distanceToBottom >= distanceToTop)

    if (!down) {
      position.y := (rect.top - size.height - offsetUp) - ui.margin.top
    }
  }

  def hide(): IO[Unit] = chained {
    IO {
      showing = None
      Drop._open = None
      collapsed @= true
    }
  }

  def toggle(target: Component, `type`: DropType = DropType.Auto): IO[Unit] = if (showing.isEmpty) {
    show(target, `type`)
  } else {
    hide()
  }


  override protected def direction: Plane = Plane.Vertical

//  override protected def expanded: Double = container.preferred.height + 2.0
}

object Drop {
  private var openStart = 0L
  private var _open: Option[Drop] = None

  def open: Option[Drop] = _open

  private def opening(drop: Drop): Unit = if (!_open.contains(drop)) {
    openStart = System.currentTimeMillis()
    hide()
    _open = Some(drop)
  }

  def hide(): Unit = _open.foreach(_.hide())

  ui.event.click.attach { evt =>
    if (System.currentTimeMillis() - openStart > 250L) {
      val target = evt.underlying.target.asInstanceOf[html.Element]
      val ignore = target.classList.contains("drop-ignore-click")
      if (!ignore) {
        hide()
      }
    }
  }
  ui.size.width.and(ui.size.height).on {
    _open.foreach(_.updatePosition())
  }
}