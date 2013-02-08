package org.hyperscala.examples.comparison

import org.hyperscala.html._
import org.hyperscala.web.site.Webpage
import org.hyperscala.ui.DynamicContent
import org.hyperscala.ui.validation._
import org.hyperscala.realtime.Realtime
import org.hyperscala.event.{ClickEvent, JavaScriptEvent}
import org.hyperscala.css.attributes.Display
import org.powerscala.{IO, Color}

/**
 * Comparison port of Play! Framework's Hello World example that's not really a Hello World example.
 *
 * Reference to the original code found here: https://github.com/playframework/Play20/tree/master/samples/scala/helloworld
 *
 * @author Matt Hicks <mhicks@outr.com>
 */
class PlayHelloWorldPage extends Webpage {
  require(Realtime)
  Realtime.connectStandard()

  title := "The 'helloworld' application"

  head.contents += new tag.Link(rel = "stylesheet", media = "screen", href = "/css/play_hello_world.css")

  val main = DynamicContent(PlayHelloWorldPage.main, "main")
  val header = main.load[tag.Header]("header")
  val content = main.load[tag.Section]("content")
  val submitButton = main.load[tag.Input]("submit")

  val configuration = new PlayHelloWorldConfiguration(this)
  content.contents += configuration

  val results = new tag.Div(id = "results")
  content.contents += results
  body.contents += main

  submitButton.listeners.synchronous {
    case evt: ClickEvent => if (configuration.style.display.getOrElse(Display.Block) == Display.None) {
      showConfigure()
    } else if (ValidatableTag.validateAll(configuration)) {
      showResults()
    }
  }

  def showResults() = {
    title := "Here is the result:"
    header.contents.replaceWith(new tag.A(href = "#", content = "Here is the result:") {
      event.click := JavaScriptEvent()
      listeners.synchronous {
        case evt: ClickEvent => showConfigure()
      }
    })
    submitButton.value := "Back to the form"

    configuration.style.display = Display.None
    results.style.display = Display.Block

    val resultName = configuration.nameInput.value()
    val repeat = configuration.repeatInput.value().toInt
    results.contents.replaceWith(new tag.Ul {
      style.color = configuration.colorSelect.value() match {
        case "red" => Color.Red
        case "green" => Color.Green
        case "blue" => Color.Blue
        case _ => Color.Black
      }

      (0 until repeat).foreach {
        case index => contents += new tag.Li(content = resultName)
      }
    })
  }

  def showConfigure() = {
    title := "The 'helloworld' application"
    header.contents.replaceWith(new tag.A(href = "#", content = "The 'helloworld' application"))
    submitButton.value := "Submit Query"

    configuration.style.display = Display.Block
    results.style.display = Display.None
  }
}

class PlayHelloWorldConfiguration(page: PlayHelloWorldPage) extends DynamicContent("configure") {
  def content = PlayHelloWorldPage.configuration

  val nameContainer = load[tag.Dl]("name_field")
  val nameInput = nameContainer.getById[tag.Input]("name")
  val nameError = nameContainer.getById[tag.Dd]("name_error")
  val repeatContainer = load[tag.Dl]("repeat_field")
  val repeatInput = repeatContainer.getById[tag.Input]("repeat")
  val repeatError = repeatContainer.getById[tag.Dd]("repeat_error")
  val colorContainer = load[tag.Dl]("color_field")
  val colorSelect = colorContainer.getById[tag.Select]("color")
  val colorError = colorContainer.getById[tag.Dd]("color_error")

  nameInput.addValidation(NotEmpty)(new ClassValidationHandler(classTag = nameContainer, errorContainer = nameError))
  repeatInput.addValidation(IntBetween(min = 1, max = 100))(new ClassValidationHandler(classTag = repeatContainer, errorContainer = repeatError))
}

object PlayHelloWorldPage {
  val main = load("play_hello_world.html")
  val configuration = load("play_hello_world_configuration.html")

  def load(filename: String) = IO.copy(getClass.getClassLoader.getResource(filename))
}