package io.youi.example.ui

import io.youi.app.screen.{PathActivation, Screen}
import io.youi.event.{EventSupport, Swipe}
import io.youi._
import io.youi.component._
import io.youi.component.support.{BorderSupport, FontSupport, MarginSupport, OverflowSupport, PositionSupport, SizeSupport}
import io.youi.component.types.{Display, Overflow, PositionType, UserSelect}
import io.youi.easing.Easing
import io.youi.example.ClientExampleApplication
import io.youi.net._
import io.youi.task._
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
    var started = false
    var start = 0.0
    swipe.start.on {
      if (!GlassPane.isActive || Sidebar.isOpen) {
        started = true
        GlassPane.show(() => {
          Sidebar.hide()
          true
        }, fadeIn = true)
        ui.userSelect @= UserSelect.None
        Messages.overflow.y @= Overflow.Hidden
        start = Sidebar.position.x
      }
    }
    swipe.move.attach { evt =>
      if (started) {
        val x = math.min(0.0, start + evt.distance)
        Sidebar.position.x @= x
      }
    }
    swipe.end.on {
      if (started) {
        started = false
        ui.userSelect @= UserSelect.Auto
        Messages.overflow.y @= Overflow.Auto
        animateFuture = animateFuture.flatMap { _ =>
          (if (Sidebar.position.x < -(Sidebar.size.width / 2)) {        // Close
            Sidebar.hide()
          } else {                                                      // Open
            Sidebar.show()
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
  private val adjust = 800.0

  position.x @= -260.0
  position.y @= 0.0
  position.z @= 1000
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

  def isOpen: Boolean = position.x() > -260.0

  def show(): TaskInstance = {
    Sidebar.position.x.to(0.0).by(adjust).start()
  }

  def hide(): TaskInstance = {
    GlassPane.hide(fadeOut = true)
    Sidebar.position.x.to(-Sidebar.size.width).by(adjust).start()
  }
}

object TopBar extends Container with SizeSupport {
  size.width := ui.size.width
  size.height := 56.0
  backgroundColor := Color.Red

  private val icon = new ImageView with MarginSupport with EventSupport with BorderSupport {
    src @= "https://s3.us-west-1.wasabisys.com/courio/user/TBNBSUpGif4ZTuay6sLTxVPPPho8adLV-icon.png?m=1573234001845"

    margin.top @= 10.px
    margin.right @= 10.px
    border.radius @= 10.px

    element.style.cssFloat = "right"

    event.click.on {
      TopBarDropdown.show()
      GlassPane.show(() => {
        TopBarDropdown.hide()
        true
      }, fadeIn = true)
    }
  }

  children += icon
}

trait Modal {
  def show(): Unit
  def hide(): Unit
  def toggle(): Unit
}

object TopBarDropdown extends Container with SizeSupport with PositionSupport with Modal with BorderSupport with OverflowSupport {
  position.`type` @= PositionType.Absolute
  position.z @= 1000
  size.width @= 260.px
  size.height @= 246.px
  backgroundColor @= Color.White
  display @= Display.None
  border.radius @= 10.px
  overflow @= Overflow.Hidden

  override def show(): Unit = {
    position.right := ui.size.width - 10.px
    position.top := 50.0

    sequential(
      size.width @= 0.0,
      size.height @= 0.0,
      display @= Display.Block,
      parallel(
        size.width to 260.px in 250.millis easing Easing.exponentialOut,
        size.height to 246.px in 250.millis easing Easing.exponentialOut
      )
    ).start()
  }

  override def hide(): Unit = {
    sequential(
      parallel(
        size.width to 0.px in 250.millis easing Easing.exponentialOut,
        size.height to 0.px in 250.millis easing Easing.exponentialOut
      ),
      display @= Display.None
    ).start()
  }

  def toggle(): Unit = if (display() == Display.None) {
    show()
  } else {
    hide()
  }
}

object Messages extends Component(dom.create.div) with SizeSupport with OverflowSupport {
  size.width := ui.size.width
  size.height := ui.size.height - (TopBar.size.height + BottomBar.size.height)
  backgroundColor := Color.Green
  overflow.y @= Overflow.Auto

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