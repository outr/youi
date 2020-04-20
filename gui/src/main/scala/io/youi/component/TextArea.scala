package io.youi.component

import io.youi.component.support.FontSupport
import io.youi.component.types.Prop
import io.youi.dom
import org.scalajs.dom.{Event, html}

class TextArea(element: html.TextArea = dom.create.textArea) extends Component(element) with FontSupport {
  lazy val value: Prop[String] = new Prop[String](element.value, element.value_=)
  lazy val placeholder: Prop[String] = new Prop[String](element.placeholder, element.placeholder_=)
  lazy val disabled: Prop[Boolean] = new Prop[Boolean](element.disabled, element.disabled_=)
  lazy val spellCheck: Prop[Boolean] = new Prop[Boolean](element.spellcheck, element.spellcheck_=)

  element.style.setProperty("resize", "none")
  element.addEventListener("input", (_: Event) => {
    Prop.changing(value) {
      value @= element.value
    }
  })

  def focus(): Unit = element.focus()
  def blur(): Unit = element.blur()
}