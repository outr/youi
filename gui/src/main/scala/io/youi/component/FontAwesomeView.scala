package io.youi.component

import io.youi.component.support.FontSupport
import io.youi.dom
import io.youi.font.{FontAwesome, FontAwesomeIcon}
import org.scalajs.dom.html
import reactify.Var

class FontAwesomeView(element: html.Element = dom.create.i) extends Component(element) with FontSupport {
  lazy val icon: Var[FontAwesomeIcon] = Var[FontAwesomeIcon](FontAwesome.None)

  private var previous = Option.empty[FontAwesomeIcon]
  icon.attachAndFire { icon =>
    previous.foreach { i =>
      classes -= i.prefix
      classes -= s"fa-${i.name}"
    }
    if (icon != FontAwesome.None) {
      classes += icon.prefix
      classes += s"fa-${icon.name}"
      previous = Some(icon)
    } else {
      previous = None
    }
  }
}