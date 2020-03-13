package io.youi.example.ui

import io.youi._
import io.youi.dom._
import io.youi.font.GoogleFont
import io.youi.component._
import io.youi.component.support.{BorderSupport, MarginSupport, MeasuredSupport, PositionSupport, SizeSupport}
import io.youi.component.types.{Border, BorderStyle, Display, DropType, PositionType, SizeType}
import io.youi.easing.Linear
import io.youi.event.EventSupport
import io.youi.example.ClientExampleApplication
import io.youi.example.screen.UIExampleScreen
import io.youi.material.{MDCButton, MDCIconButton, MDCIconButtonToggle, MDCTextField, MDCTopAppBar, Material, MaterialComponents}
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

  override def createUI(): Future[Unit] = for {
    fnt <- GoogleFont.`Lobster`.load()
    _ <- MaterialComponents.loaded
  } yield {
    MaterialComponents.theme.primary := ClientExampleApplication.colors.blue.dark

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

    val button = new MDCButton
    button.label @= "My Button"
    container.children += button

    val textField = new MDCTextField
    textField.label @= "Username Test"
    container.children += textField

    val iconButton = new MDCIconButton
    container.children += iconButton

    val iconButtonToggle = new MDCIconButtonToggle
    container.children += iconButtonToggle

    val topBar = new MDCTopAppBar
    topBar.menu.event.click.on {
      topBar.collapsed @= !topBar.collapsed()
    }
    topBar.main.children += new MDCTextField {
      label @= "Testing"
    }
    topBar.controls.children += new MDCIconButton(Material.Icons.Action.Bookmarks)
    topBar.controls.children += new MDCIconButton(Material.Icons.Action.Eject)
    container.children += topBar

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