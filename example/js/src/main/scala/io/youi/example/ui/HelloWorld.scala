package io.youi.example.ui

import io.youi._
import io.youi.dom._
import io.youi.font.GoogleFont
import io.youi.component._
import io.youi.component.support.{BorderSupport, MarginSupport, MeasuredSupport, PositionSupport, SizeSupport}
import io.youi.component.types.{Border, BorderStyle, Display, DropType, PositionType, SizeType}
import io.youi.easing.Linear
import io.youi.event.EventSupport
import io.youi.example.screen.UIExampleScreen
import io.youi.material.{MDCTextField, Material}
import io.youi.net._
import org.scalajs.dom.html
import reactify._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js

class HelloWorld extends UIExampleScreen {
  override def title: String = "Hello World"
  override def path: Path = path"/examples/hello.html"

  private val text = new TextView() with PositionSupport with MeasuredSupport with EventSupport {
    content @= "Hello, World!"
    font.size @= 64.px
    color @= Color.DarkBlue
    position.`type` @= PositionType.Absolute
    position.center := ui.size.center
    position.middle := ui.size.middle
  }

  override def createUI(): Future[Unit] = GoogleFont.`Lobster`.load().map { fnt =>
    text.font.family @= fnt.family
    container.children += text

    /*val popup = new Popup(showGlassPane = false) with EventSupport
    popup.easing @= Linear
    container.children += popup

    text.event.click.on {
      popup.show()
    }
    popup.event.click.on {
      popup.hide()
    }*/

    /*val dropdown = new Drop with EventSupport
    dropdown.backgroundColor @= Color.LightCoral
    dropdown.border.radius @= 5.0
    dropdown.container.children += new TextView {
      content @= "This is dropdown text!<br/>One<br/>Two<br/>Three"
    }
    container.children += dropdown

    text.event.click.attach { evt =>
      dropdown.toggle(text, DropType.Up)
    }
    dropdown.event.click.on {
      dropdown.hide()
    }*/

    /*
    <button class="mdc-button foo-button">
  <div class="mdc-button__ripple"></div>
  <span class="mdc-button__label">Button</span>
</button>
     */

    val button = dom.create.button
    button.addClasses("mdc-button", "foo-button")

    val div = dom.create.div
    div.addClasses("mdc-button__ripple")
    button.appendChild(div)

    val span = dom.create.span
    span.addClasses("mdc-button__label")
    span.innerHTML = "Button"
    button.appendChild(span)

    container.appendChild(button)

    /*val field = dom.fromString[html.Div](
      """
        |<div class="mdc-text-field username">
        |  <input type="text" class="mdc-text-field__input" id="username-input" name="username">
        |  <label class="mdc-floating-label" for="username-input">Username</label>
        |  <div class="mdc-line-ripple"></div>
        |</div>
        |""".stripMargin).head
    container.appendChild(field)*/

    val textField = new MDCTextField
    textField.id @= "myTextField"
    textField.label @= "Username Test"
    container.children += textField

    text.event.click.on {
      textField.shakeLabel()
    }

    /*val example = new Container with MarginSupport with SizeSupport with BorderSupport {
      backgroundColor @= Color.Yellow
      color @= Color.Black
      border @= Border(2.0, BorderStyle.Solid, Color.Red)
      size.height := container.size.height

      content @= "Hello, world!"
    }
    val sidebar = new Sidebar(container = Some(example))
    sidebar.backgroundColor @= Color.Green
    sidebar.content @= "Hello, world! Hello, world! Hello, world! Hello, world! Hello, world! Hello, world! Hello, world! Hello, world! Hello, world!"
    container.children += sidebar
    container.children += example

    text.event.click.on {
      sidebar.open @= !sidebar.open
    }*/
  }
}