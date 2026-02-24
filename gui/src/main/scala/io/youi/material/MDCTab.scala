package io.youi.material

import io.youi.component.Component
import io.youi.dom
import io.youi.dom._
import io.youi.event.EventSupport
import org.scalajs.dom.html
import reactify.Var

class MDCTab extends Component(dom.create.button) with EventSupport {
  classes := List("mdc-tab")
  element.setAttribute("role", "tab")
  element.setAttribute("aria-selected", "false")
  element.setAttribute("tabindex", "-1")

  val label: Var[String] = Var("")
  val icon: Var[MaterialIcon] = Var(Material.Icons.Empty)

  def this(label: String, icon: MaterialIcon = Material.Icons.Empty) = {
    this()
    this.label @= label
    this.icon @= icon
  }

  private object elements {
    val content: html.Span = {
      val span = dom.create.span
      span.addClasses("mdc-tab__content")
      span
    }
    val iconSpan: html.Span = {
      val span = dom.create.span
      span.addClasses("material-icons", "mdc-tab__icon")
      span.setAttribute("aria-hidden", "true")
      MDCTab.this.icon.attachAndFire { i =>
        span.innerHTML = i.name
        span.style.display = if (i.isEmpty) "none" else ""
      }
      span
    }
    val textLabel: html.Span = {
      val span = dom.create.span
      span.addClasses("mdc-tab__text-label")
      MDCTab.this.label.attachAndFire(span.innerHTML_=)
      span
    }
    val indicator: html.Span = {
      val span = dom.create.span
      span.addClasses("mdc-tab-indicator")
      span
    }
    val indicatorContent: html.Span = {
      val span = dom.create.span
      span.addClasses("mdc-tab-indicator__content", "mdc-tab-indicator__content--underline")
      span
    }
    val ripple: html.Span = {
      val span = dom.create.span
      span.addClasses("mdc-tab__ripple")
      span
    }
  }

  elements.content.appendChild(elements.iconSpan)
  elements.content.appendChild(elements.textLabel)
  elements.indicator.appendChild(elements.indicatorContent)
  element.appendChild(elements.content)
  element.appendChild(elements.indicator)
  element.appendChild(elements.ripple)

  /** Mark this tab as initially active (before MDCTabBar adapter initialization). */
  def setInitiallyActive(): Unit = {
    classes += "mdc-tab--active"
    element.setAttribute("aria-selected", "true")
    element.setAttribute("tabindex", "0")
    elements.indicator.addClasses("mdc-tab-indicator--active")
  }
}
