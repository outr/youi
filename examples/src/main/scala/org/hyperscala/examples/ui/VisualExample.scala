package org.hyperscala.examples.ui

import org.hyperscala.html._
import org.hyperscala.web.live.{ClickEvent, LiveEvent, LivePage}
import org.hyperscala.ui.widgets.visual.{StandardVisual, Visual}
import org.hyperscala.css.attributes._

import org.powerscala.property._

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class VisualExample extends LivePage {
  body.style.font.family := "sans-serif"

  intercept.beforeRender {
    case v: StandardVisual[_] => {
      v.labelDiv.style.float := Float.Left
      v.labelDiv.style.width := 300.px
      v.labelDiv.style.text.align := Alignment.Right
      v.labelDiv.style.padding.all := 5.px
      v.readDiv.style.padding.all := 5.px
      v.editableDiv.style.padding.all := 5.px
    }
  }

  val stringVisual = Visual[String]().label("String Visual").editing.build()
  stringVisual.property := "Hello World!"

  body.contents += stringVisual

  body.contents += new tag.Button(content = "Toggle Editing") {
    event.click := LiveEvent()

    listeners.synchronous {
      case evt: ClickEvent => {
        stringVisual.editing := !stringVisual.editing()
      }
    }
  }

  body.contents += new tag.Button(content = "Set Value") {
    event.click := LiveEvent()

    listeners.synchronous {
      case evt: ClickEvent => stringVisual.property := "Value Set!"
    }
  }
}