package io.youi.material

import io.youi.component.types.Prop
import io.youi.component.{Component, Container}
import io.youi.{History, Unique, dom}
import io.youi.dom._
import io.youi.event.EventSupport
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

class MDCIconButton extends Component(dom.create.button) {
  classes := Set("mdc-icon-button", "material-icons")

  def this(icon: MaterialIcon) = {
    this()
    this.icon @= icon
  }

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

class MDCTopAppBar extends Component(dom.create[html.Element]("header")) {
  classes := Set("mdc-top-app-bar")

  val title: Var[String] = Var("")

  val fixed: Prop[Boolean] = classes.toggle("mdc-top-app-bar--fixed")
  val short: Prop[Boolean] = classes.toggle("mdc-top-app-bar--short")
  val collapsed: Prop[Boolean] = classes.toggle("mdc-top-app-bar--short-collapsed")
  val prominent: Prop[Boolean] = classes.toggle("mdc-top-app-bar--prominent")
  val dense: Prop[Boolean] = classes.toggle("mdc-top-app-bar--dense")

  private object elements {
    val row: html.Div = dom.create.div
    row.addClasses("mdc-top-app-bar__row")

    val section: html.Element = dom.create[html.Element]("section")
    section.addClasses("mdc-top-app-bar__section", "mdc-top-app-bar__section--align-start")

    val button: html.Button = dom.create.button
    button.addClasses("material-icons", "mdc-top-app-bar__navigation-icon", "mdc-icon-button")
    button.innerHTML = "menu"

    val span: html.Span = dom.create.span
    span.addClasses("mdc-top-app-bar__title")
    title.attachAndFire(span.innerHTML_=)

    val right: html.Element = dom.create[html.Element]("section")
    right.addClasses("mdc-top-app-bar__section", "mdc-top-app-bar__section--align-end")
  }

  elements.section.appendChild(elements.button)
  elements.section.appendChild(elements.span)
  elements.row.appendChild(elements.section)
  elements.row.appendChild(elements.right)
  element.appendChild(elements.row)

  val menu: Component with EventSupport = new Component(elements.button) with EventSupport
  val main: Container = new Container(elements.section)
  val controls: Container = new Container(elements.right)

  private val adapter: MDCTopAppBarImplementation = {
    assert(MaterialComponents.loaded.isCompleted)
    MDCTopAppBar.attachTo(element)
  }
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

  private val adapter: MDCRippleImplementation = {
    assert(MaterialComponents.loaded.isCompleted)
    MDCRipple.attachTo(element)
  }
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

  private val adapter: MDCTextFieldImplementation = {
    assert(MaterialComponents.loaded.isCompleted)
    MDCTextField.attachTo(element)
  }

  def shakeLabel(): Unit = adapter.getLabelAdapterMethods_().shakeLabel(true)
}

@js.native
@JSGlobal("mdc.topAppBar.MDCTopAppBar")
object MDCTopAppBar extends js.Object {
  def attachTo(element: html.Element): MDCTopAppBarImplementation = js.native
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
trait MDCTopAppBarImplementation extends js.Object {
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