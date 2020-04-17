package io.youi.material

import io.youi.component.Component
import io.youi.component.support.{ContentSupport, InternalContainerSupport}
import io.youi.component.types.Display
import io.youi.dom
import io.youi.dom._
import io.youi.event.EventSupport
import org.scalajs.dom.html
import reactify.Var

class MDCChip extends Component(dom.create.div) with EventSupport with InternalContainerSupport[Component] {
  val content: Var[String] = Var("")
  val leading: ChipIcon = new ChipIcon("leading")
  val trailing: ChipIcon = new ChipIcon("trailing")

  def this(content: String, leading: MaterialIcon = Material.Icons.Empty, trailing: MaterialIcon = Material.Icons.Empty) = {
    this()
    this.content @= content
    this.leading.value @= leading
    this.trailing.value @= trailing
  }

  classes := Set("mdc-chip")
  element.setAttribute("role", "row")

  protected object elements {
    val ripple: html.Div = {
      val div = dom.create.div
      div.addClasses("mdc-chip__ripple")
      div
    }
    val cell: html.Span = {
      val span = dom.create.span
      span.setAttribute("role", "gridcell")
      span
    }
    val button: html.Span = {
      val span = dom.create.span
      span.setAttribute("role", "button")
      span.addClasses("mdc-chip__primary-action")
      span
    }
    val label: html.Span = {
      val span = dom.create.span
      span.addClasses("mdc-chip__text")
      content.attachAndFire(span.innerHTML_=)
      span
    }
    val trailingCell: html.Span = {
      val span = dom.create.span
      span.setAttribute("role", "gridcell")
      span
    }
  }

  children += elements.ripple
  children += leading
  children += elements.label
  children += elements.button
  children += elements.cell
  children += trailing
  children += elements.trailingCell

  class ChipIcon(position: String) extends Component(dom.create.i) with EventSupport with ContentSupport {
    val value: Var[MaterialIcon] = Var(Material.Icons.Empty)

    classes ++= List("material-icons", "mdc-chip__icon", s"mdc-chip__icon--$position")
    color := MDCChip.this.color
    display := (if (value().isEmpty) Display.None else Display.Block)
    content := value().name
  }
}
