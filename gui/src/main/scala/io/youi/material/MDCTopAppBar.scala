package io.youi.material

import io.youi.component.types.Prop
import io.youi.component.{Component, Container}
import io.youi.dom
import io.youi.dom._
import io.youi.event.EventSupport
import io.youi.material.impl.MDCTopAppBarImplementation
import org.scalajs.dom.html
import reactify.Var

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

class MDCTopAppBar extends Component(dom.create[html.Element]("header")) {
  classes := List("mdc-top-app-bar")

  val heading: Var[String] = Var("")

  val fixed: Prop[Boolean] = classes.toggle("mdc-top-app-bar--fixed")
  val short: Prop[Boolean] = classes.toggle("mdc-top-app-bar--short")
  val collapsed: Prop[Boolean] = classes.toggle("mdc-top-app-bar--short-collapsed")
  val prominent: Prop[Boolean] = classes.toggle("mdc-top-app-bar--prominent")
  val dense: Prop[Boolean] = classes.toggle("mdc-top-app-bar--dense")

  val height: Var[Double] = Var(64.0)

  private object elements {
    val row: html.Div = dom.create.div
    row.addClasses("mdc-top-app-bar__row")
    height.attach { h =>
      row.style.height = s"${h}px"
    }

    val section: html.Element = dom.create[html.Element]("section")
    section.addClasses("mdc-top-app-bar__section", "mdc-top-app-bar__section--align-start")

    val button: html.Button = dom.create.button
    button.addClasses("material-icons", "mdc-top-app-bar__navigation-icon", "mdc-icon-button")
    button.innerHTML = "menu"

    val span: html.Span = dom.create.span
    span.addClasses("mdc-top-app-bar__title")
    heading.attachAndFire(span.innerHTML_=)

    val right: html.Element = dom.create[html.Element]("section")
    right.addClasses("mdc-top-app-bar__section", "mdc-top-app-bar__section--align-end")
  }

  elements.section.appendChild(elements.button)
  elements.section.appendChild(elements.span)
  elements.row.appendChild(elements.section)
  elements.row.appendChild(elements.right)
  element.appendChild(elements.row)

  val menu: Component & EventSupport = new Component(elements.button) with EventSupport
  val main: Container = new Container(elements.section)
  val controls: Container = new Container(elements.right)

  private val adapter: MDCTopAppBarImplementation = {
    assert(MaterialComponents.loaded, "MaterialComponents is not loaded!")
    MDCTopAppBar.attachTo(element)
  }
}

@js.native
@JSGlobal("mdc.topAppBar.MDCTopAppBar")
object MDCTopAppBar extends js.Object {
  def attachTo(element: html.Element): MDCTopAppBarImplementation = js.native
}