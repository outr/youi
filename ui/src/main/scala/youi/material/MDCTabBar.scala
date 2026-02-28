package youi.material

import youi.component.Component
import youi.component.Container
import youi.dom
import youi.dom._
import youi.material.impl.MDCTabBarImplementation
import org.scalajs.dom.html
import reactify.{Channel, Var}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

class MDCTabBar extends Component(dom.create.div) {
  classes := List("mdc-tab-bar")
  element.setAttribute("role", "tablist")

  val onActivated: Channel[Int] = Channel[Int]

  private object elements {
    val scroller: html.Div = {
      val div = dom.create.div
      div.addClasses("mdc-tab-scroller")
      div
    }
    val scrollArea: html.Div = {
      val div = dom.create.div
      div.addClasses("mdc-tab-scroller__scroll-area")
      div
    }
    val scrollContent: html.Div = {
      val div = dom.create.div
      div.addClasses("mdc-tab-scroller__scroll-content")
      div
    }
  }

  elements.scrollArea.appendChild(elements.scrollContent)
  elements.scroller.appendChild(elements.scrollArea)
  element.appendChild(elements.scroller)

  val tabs: Container = new Container(elements.scrollContent)

  // Listen for tab activation events (fired by MDC after adapter is initialized)
  element.addEventListener("MDCTabBar:activated", (evt: org.scalajs.dom.Event) => {
    val detail = evt.asInstanceOf[js.Dynamic].detail
    val index = detail.index.asInstanceOf[Int]
    onActivated @= index
  })

  private lazy val adapter: MDCTabBarImplementation =
    MaterialComponents.verified(MDCTabBar.attachTo(element))

  /** Initialize the MDC adapter. Call after all tabs have been added. */
  def init(): Unit = { val _ = adapter }

  def activateTab(index: Int): Unit = adapter.activateTab(index)
  def scrollIntoView(index: Int): Unit = adapter.scrollIntoView(index)
}

@js.native
@JSGlobal("mdc.tabBar.MDCTabBar")
object MDCTabBar extends js.Object {
  def attachTo(element: html.Element): MDCTabBarImplementation = js.native
}
