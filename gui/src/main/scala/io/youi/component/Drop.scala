package io.youi.component

import io.youi.component.support.{BorderSupport, CollapsibleSupport, MeasuredSupport, OverflowSupport, PositionSupport, SizeSupport}
import io.youi.component.types.{Display, DropType, Overflow, PositionType}
import io.youi._
import reactify._

import scala.concurrent.Future

class Drop extends Component(dom.create.div) with SizeSupport with PositionSupport with BorderSupport with OverflowSupport with CollapsibleSupport {
  private var showing: Option[Component] = None

  lazy val container: Container with MeasuredSupport = new Container with MeasuredSupport
  val offset: Var[Double] = Var(0.0)

  element.appendChild(container)
  position.x @= 0.0
  position.y @= 0.0
  position.z @= 200
  position.`type` @= PositionType.Absolute
  size.width := math.round(container.measured.width + 2.0)
  size.height := 0.0
  display @= Display.None

  overflow @= Overflow.Hidden

  def show(target: Component, `type`: DropType = DropType.Auto): Future[Unit] = {
    showing = Some(target)
    Drop.opening(this)
    val rect = target.absoluteBounding
    position.x @= math.min(rect.left, ui.size.width - container.measured.width)
    position.y @= rect.bottom + offset

    val distanceToTop = rect.top
    val distanceToBottom = ui.size.height - rect.bottom
    val down = `type` == DropType.Down || (`type` == DropType.Auto && distanceToBottom >= distanceToTop)

    if (!down) {
      position.y := rect.top - size.height
    }
    collapsed @= false
    future
  }

  def hide(): Future[Unit] = {
    showing = None
    Drop.open = None
    collapsed @= true
    future
  }

  def toggle(target: Component, `type`: DropType = DropType.Auto): Future[Unit] = if (showing.isEmpty) {
    show(target, `type`)
  } else {
    hide()
  }


  override protected def direction: Plane = Plane.Vertical

  override protected def expanded: Double = container.measured.height + 2.0
}

object Drop {
  private var openStart = 0L
  private var open: Option[Drop] = None

  private def opening(drop: Drop): Unit = {
    openStart = System.currentTimeMillis()
    hide()
    open = Some(drop)
  }

  def hide(): Unit = open.foreach(_.hide())

  ui.event.click.on {
    if (System.currentTimeMillis() - openStart > 250L) {
      hide()
    }
  }
}