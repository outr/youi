package youi.component

import youi.component.types.{Display, PositionType}
import youi.event.EventSupport
import youi.{Color, dom, ui}
import reactify.{Var, stateful2Value}

trait Modal extends EventSupport {
  this: Component =>

  val closeOnBackdrop: Var[Boolean] = Var(true)
  val closeOnEscape: Var[Boolean] = Var(true)

  private var previousDisplay: Display = display()

  def show(): Unit = {
    Modal.backdrop.display @= Display.Block
    Modal.activeModals @= Modal.activeModals() :+ this
    position.`type` @= PositionType.Fixed
    position.z @= Modal.backdrop.position.z() + Modal.activeModals().size
    display @= (previousDisplay match {
      case Display.None => Display.Block
      case d => d
    })
    centerOnViewport()
  }

  def hide(): Unit = {
    display @= Display.None
    Modal.activeModals @= Modal.activeModals().filterNot(_ eq this)
    if (Modal.activeModals().isEmpty) {
      Modal.backdrop.display @= Display.None
    }
  }

  def isVisible: Boolean = display() != Display.None

  private def centerOnViewport(): Unit = {
    val vw = ui.size.width()
    val vh = ui.size.height()
    val w = effectiveWidth()
    val h = effectiveHeight()
    position.left @= math.max(0.0, (vw - w) / 2.0)
    position.top @= math.max(0.0, (vh - h) / 2.0)
  }
}

object Modal {
  private val activeModals: Var[List[Modal]] = Var(Nil)

  lazy val backdrop: Component = {
    val c = new Component(dom.create.div) with EventSupport
    c.position.`type` @= PositionType.Fixed
    c.position.x @= 0.0
    c.position.y @= 0.0
    c.position.z @= 9000
    c.size.width := ui.size.width
    c.size.height := ui.size.height
    c.backgroundColor @= Color.Black.withAlpha(0.5)
    c.display @= Display.None
    ui.children += c

    c.event.click.attach { _ =>
      activeModals().lastOption.foreach { modal =>
        if (modal.closeOnBackdrop()) modal.hide()
      }
    }

    org.scalajs.dom.document.addEventListener("keydown", (e: org.scalajs.dom.KeyboardEvent) => {
      if (e.key == "Escape") {
        activeModals().lastOption.foreach { modal =>
          if (modal.closeOnEscape()) modal.hide()
        }
      }
    })

    c
  }
}
