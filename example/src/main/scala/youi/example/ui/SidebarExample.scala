package youi.example.ui

import rapid.Task
import youi._
import youi.component._
import youi.component.support.{BorderSupport, ContentSupport, MarginSupport}
import youi.component.types.{Border, BorderStyle, PositionType}
import youi.event.EventSupport
import youi.example.ExampleApp
import youi.example.screen.UIExampleScreen
import youi.font.GoogleFont
import spice.net._
import reactify._

class SidebarExample extends UIExampleScreen {
  override def title: String = "Sidebar Example"
  override def path: URLPath = path"/examples/sidebar.html"

  private val text = new TextView() with EventSupport {
    content @= "Toggle Sidebar"
    font.size @= 64.px
    color := ExampleApp.textColor
    position.`type` @= PositionType.Absolute
    position.center := ui.size.center
    position.middle := ui.size.middle
  }

  override def createUI(): Task[Unit] = for {
    fnt <- GoogleFont.`Lobster`.load()
  } yield {
    text.font.family @= fnt.family
    container.children += text

    val example = new Container with MarginSupport with BorderSupport with ContentSupport {
      backgroundColor @= Color.Yellow
      color @= Color.Black
      border @= Border(2.0, BorderStyle.Solid, Color.Red)
      size.height := container.size.height

      content @= "Hello, world!"
    }
    val sidebar = new Sidebar(container = Some(example)) with ContentSupport with BorderSupport
    sidebar.backgroundColor @= Color.Green
    sidebar.border @= Border(2.0, BorderStyle.Solid, Color.Red)
    if (!isMobileDevice) {
      sidebar.position.top := 0.0
      sidebar.size.height := container.size.height
    }
    sidebar.content @= "Hello, world! Hello, world! Hello, world! Hello, world! Hello, world! Hello, world! Hello, world! Hello, world! Hello, world!"
    container.children += sidebar
    container.children += example

    text.event.click.on {
      sidebar.open @= !sidebar.open
    }
  }
}