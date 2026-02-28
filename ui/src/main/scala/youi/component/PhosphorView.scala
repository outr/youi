package youi.component

import youi.component.support.FontSupport
import youi.dom
import youi.font.{Phosphor, PhosphorIcon}
import org.scalajs.dom.html
import reactify.Var

class PhosphorView(element: html.Element = dom.create.i) extends Component(element) with FontSupport {
  lazy val icon: Var[PhosphorIcon] = Var[PhosphorIcon](Phosphor.None)

  private var previous = Option.empty[PhosphorIcon]
  icon.attachAndFire { icon =>
    previous.foreach { i =>
      classes -= i.weight
      classes -= s"ph-${i.name}"
    }
    if (icon != Phosphor.None) {
      classes += icon.weight
      classes += s"ph-${icon.name}"
      previous = Some(icon)
    } else {
      previous = None
    }
  }
}
