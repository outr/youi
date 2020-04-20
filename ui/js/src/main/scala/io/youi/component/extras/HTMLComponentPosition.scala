package io.youi.component.extras

class HTMLComponentPosition(override protected val component: HTMLComponent[_ <: html.Element]) extends ComponentPosition with StringifyImplicits {
  private def e: html.Element = HTMLComponent.element(component)

  x.attach { value =>
    e.style.left = s"${value}px"
    e.style.top = s"${y()}px"
  }
  y.attach { value =>
    e.style.left = s"${x()}px"
    e.style.top = s"${value}px"
  }
  z.attach { value =>
    e.style.zIndex = value.toString
  }

  val `type`: StyleProp[Position] = component.style[Position]("position", Position.Static, StyleConnect.style[Position], ignoreParent = true)

  x.and(y).once { _ =>
    if (`type`() == Position.Static) {
      `type` @= Position.Absolute
    }
  }
  `type`.attach { _ =>
    e.style.left = s"${x()}px"
    e.style.top = s"${y()}px"
  }

  object scroll {
    private def real(d: Double): Double = if (d.isInfinite || d.isNaN) {
      0.0
    } else {
      d
    }

    lazy val x: Var[Double] = Var(e.getBoundingClientRect().left)
    lazy val y: Var[Double] = Var(e.getBoundingClientRect().top)

    object max {
      lazy val x: Val[Double] = Val(real(component.size.scroll.width - component.size.view.width))
      lazy val y: Val[Double] = Val(real(component.size.scroll.height - component.size.view.height))
    }

    object percent {
      lazy val x: Val[Double] = Val {
        if (scroll.x() != 0.0 && max.x() != 0.0) {
          math.min(1.0, math.abs(scroll.x / max.x))
        } else {
          0.0
        }
      }
      lazy val y: Val[Double] = Val {
        if (scroll.y() != 0.0 && max.y() != 0.0) {
          math.min(1.0, math.abs(scroll.y / max.y))
        } else {
          0.0
        }
      }
    }
  }
}