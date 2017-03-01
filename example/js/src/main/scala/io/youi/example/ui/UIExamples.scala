package io.youi.example.ui

import io.youi.hypertext.Button
import io.youi.{History, UI}

import reactify._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object UIExamples extends UIExampleScreen {
  val examples: List[(String, () => UIExampleScreen)] = List(
    "Hello World" -> (() => HelloWorld),
    "Image Example" -> (() => ImageExample),
    "Label Example" -> (() => LabelExample),
    "Animation Example" -> (() => AnimationExample),
    "Virtual Size Example" -> (() => VirtualSizeExample)
  )

  override protected def load(): Future[Unit] = super.load().map { _ =>
    var previous: Option[Button] = None
    examples.foreach {
      case (screenName, function) => {
        val button = new Button {
          text := screenName
          size.actual.height.attach { d =>
            scribe.info(s"Height changed for $screenName button to $d")
          }
          previous.foreach { p =>
            position.top := p.position.bottom + 45.0
          }
          click.attach { evt =>
            evt.stopPropagation()
            evt.preventDefault()

//            container.visible := false
            val screen = function()
            History.pushPath(screen.path)
            UI.init()
            UI.title := screenName
          }
        }
        previous = Some(button)
        container.children += button
//        val button = dom.create[html.Button]("button")
//        button.innerHTML = name
//        button.style.display = "block"
//        button.style.clear = "both"
//        button.style.margin = "5px"
//        button.addEventListener("click", (evt: Event) => {
//          evt.preventDefault()
//          evt.stopPropagation()
//
//          container.visible := false
//          function()
//          UI.init()
//          UI.title := name
//        })
//
//        container.appendChild(button)
      }
    }
  }

  override def path: String = "/ui-examples.html"
}