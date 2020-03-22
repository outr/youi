package io.youi.material

import io.youi.component.Component
import io.youi.dom
import io.youi.dom._
import io.youi.event.EventSupport
import org.scalajs.dom.html
import reactify.Var

class MDCChip extends Component(dom.create.div) with EventSupport {
  val content: Var[String] = Var("")
  val leading: Var[Option[MaterialIcon]] = Var(None)
  val trailing: Var[Option[MaterialIcon]] = Var(None)

  def this(content: String, leading: Option[MaterialIcon] = None, trailing: Option[MaterialIcon] = None) = {
    this()
    this.content @= content
    this.leading @= leading
    this.trailing @= trailing
  }

  classes := Set("mdc-chip")
  element.setAttribute("role", "row")

  private object elements {
    val ripple: html.Div = {
      val div = dom.create.div
      div.addClasses("mdc-chip__ripple")
      div
    }
    val leadingIcon: html.Element = {
      val i = dom.create.i
      i.addClasses("material-icons", "mdc-chip__icon", "mdc-chip__icon--leading")
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
    val trailingIcon: html.Element = {
      val i = dom.create.i
      i.addClasses("material-icons", "mdc-chip__icon", "mdc-chip__icon--trailing")
      color.attach(c => i.style.color = c.toRGBA)
      trailing.attachAndFire {
        case Some(mi) => {
          trailingCell.style.display = "block"
          i.innerText = mi.name
        }
        case None => trailingCell.style.display = "none"
      }
      i
    }
  }

  element.appendChild(elements.ripple)
  element.appendChild(elements.leadingIcon)
  elements.button.appendChild(elements.label)
  elements.cell.appendChild(elements.button)
  element.appendChild(elements.cell)
  elements.trailingCell.appendChild(elements.trailingIcon)
  element.appendChild(elements.trailingCell)
}
