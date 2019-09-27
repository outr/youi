package io.youi.example.ui.component

import io.youi.component.{Container, HTMLTextView, ImageView}
import io.youi.example.ClientExampleApplication
import io.youi._
import io.youi.app.screen.ScreenManager
import io.youi.example.screen.UIExampleScreen
import io.youi.example.ui.UIExamples
import io.youi.font.GoogleFont
import io.youi.image.Image
import io.youi.paint.Paint
import io.youi.style.{Display, Position, WhiteSpace}
import reactify._

import scala.concurrent.ExecutionContext.Implicits.global

class Header extends Container { self =>
  protected def application: ClientExampleApplication.type = ClientExampleApplication

  position.`type` @= Position.Absolute
  position.left @= 0.0
  position.top @= 0.0
  size.width := ui.size.width
  size.height @= 75.0
  background := Paint.vertical(75.0).distributeColors(Color.White, Color.LightGray, Color.DarkGray)

  val logo: ImageView = new ImageView {
    Image("/images/youi.png").foreach { img =>
      image @= img
      size @= img.size.scale(height = Some(65.0))
    }
    position.left @= 10.0
    position.middle := self.size.middle
    event.link("/ui-examples.html")
  }

  val title: HTMLTextView = new HTMLTextView {
    GoogleFont.`Open Sans`.`600`.load().foreach { fnt =>
      font @= fnt
    }
    font.size @= 20.pt
    color @= application.colors.blue.dark
    ScreenManager().active.attachAndFire { screen =>
      value @= screen.title
    }
    whiteSpace @= WhiteSpace.NoWrap
    position.right := ui.size.width - 25.0
    position.top @= 15.0
  }

  val link: HTMLTextView = new HTMLTextView {
    GoogleFont.`Open Sans`.`600`.load().foreach { fnt =>
      font @= fnt
    }
    font.size @= 14.pt
    color @= application.colors.blue.dark
    value @= "View Source"
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
    position.right := ui.size.width - 25.0
    position.top := title.position.bottom - 5.0
  }

  children ++= List(logo, title, link)
}