package io.youi.component

trait UIModal {
  this: HTMLComponent[_ <: html.Element] =>

  val closeOnBackdrop: Var[Boolean] = Var(true)

  position.center := ui.position.center
  position.middle := ui.position.middle
  position.`type` @= Position.Fixed
  display @= Display.None

  def show(): Unit = {
    UIModal.active @= Some(this)
    if (position.z() <= UIModal.backdrop.position.z()) {
      position.z.static(UIModal.backdrop.position.z() + 1)
    }
    display @= Display.Block
  }

  def hide(): Unit = {
    UIModal.active @= None
    display @= Display.None
  }
}

object UIModal {
  val active: Var[Option[UIModal]] = Var(None)

  val backdrop: Container = new Container {
    background @= Color.Black
    opacity @= 0.5
    position.x @= 0.0
    position.y @= 0.0
    position.z @= 100
    position.`type` @= Position.Fixed
    size.width := ui.size.width
    size.height := ui.size.height
    display @= Display.None

    ui.children += this

    event.click.on {
      active().foreach { m =>
        if (m.closeOnBackdrop()) {
          hide()
        }
      }
    }
  }

  private var previous = Option.empty[UIModal]
  active.attach { a =>
    previous.foreach(p => p.hide())
    a match {
      case Some(modal) => {
        modal.show()
        backdrop.display @= Display.Block
      }
      case None => backdrop.display @= Display.None
    }
    previous = a
  }

  def hide(): Unit = active @= None
}