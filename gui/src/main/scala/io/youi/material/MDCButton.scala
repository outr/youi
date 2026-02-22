package io.youi.material

import io.youi.component.Component
import io.youi.component.support.{ContentSupport, InternalContainerSupport}
import io.youi.component.types.{Display, Prop}
import io.youi.dom
import io.youi.dom._
import io.youi.event.EventSupport
import io.youi.material.impl.{MDCRipple, MDCRippleImplementation}
import org.scalajs.dom.html
import reactify._

class MDCButton extends Component(dom.create.button) with InternalContainerSupport[Component] {
  classes := List("mdc-button")

  protected def button: html.Button = element.asInstanceOf[html.Button]

  val label: Var[String] = Var("")
  val raised: Prop[Boolean] = classes.toggle("mdc-button--raised")
  val unelevated: Prop[Boolean] = classes.toggle("mdc-button--unelevated")
  val outlined: Prop[Boolean] = classes.toggle("mdc-button--outlined")
  val disabled: Prop[Boolean] = new Prop(button.disabled, button.disabled_=)

  val leading: ButtonIcon = new ButtonIcon
  val trailing: ButtonIcon = new ButtonIcon

  label.on(measure.trigger())

  def this(label: String, leading: MaterialIcon = Material.Icons.Empty, trailing: MaterialIcon = Material.Icons.Empty) = {
    this()
    this.label @= label
    this.leading.value @= leading
    this.trailing.value @= trailing
  }

  private object elements {
    val ripple: html.Div = {
      val div = dom.create.div
      div.addClasses("mdc-button__ripple")
      div
    }
    val label: html.Span = {
      val span = dom.create.span
      span.addClasses("mdc-button__label")
      MDCButton.this.label.attach(span.innerHTML_=)
      span
    }
  }

  children += elements.ripple
  children += leading
  children += elements.label
  children += trailing

  private val adapter: MDCRippleImplementation = MaterialComponents.verified(MDCRipple.attachTo(element))

  class ButtonIcon extends Component(dom.create.i) with EventSupport with ContentSupport {
    val value: Var[MaterialIcon] = Var(Material.Icons.Empty)

    classes ++= List("material-icons", "mdc-button__icon")
    color := MDCButton.this.color
    display := (if (value().isEmpty) Display.None else Display.InlineBlock)
    content := value().name
  }
}