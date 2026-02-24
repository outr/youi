package io.youi.material

import io.youi.Unique
import io.youi.component.Component
import io.youi.dom
import io.youi.dom._
import io.youi.material.impl.MDCSelectImplementation
import org.scalajs.dom.html
import reactify.{Channel, Var}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

class MDCSelect extends Component(dom.create.div) {
  classes := List("mdc-select", "mdc-select--filled")

  private val labelId: String = Unique()
  private val selectedTextId: String = Unique()

  val label: Var[String] = Var("")
  val value: Var[String] = Var("")
  val selectedIndex: Var[Int] = Var(-1)
  val disabled: Var[Boolean] = Var(false)
  val onChange: Channel[String] = Channel[String]

  private object elements {
    val anchor: html.Div = {
      val div = dom.create.div
      div.addClasses("mdc-select__anchor")
      div.setAttribute("role", "button")
      div.setAttribute("aria-haspopup", "listbox")
      div.setAttribute("aria-expanded", "false")
      div.setAttribute("aria-labelledby", s"$labelId $selectedTextId")
      div
    }
    val ripple: html.Span = {
      val span = dom.create.span
      span.addClasses("mdc-select__ripple")
      span
    }
    val floatingLabel: html.Span = {
      val span = dom.create.span
      span.id = labelId
      span.addClasses("mdc-floating-label")
      MDCSelect.this.label.attachAndFire(span.innerHTML_=)
      span
    }
    val selectedTextContainer: html.Span = {
      val span = dom.create.span
      span.addClasses("mdc-select__selected-text-container")
      span
    }
    val selectedText: html.Span = {
      val span = dom.create.span
      span.id = selectedTextId
      span.addClasses("mdc-select__selected-text")
      span
    }
    val dropdownIcon: html.Span = {
      val span = dom.create.span
      span.addClasses("mdc-select__dropdown-icon")
      span.innerHTML =
        """<svg class="mdc-select__dropdown-icon-graphic" viewBox="7 10 10 5" focusable="false">
          |  <polygon class="mdc-select__dropdown-icon-inactive" stroke="none" fill-rule="evenodd" points="7 10 12 15 17 10"/>
          |  <polygon class="mdc-select__dropdown-icon-active" stroke="none" fill-rule="evenodd" points="7 15 12 10 17 15"/>
          |</svg>""".stripMargin
      span
    }
    val lineRipple: html.Span = {
      val span = dom.create.span
      span.addClasses("mdc-line-ripple")
      span
    }
    val menu: html.Div = {
      val div = dom.create.div
      div.addClasses("mdc-select__menu", "mdc-menu", "mdc-menu-surface", "mdc-menu-surface--fullwidth")
      div
    }
    val list: html.Element = {
      val ul = dom.create[html.Element]("ul")
      ul.addClasses("mdc-deprecated-list")
      ul.setAttribute("role", "listbox")
      ul
    }
  }

  // Build anchor
  elements.selectedTextContainer.appendChild(elements.selectedText)
  elements.anchor.appendChild(elements.ripple)
  elements.anchor.appendChild(elements.floatingLabel)
  elements.anchor.appendChild(elements.selectedTextContainer)
  elements.anchor.appendChild(elements.dropdownIcon)
  elements.anchor.appendChild(elements.lineRipple)

  // Build menu
  elements.menu.appendChild(elements.list)

  element.appendChild(elements.anchor)
  element.appendChild(elements.menu)

  element.addEventListener("MDCSelect:change", (evt: org.scalajs.dom.Event) => {
    val detail = evt.asInstanceOf[js.Dynamic].detail
    val v = detail.value.asInstanceOf[String]
    updating = true
    value @= v
    selectedIndex @= detail.index.asInstanceOf[Int]
    onChange @= v
    updating = false
  })

  private lazy val adapter: MDCSelectImplementation =
    MaterialComponents.verified(MDCSelect.attachTo(element))

  private var updating = false

  /** Add an option to this select. */
  def addOption(optionValue: String, text: String): Unit = {
    val li = dom.create[html.Element]("li")
    li.addClasses("mdc-deprecated-list-item")
    li.setAttribute("aria-selected", "false")
    li.setAttribute("data-value", optionValue)
    li.setAttribute("role", "option")
    val ripple = dom.create.span
    ripple.addClasses("mdc-deprecated-list-item__ripple")
    li.appendChild(ripple)
    if (text.nonEmpty) {
      val span = dom.create.span
      span.addClasses("mdc-deprecated-list-item__text")
      span.innerHTML = text
      li.appendChild(span)
    }
    elements.list.appendChild(li)
  }

  /** Initialize the MDC adapter. Call after all options have been added. */
  def init(): Unit = {
    val _ = adapter
    value.attach { v =>
      if (!updating) adapter.value = v
    }
    disabled.attach(adapter.disabled_=)
  }
}

@js.native
@JSGlobal("mdc.select.MDCSelect")
object MDCSelect extends js.Object {
  def attachTo(element: html.Element): MDCSelectImplementation = js.native
}
