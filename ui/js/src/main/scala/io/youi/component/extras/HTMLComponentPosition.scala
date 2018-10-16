package io.youi.component.extras

import io.youi.style.Position
import io.youi.theme.{StringifyImplicits, StyleConnect, StyleProp}
import org.scalajs.dom._
import reactify.{Val, Var}

class HTMLComponentPosition(override protected val component: HTMLComponent[_ <: html.Element]) extends ComponentPosition with StringifyImplicits {
  private def e: html.Element = HTMLComponent.element(component)

  x.attach { value =>
    e.style.left = s"${value}px"
  }
  y.attach { value =>
    e.style.top = s"${value}px"
  }
  z.attach { value =>
    e.style.zIndex = value.toString
  }

  val `type`: StyleProp[Position] = component.style[Position]("position", if (x() == 0.0 && y() == 0.0) {
    Position.Static
  } else {
    Position.Absolute
  }, StyleConnect.style[Position], ignoreParent = true)

  object scroll {
    private def prop(get: => Double, set: Double => Unit): Var[Double] = {
      val v = Var(get)
      var updating = false
      component.delta.on {
        updating = true
        try {
          v.static(get)
        } finally {
          updating = false
        }
      }
      v.attach { d =>
        if (!updating) {
          set(d)
        }
      }
      v
    }

    private def real(d: Double): Double = if (d.isInfinite || d.isNaN) {
      0.0
    } else {
      d
    }

    lazy val x: Var[Double] = prop(e.getBoundingClientRect().left, e.scrollLeft = _)
    lazy val y: Var[Double] = prop(e.getBoundingClientRect().top, e.scrollTop = _)

    object max {
      lazy val x: Val[Double] = Val(real(component.size.scroll.width - component.size.view.width))
      lazy val y: Val[Double] = Val(real(component.size.scroll.height - component.size.view.height))
    }

    object percent {
      lazy val x: Val[Double] = Val(math.abs(scroll.x / max.x))
      lazy val y: Val[Double] = Val(math.abs(scroll.y / max.y))
    }
  }
}