package io.youi.component

import io.youi.component.support.{BorderSupport, MeasuredSupport, OverflowSupport, PositionSupport, SizeSupport}
import io.youi.component.types.{Border, BorderStyle, Display, DropType, Overflow, PositionType}
import io.youi.easing.Easing
import io.youi.{Color, dom, ui}
import io.youi.task._
import reactify._

import scala.concurrent.Future
import scala.concurrent.duration._
import scribe.Execution.global

class Drop extends Component(dom.create.div) with SizeSupport with PositionSupport with BorderSupport with OverflowSupport {
  private var future: Future[Unit] = Future.successful(())
  private var showing: Option[Component] = None

  val easing: Var[Easing] = Var(Easing.exponentialOut)
  val speed: Var[FiniteDuration] = Var(300.millis)

  // TODO: Make container mix-in CollapsibleSupport
  val container: Container with MeasuredSupport = new Container with MeasuredSupport

  element.appendChild(container)
  position.x @= 0.0
  position.y @= 0.0
  position.z @= 200
  position.`type` @= PositionType.Absolute
  size.width := math.round(container.measured.width + 2.0)
  size.height := 0.0
  display @= Display.None

  overflow @= Overflow.Hidden
//  border @= Border(1.0, BorderStyle.Solid, Color.Black)
  backgroundColor @= Color.LightBlue

  def show(target: Component, `type`: DropType = DropType.Auto): Future[Unit] = {
    showing = Some(target)
    future = future.flatMap { _ =>
      Drop.opening(this)
      val rect = target.absoluteBounding
      position.x @= rect.left
      position.y @= rect.bottom

      val distanceToTop = rect.top
      val distanceToBottom = ui.size.height - rect.bottom
      val down = `type` == DropType.Down || (`type` == DropType.Auto && distanceToBottom >= distanceToTop)
      val distanceToEnd = if (down) distanceToBottom else distanceToTop

      if (!down) {
        position.y := rect.top - size.height
      }

      val destinationHeight = math.min(math.round(container.measured.height + 2.0), distanceToEnd)

      sequential(
        size.height @= 0.0,
        display @= Display.Block,
        size.height.to(destinationHeight).in(speed).easing(easing)
      ).start().future.map(_ => ())
    }
    future
  }

  def hide(): Future[Unit] = {
    showing = None
    Drop.open = None
    future = future.flatMap { _ =>
      scribe.info("Starting hide!")
      sequential(
        size.height.to(0.0).in(speed).easing(easing),
        display @= Display.None,
      ).start().future.map(_ => ())
    }
    future
  }

  def toggle(target: Component): Future[Unit] = if (showing.isEmpty) {
    show(target)
  } else {
    hide()
  }
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