package io.youi.example.ui

import io.youi.{UI, dom}

import com.outr.scribe._

import scala.scalajs.js.annotation.JSExportTopLevel
import org.scalajs.dom._

object UIExamples {
  val examples = List(
    "Hello World" -> (() => HelloWorld),
    "Image Example" -> (() => ImageExample)
  )
  val container: html.Div = dom.create[html.Div]("div")

  @JSExportTopLevel("examples")
  def main(): Unit = {
    examples.foreach {
      case (name, function) => {
        val button = dom.create[html.Button]("button")
        button.innerHTML = name
        button.addEventListener("click", (evt: Event) => {
          evt.preventDefault()
          evt.stopPropagation()

          logger.info(s"Hiding...")
          container.style.display = "none"
          logger.info("Calling function...")
          function()
          logger.info("Calling init...")
          UI.init()
          logger.info("Finished!")
        })
        container.appendChild(button)
      }
    }

    document.body.appendChild(container)
  }
}