package org.hyperscala.web.live

import org.hyperscala.html.tag._
import org.powerscala.hierarchy.ContainerView
import org.hyperscala.html.HTMLTag
import org.hyperscala.html.attributes.{Method, ButtonType}
import org.hyperscala.web.event.FormSubmit

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait LiveForm extends Form {
  val view = new ContainerView[HTMLTag](this)

  view.live {
    case input: Input => {
      input.event.change := LiveEvent()
    }
    case button: Button => {
      button.event.click := LiveEvent()

      button.listeners.synchronous {
        case evt: ClickEvent => {
          if (button.buttonType() == ButtonType.Submit) {
            LiveForm.this.fire(FormSubmit(Method.Post))
          }
          button.fire(FormSubmit(Method.Post))
        }
      }
    }
    case select: Select => {
      select.event.change := LiveEvent()
    }
    case textArea: TextArea => {
      textArea.event.change := LiveEvent()
    }
    case _ => // Ignore
  }
}
