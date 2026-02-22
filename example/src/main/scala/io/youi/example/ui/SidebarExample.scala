package io.youi.example.ui

import rapid.Task
import io.youi._
import io.youi.component._
import io.youi.component.support.{BorderSupport, ContentSupport, MarginSupport}
import io.youi.component.types.{Border, BorderStyle, PositionType}
import io.youi.event.EventSupport
import io.youi.example.screen.UIExampleScreen
import io.youi.font.GoogleFont
import spice.net._
import reactify._

class SidebarExample extends UIExampleScreen {
  override def title: String = "Sidebar Example"
  override def path: URLPath = path"/examples/sidebar.html"

  private val text = new TextView() with EventSupport {
    content @= "Toggle Sidebar"
    font.size @= 64.px
    color @= Color.DarkBlue
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
    val sidebar = new Sidebar(container = Some(example)) with ContentSupport
    sidebar.backgroundColor @= Color.Green
    if (!isMobileDevice) {
      sidebar.position.top := header.size.height
      sidebar.size.height := ui.size.height - header.size.height
    }
    sidebar.content @= "Hello, world! Hello, world! Hello, world! Hello, world! Hello, world! Hello, world! Hello, world! Hello, world! Hello, world!"
    container.children += sidebar
    container.children += example

    text.event.click.on {
      sidebar.open @= !sidebar.open
    }
  }
}