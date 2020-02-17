package io.youi.example.ui

import io.youi.app.screen.{PathActivation, Screen}
import io.youi.{Color, dom, ui}
import io.youi.gui._
import io.youi.gui.event.{EventSupport, GestureSupport}
import io.youi.gui.support.{MarginSupport, PositionSupport, SizeSupport}
import io.youi.gui.types.PositionType
import io.youi.net._
import io.youi.task.AnimateBy
import org.scalajs.dom._

import scala.concurrent.Future
import scribe.Execution.global

class CourioPrototype extends Screen with PathActivation {
  override def title: String = "Courio"
  override def path: Path = path"/courio.html"

  private lazy val container = new Component(dom.create.div) with SizeSupport

  override protected def init(): Future[Unit] = super.init().map { _ =>
    container.style.display = "none"
    container.size.width := ui.size.width
    container.size.height := ui.size.height

    container.appendChild(Sidebar)

    document.body.appendChild(container)
  }

  override protected def activate(): Future[Unit] = super.activate().map { _ =>
    container.style.display = "block"
  }

  override protected def deactivate(): Future[Unit] = super.activate().map { _ =>
    container.style.display = "none"
  }
}

object Sidebar extends Component(dom.create.div) with PositionSupport with SizeSupport {
  position.x @= 0.0
  position.y @= 0.0
  position.`type` @= PositionType.Absolute
  size.width @= 260
  size.height := ui.size.height
  backgroundColor @= Some(Color.fromLong(0x1F3142FF))

  private val logo: ImageView = new ImageView() with SizeSupport with MarginSupport {
    src @= "https://app.courio.com/images/logo-dark.svg"
    size.height @= 50.0
    margin.top @= 14.0
    margin.left @= 20.0
  }

  private val grabArea: Component = new Component(dom.create.div) with SizeSupport with PositionSupport with GestureSupport {
    position.x @= 250.0
    position.y @= 0.0
    position.`type` @= PositionType.Absolute
    size.width @= 20.0
    size.height := ui.size.height
    backgroundColor @= Color.Red

    gestures.pointers.dragged.attach { pointer =>
      val x = math.min(0.0, pointer.move.global.x - 250.0)
      Sidebar.position.x @= x
    }

    import io.youi.task._
    val adjust = 800.0
    gestures.pointers.removed.on {
      if (Sidebar.position.x < -(Sidebar.size.width / 2)) {
        Sidebar.position.x to -Sidebar.size.width() by adjust start(ui)
      } else {
        Sidebar.position.x to 0.0 by adjust start(ui)
      }
    }
  }

  this.appendChild(grabArea)
  this.appendChild(logo)
}