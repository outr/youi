package io.youi.example.ui

import io.youi.app.screen.{PathActivation, Screen}
import io.youi.event.{EventSupport, Swipe}
import io.youi._
import io.youi.component._
import io.youi.component.support.{FontSupport, MarginSupport, PositionSupport, SizeSupport}
import io.youi.component.types.{Display, PositionType}
import io.youi.easing.Easing
import io.youi.net._
import io.youi.task._
import org.scalajs.dom._
import reactify._

import scala.concurrent.Future
import scala.concurrent.duration._
import scribe.Execution.global

class CourioPrototype extends Screen with PathActivation {
  override def title: String = "Courio"
  override def path: Path = path"/courio.html"

  private var animateFuture: Future[Double] = Future.successful(0.0)

  private lazy val container = new Component(dom.create.div) with SizeSupport

  override protected def init(): Future[Unit] = super.init().map { _ =>
    val swipe = new Swipe(ui, ui.event, onlyTouch = true, Set(Swipe.Direction.Left, Swipe.Direction.Right))
    val adjust = 800.0
    var start = 0.0
    ui.event.touch.move.attach(_.stopPropagation())
    swipe.start.on {
      if (!GlassPane.isActive) {
        Messages.style.overflowY = "hidden"
        start = Sidebar.position.x
      }
    }
    swipe.move.attach { evt =>
      if (!GlassPane.isActive) {
        val x = math.min(0.0, start + evt.distance)
        Sidebar.position.x @= x
      }
    }
    swipe.end.on {
      if (!GlassPane.isActive) {
        Messages.style.overflowY = "auto"
        animateFuture = animateFuture.flatMap { _ =>
          (if (Sidebar.position.x < -(Sidebar.size.width / 2)) {
            Sidebar.position.x to -Sidebar.size.width() by adjust start (AnimationFrame)
          } else {
            Sidebar.position.x to 0.0 by adjust start (AnimationFrame)
          }).future
        }
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
    ui.children += GlassPane
    ui.children += TopBarDropdown
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

object TopBar extends Container with SizeSupport {
  size.width := ui.size.width
  size.height := 56.0
  backgroundColor := Color.Red

  private val icon = new ImageView with MarginSupport with EventSupport {
    src @= "https://s3.us-west-1.wasabisys.com/courio/user/TBNBSUpGif4ZTuay6sLTxVPPPho8adLV-icon.png?m=1573234001845"

    margin.top @= 10.px
    margin.right @= 10.px

    element.style.borderRadius = "10px"
    element.style.cssFloat = "right"

    event.click.on {
      GlassPane.activate(TopBarDropdown)
    }
  }

  children += icon
}

trait Modal {
  def show(): Unit
  def hide(): Unit
  def toggle(): Unit
}

object TopBarDropdown extends Container with SizeSupport with PositionSupport with Modal {
  position.`type` @= PositionType.Absolute
  position.z @= 1000
  size.width @= 260.px
  size.height @= 246.px
  backgroundColor @= Color.White
  display @= Display.None

  element.style.borderRadius = "10px"

  override def show(): Unit = {
    position.right := ui.size.width - 10.px
    position.top := 50.0

    size.height @= 0.0
    display @= Display.Block
    element.style.overflow = "hidden"
    val task = size.height to 246.px in 500.millis easing Easing.elasticOut
    task.start(AnimationFrame)
  }

  override def hide(): Unit = {
    val task = sequential(
      size.height to 0.px in 250.millis easing Easing.exponentialIn,
      display @= Display.None
    )
    task.start(AnimationFrame)
  }

  def toggle(): Unit = if (display() == Display.None) {
    show()
  } else {
    hide()
  }
}

object GlassPane extends Component(dom.create.div) with SizeSupport with PositionSupport with EventSupport {
  private var active: Option[Modal] = None

  private val backgroundAlpha = Var(0.5)

  def isActive: Boolean = active.nonEmpty

  position.`type` @= PositionType.Absolute
  position.x @= 0.0
  position.y @= 0.0
  position.z @= 100
  size.width := ui.size.width
  size.height := ui.size.height
  backgroundColor := Color.Black.withAlpha(backgroundAlpha)
  display @= Display.None

  event.click.on(deactivate())

  private def show(): Unit = {
    val task = sequential(
      backgroundAlpha @= 0.0,
      display @= Display.Block,
      backgroundAlpha to 0.5 in 250.millis
    )
    task.start(AnimationFrame)
  }

  private def hide(): Unit = {
    val task = sequential(
      backgroundAlpha to 0.0 in 250.millis,
      display @= Display.None
    )
    task.start(AnimationFrame)
  }

  def activate(modal: Modal): Unit = {
    active = Some(modal)
    show()
    modal.show()
  }

  def deactivate(): Unit = {
    active.foreach(_.hide())
    hide()
    active = None
  }
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