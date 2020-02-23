package io.youi.example.ui

import io.youi.app.screen.{PathActivation, Screen}
import io.youi.event.{EventSupport, Swipe}
import io.youi.{AnimationFrame, Color, component, dom, ui}
import io.youi.component._
import io.youi.component.support.{FontSupport, MarginSupport, PositionSupport, SizeSupport}
import io.youi.component.types.{Display, PositionType}
import io.youi.net._
import io.youi.task._
import org.scalajs.dom._
import reactify._

import scala.concurrent.Future
import scribe.Execution.global

class CourioPrototype extends Screen with PathActivation {
  override def title: String = "Courio"
  override def path: Path = path"/courio.html"

  private var animateFuture: Future[Double] = Future.successful(0.0)

  private lazy val container = new Component(dom.create.div) with SizeSupport

  private lazy val glassPane = new Component(dom.create.div) with SizeSupport with PositionSupport {
    id @= "glassPane"
    scribe.info(s"Position: ${position.x()}x${position.y()}")
    position.`type` @= PositionType.Absolute
    position.x @= 0.0
    position.y @= 0.0
    position.z @= 1000
    size.width := ui.size.width
    size.height := ui.size.height
    backgroundColor @= Color.Clear
    display @= Display.None
  }

  override protected def init(): Future[Unit] = super.init().map { _ =>
    val swipe = new Swipe(ui, ui.event, onlyTouch = true, Set(Swipe.Direction.Left, Swipe.Direction.Right))
    val adjust = 800.0
    var start = 0.0
    ui.event.touch.move.attach(_.stopPropagation())
    swipe.start.on {
      glassPane.display @= Display.Block
      start = Sidebar.position.x
    }
    swipe.move.attach { evt =>
      val x = math.min(0.0, start + evt.distance)
      Sidebar.position.x @= x
    }
    swipe.end.on {
      glassPane.display @= Display.None
      animateFuture = animateFuture.flatMap { _ =>
        (if (Sidebar.position.x < -(Sidebar.size.width / 2)) {
          Sidebar.position.x to -Sidebar.size.width() by adjust start (AnimationFrame)
        } else {
          Sidebar.position.x to 0.0 by adjust start (AnimationFrame)
        }).future
      }
    }

    container.display @= Display.None
    container.size.width := ui.size.width
    container.size.height := ui.size.height

    container.appendChild(Sidebar)
    container.appendChild(TopBar)
    container.appendChild(Messages)
    container.appendChild(BottomBar)

    ui.children += container
    ui.children += glassPane
  }

  override protected def activate(): Future[Unit] = super.activate().map { _ =>
    container.display @= Display.Block
  }

  override protected def deactivate(): Future[Unit] = super.activate().map { _ =>
    container.display @= Display.Block
  }
}

object Sidebar extends Component(dom.create.div) with PositionSupport with SizeSupport {
  position.x @= -260.0
  position.y @= 0.0
  position.`type` @= PositionType.Absolute
  size.width @= 260
  size.height := ui.size.height
  backgroundColor @= Color.fromLong(0x1F3142FF)

  private val logo: ImageView = new ImageView() with SizeSupport with MarginSupport {
    src @= "https://app.courio.com/images/logo-dark.svg"
    size.height @= 50.0
    margin.top @= 14.0
    margin.left @= 20.0
  }

  this.appendChild(logo)
}

object TopBar extends Component(dom.create.div) with SizeSupport {
  size.width := ui.size.width
  size.height := 56.0
  backgroundColor := Color.Red
}

object Messages extends Component(dom.create.div) with SizeSupport {
  size.width := ui.size.width
  size.height := ui.size.height - (TopBar.size.height + BottomBar.size.height)
  backgroundColor := Color.Green

  this.style.overflowY = "auto"

  (0 until 100).foreach { _ =>
    this.appendChild(new Message)
  }
}

object BottomBar extends Component(dom.create.div) with SizeSupport {
  size.width := ui.size.width
  size.height := 50.0
  backgroundColor := Color.Blue

  private val input = new TextArea() with SizeSupport {
    size.width := BottomBar.this.size.width - 10
    size.height := 40.0

    element.addEventListener("paste", (evt: Event) => {
      scribe.info("PASTE!")
      evt.preventDefault()
      evt.stopPropagation()
    })
  }
  this.appendChild(input)
}

class Message extends Component(dom.create.div) with SizeSupport with FontSupport {
  size.width := ui.size.width - 10.0
  size.height @= 54.0
  backgroundColor @= Color.random
  this.style.borderRadius = "15px"
  this.style.padding = "10px"
  this.style.marginLeft = "5px"
  this.style.marginRight = "5px"
  this.style.marginTop = "5px"
  font.size @= 12.0

  content @= "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
}