package io.youi.material

import io.youi.component.Component
import io.youi.component.types.Prop
import io.youi.dom
import io.youi.dom._
import io.youi.material.impl.{MDCRipple, MDCRippleImplementation}
import org.scalajs.dom.html
import reactify.Var

class MDCButton extends Component(dom.create.button) {
  classes := Set("mdc-button")

  protected def button: html.Button = element.asInstanceOf[html.Button]

  val label: Var[String] = Var("")
  val leading: Var[Option[MaterialIcon]] = Var(None)
  val trailing: Var[Option[MaterialIcon]] = Var(None)
  val raised: Prop[Boolean] = classes.toggle("mdc-button--raised")
  val unelevated: Prop[Boolean] = classes.toggle("mdc-button--unelevated")
  val outlined: Prop[Boolean] = classes.toggle("mdc-button--outlined")
  val disabled: Prop[Boolean] = new Prop(button.disabled, button.disabled_=)

  def this(label: String, leading: Option[MaterialIcon] = None, trailing: Option[MaterialIcon] = None) = {
    this()
    this.label @= label
    this.leading @= leading
    this.trailing @= trailing
  }

  private object elements {
    val ripple: html.Div = {
      val div = dom.create.div
      div.addClasses("mdc-button__ripple")
      div
    }
    val leadingIcon: html.Element = {
      val i = dom.create.i
      i.addClasses("material-icons", "mdc-button__icon")
      color.attach(c => i.style.color = c.toRGBA)
      leading.attachAndFire {
        case Some(mi) => {
          i.style.display = "block"
          i.innerText = mi.name
        }
        case None => i.style.display = "none"
      }
      i
    }
    val label: html.Span = {
      val span = dom.create.span
      span.addClasses("mdc-button__label")
      MDCButton.this.label.attach(span.innerHTML_=)
      span
    }
    val trailingIcon: html.Element = {
      val i = dom.create.i
      i.addClasses("material-icons", "mdc-button__icon")
      color.attach(c => i.style.color = c.toRGBA)
      trailing.attachAndFire {
        case Some(mi) => {
          i.style.display = "block"
          i.innerText = mi.name
        }
        case None => i.style.display = "none"
      }
      i
    }
  }

  element.appendChild(elements.ripple)
  element.appendChild(elements.leadingIcon)
  element.appendChild(elements.label)
  element.appendChild(elements.trailingIcon)

  private val adapter: MDCRippleImplementation = MaterialComponents.verified(MDCRipple.attachTo(element))
}