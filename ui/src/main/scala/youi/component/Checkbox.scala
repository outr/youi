package youi.component

import youi.component.support.FontSupport
import youi.component.types.Prop
import youi.dom
import org.scalajs.dom.{Event, html}

class Checkbox(element: html.Input = dom.create.input) extends Component(element) with FontSupport {
  lazy val name: Prop[String] = new Prop[String](element.name, element.name_=)
  lazy val checked: Prop[Boolean] = new Prop[Boolean](element.checked, element.checked_=)
  lazy val disabled: Prop[Boolean] = new Prop[Boolean](element.disabled, element.disabled_=)

  element.`type` = "checkbox"
  element.addEventListener("change", (_: Event) => {
    checked @= element.checked
  })

  def focus(): Unit = element.focus()
  def blur(): Unit = element.blur()
}