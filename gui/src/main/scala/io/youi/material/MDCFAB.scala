package io.youi.material

import io.youi.component.Component
import io.youi.component.types.Prop
import io.youi.dom
import io.youi.dom._
import io.youi.event.EventSupport
import io.youi.material.impl.{MDCRipple, MDCRippleImplementation}
import org.scalajs.dom.html
import reactify.Var

class MDCFAB extends Component(dom.create.button) with EventSupport {
  classes := List("mdc-fab")
  element.setAttribute("aria-label", "")

  val icon: Var[MaterialIcon] = Var(Material.Icons.Empty)
  val label: Var[String] = Var("")
  val mini: Prop[Boolean] = classes.toggle("mdc-fab--mini")
  val extended: Prop[Boolean] = classes.toggle("mdc-fab--extended")

  def this(icon: MaterialIcon, label: String = "", mini: Boolean = false) = {
    this()
    this.icon @= icon
    this.label @= label
    this.mini @= mini
    if (label.nonEmpty) this.extended @= true
  }

  private object elements {
    val ripple: html.Div = {
      val div = dom.create.div
      div.addClasses("mdc-fab__ripple")
      div
    }
    val iconSpan: html.Span = {
      val span = dom.create.span
      span.addClasses("mdc-fab__icon", "material-icons")
      MDCFAB.this.icon.attachAndFire(i => span.innerHTML = i.name)
      span
    }
    val labelSpan: html.Span = {
      val span = dom.create.span
      span.addClasses("mdc-fab__label")
      MDCFAB.this.label.attachAndFire { l =>
        span.innerHTML = l
        span.style.display = if (l.isEmpty) "none" else ""
      }
      span
    }
  }

  element.appendChild(elements.ripple)
  element.appendChild(elements.iconSpan)
  element.appendChild(elements.labelSpan)

  private val adapter: MDCRippleImplementation = MaterialComponents.verified(MDCRipple.attachTo(element))
}
