package youi.example.ui.component

import rapid.Task
import youi._
import youi.app.screen.ScreenManager
import youi.component.support._
import youi.component.types.{Cursor, Display, PositionType, WhiteSpace}
import youi.component.{Component, Container, ImageView}
import youi.event.EventSupport
import youi.example.ExampleApp
import youi.example.screen.UIExampleScreen
import youi.font.GoogleFont
import youi.material.Material
import youi.paint.Paint
import reactify._
import spice.net._

class Header extends Container { self =>
  private def narrow: Boolean = ui.size.width() <= 600.0

  size.width := ui.size.width
  size.height := (if (narrow) 50.0 else 75.0)

  private val logo: ImageView = new ImageView with MarginSupport with EventSupport {
    src @= "/images/youi.png"
    size.height := (if (narrow) 40.0 else 65.0)
    margin.left @= 10.0
    margin.top := (if (narrow) 5.0 else 5.0)

    event.click.on {
      History.pushPath(path"/ui-examples.html")
    }
  }

  private val darkModeToggle = new Component(dom.create.i) with FontSupport with EventSupport with ContentSupport {
    classes += "material-icons"
    content @= Material.Icons.Image.Brightness7.name
    font.size := (if (narrow) 20.0 else 24.0)
    cursor @= Cursor.Pointer
    position.`type` @= PositionType.Absolute
    position.right := ui.size.width - 25.0
    position.middle := self.size.middle
    event.click.on {
      ExampleApp.darkMode @= !ExampleApp.darkMode()
    }
  }

  private val heading = new Component(dom.create.span) with FontSupport with ContentSupport {
    font.size := (if (narrow) 14.0 else 20.pt)
    ScreenManager().active.attachAndFire { screen =>
      content @= screen.title
    }
    whiteSpace @= WhiteSpace.NoWrap
    position.`type` @= PositionType.Absolute
    position.right := darkModeToggle.position.left - 10.0
  }

  private val link = new Component(dom.create.span) with FontSupport with EventSupport with ContentSupport {
    font.size := (if (narrow) 11.0 else 14.pt)
    content @= "View Source"
    cursor @= Cursor.Pointer
    ScreenManager().active.attachAndFire {
      case _: UIExampleScreen => display @= Display.Block
      case _ => display @= Display.None
    }
    event.click.on {
      ScreenManager().active() match {
        case screen: UIExampleScreen => History.set(screen.url, "_blank")
        case _ =>
      }
    }
    whiteSpace @= WhiteSpace.NoWrap
    position.`type` @= PositionType.Absolute
    position.right := darkModeToggle.position.left - 10.0
    position.top := heading.position.bottom - 10.0
  }

  // Center heading+link block vertically within the header
  heading.position.top := (self.size.height - (heading.measured.height + link.measured.height - 10.0)) / 2.0

  GoogleFont.`Open Sans`.`600`.load().map { font =>
    heading.font.weight @= font
    link.font.weight @= font
  }.handleError { t => Task(scribe.error(s"Failed to load font", t)) }.startUnit()

  // Load Material Icons font for the dark mode toggle icon
  Material.load().handleError { t => Task(scribe.error(s"Failed to load Material Icons", t)) }.startUnit()

  heading.color := ExampleApp.accentColor
  link.color := ExampleApp.accentColor
  darkModeToggle.color := ExampleApp.accentColor

  private def updateBackground(): Unit = {
    val h = size.height()
    if (ExampleApp.darkMode()) {
      background @= Paint.vertical(h).distributeColors(
        Color.fromHex("1a1a2e"), Color.fromHex("16213e"), Color.fromHex("0f3460")
      )
    } else {
      background @= Paint.vertical(h).distributeColors(Color.White, Color.LightGray, Color.DarkGray)
    }
  }

  ExampleApp.darkMode.attachAndFire { isDark =>
    if (isDark) {
      darkModeToggle.content @= Material.Icons.Image.Brightness4.name
    } else {
      darkModeToggle.content @= Material.Icons.Image.Brightness7.name
    }
    updateBackground()
  }
  size.height.on(updateBackground())

  children ++= List(logo, darkModeToggle, heading, link)
}