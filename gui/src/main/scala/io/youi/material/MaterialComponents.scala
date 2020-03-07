package io.youi.material

import io.youi.component.{Component, Container}
import io.youi.{History, Unique, dom}
import io.youi.dom._
import io.youi.net._
import org.scalajs.dom.html
import reactify.Var

import scala.concurrent.Future
import scribe.Execution.global

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

object MaterialComponents {
  lazy val loaded: Future[Unit] = {
    dom.addCSS(History.url.withPath(path"/material-components-web.min.css"))
    dom.addScript(History.url.withPath(path"/material-components-web.min.js")).flatMap { _ =>
      Material.load().map(_ => ())
    }
  }
}

/*
<button id="add-to-favorites"
   class="mdc-icon-button"
   aria-label="Add to favorites"
   aria-pressed="false">
   <i class="material-icons mdc-icon-button__icon mdc-icon-button__icon--on">favorite</i>
   <i class="material-icons mdc-icon-button__icon">favorite_border</i>
</button>

<button class="mdc-icon-button material-icons">favorite</button>
 */
class MDCIconButton extends Component(dom.create.button) {
  classes := Set("mdc-icon-button", "material-icons")

  val icon: Var[MaterialIcon] = Var(Material.Icons.Alert.Warning)

  icon.attachAndFire(i => content @= i.name)

  private val adapter: Future[MDCRippleImplementation] = MaterialComponents.loaded.map(_ => MDCRipple.attachTo(element))
  adapter.foreach(_.unbounded = true)
}

class MDCIconButtonToggle extends Component(dom.create.button) {
  classes := Set("mdc-icon-button")
  element.setAttribute("aria-pressed", "false")

  val on: Var[MaterialIcon] = Var(Material.Icons.Alert.ErrorOutline)
  val off: Var[MaterialIcon] = Var(Material.Icons.Alert.Error)

  private object elements {
    val on: html.Element = dom.create[html.Element]("i")
    val off: html.Element = dom.create[html.Element]("i")

    on.addClasses("material-icons", "mdc-icon-button__icon", "mdc-icon-button__icon--on")
    MDCIconButtonToggle.this.on.attachAndFire(i => on.innerHTML = i.name)

    off.addClasses("material-icons", "mdc-icon-button__icon")
    MDCIconButtonToggle.this.off.attachAndFire(i => off.innerHTML = i.name)
  }

  element.appendChild(elements.on)
  element.appendChild(elements.off)

  private val adapter: Future[MDCIconButtonToggleImplementation] = MaterialComponents.loaded.map(_ => MDCIconButtonToggle.attachTo(element))
}

class MDCButton extends Component(dom.create.button) {
  classes := Set("mdc-button")

  val label: Var[String] = Var("")

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

  element.appendChild(elements.ripple)
  element.appendChild(elements.label)

  private val adapter: Future[MDCRippleImplementation] = MaterialComponents.loaded.map(_ => MDCRipple.attachTo(element))
}

class MDCTextField extends Component(dom.create.div) {
  classes := Set("mdc-text-field")

  val label: Var[String] = Var("")
  val value: Var[String] = Var("")

  private object elements {
    val input: html.Input = {
      val input = dom.create.input
      input.id = Unique()
      input.addClasses("mdc-text-field__input")
      MDCTextField.this.value.attach(input.value_=)
      // TODO: Support change events
      input
    }
    val lineRipple: html.Div = {
      val div = dom.create.div
      div.addClasses("mdc-line-ripple")
      div
    }
    val label: html.Label = {
      val label = dom.create.label
      label.addClasses("mdc-floating-label")
      label.htmlFor = input.id
      MDCTextField.this.label.attach(label.innerHTML_=)
      label
    }
  }

  element.appendChild(elements.input)
  element.appendChild(elements.lineRipple)
  element.appendChild(elements.label)

  private val adapter: Future[MDCTextFieldImplementation] = MaterialComponents.loaded.map(_ => MDCTextField.attachTo(element))

  def shakeLabel(): Unit = adapter.foreach(_.getLabelAdapterMethods_().shakeLabel(true))
}

@js.native
@JSGlobal("mdc.ripple.MDCRipple")
object MDCRipple extends js.Object {
  def attachTo(element: html.Element): MDCRippleImplementation = js.native
}

@js.native
@JSGlobal("mdc.iconButton.MDCIconButtonToggle")
object MDCIconButtonToggle extends js.Object {
  def attachTo(element: html.Element): MDCIconButtonToggleImplementation = js.native
}

@js.native
@JSGlobal("mdc.textField.MDCTextField")
object MDCTextField extends js.Object {
  def attachTo(element: html.Element): MDCTextFieldImplementation = js.native
}

@js.native
trait MDCRippleImplementation extends js.Object {
  var unbounded: Boolean
}

@js.native
trait MDCIconButtonToggleImplementation extends js.Object {
}

@js.native
trait MDCTextFieldImplementation extends js.Object {
  def getLabelAdapterMethods_(): MDCLabelAdapter
}

@js.native
trait MDCLabelAdapter extends js.Object {
  def shakeLabel(shouldShake: Boolean): Unit
}