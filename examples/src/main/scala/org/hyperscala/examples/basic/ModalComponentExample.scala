package org.hyperscala.examples.basic

import org.hyperscala.css.attributes.Position
import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.realtime._
import org.hyperscala.selector.Selector
import org.hyperscala.ui.ModalComponent
import org.hyperscala.web._
import org.powerscala.Color

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ModalComponentExample extends Webpage with Example {
  require(ModalComponent)

  connected[Webpage] {
    case webpage => ModalComponent(webpage).modalClicked.on {
      case evt => ModalComponent(webpage).selected := null
    }
  }

  body.contents += new tag.Div {
    style.marginLeft := 100.px
    style.marginTop := 100.px
    contents += new tag.Div(id = "test1") {
      contents += "Simple inline layout"

      style.width := 500.px
      style.height := 400.px
      style.backgroundColor := Color.Aquamarine

      clickEvent.onRealtime {
        case evt => ModalComponent(this.webpage).selected := Selector.id(this)
      }
    }
  }

  body.contents += new tag.Div {
    style.marginLeft := 100.px
    style.marginTop := 100.px
    contents += new tag.Div(id = "test2") {
      contents += "Simple absolute layout"

      style.left := 100.px
      style.top := 100.px
      style.width := 200.px
      style.height := 200.px
      style.backgroundColor := Color.RosyBrown
      style.position := Position.Absolute

      clickEvent.onRealtime {
        case evt => ModalComponent(this.webpage).selected := Selector.id(this)
      }
    }
  }
}