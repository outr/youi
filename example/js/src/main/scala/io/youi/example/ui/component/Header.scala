package io.youi.example.ui.component

import io.youi.component.{Container, ImageView, TextView}
import io.youi.example.ClientExampleApplication
import io.youi._
import io.youi.app.screen.ScreenManager
import io.youi.component.extras.HTMLImageViewImplementation
import io.youi.font.GoogleFont
import io.youi.image.Image
import io.youi.paint.Paint

import scala.concurrent.ExecutionContext.Implicits.global

class Header extends Container {
  protected def application: ClientExampleApplication.type = ClientExampleApplication

  position.left := 0.0
  position.top := 0.0
  size.width := ui.size.width
  size.height := 125.0
  background := Paint.vertical(125.0).distributeColors(Color.White, Color.LightGray, Color.DarkGray)

  val logo: ImageView = new ImageView(HTMLImageViewImplementation) {
    Image("/images/youi.png").foreach { img =>
      image := img
    }
    size.width := 306.0
    size.height := 117.0
    position.left := 25.0
    position.top := 4.0
  }

  val title: TextView = new TextView {
    GoogleFont.`Open Sans`.`600`.load().foreach { fnt =>
      font.family := fnt
      font.weight := fnt
    }
    font.size := 20.pt
    color := application.colors.blue.dark
    ScreenManager().active.attachAndFire { screen =>
      value := screen.title
    }
    position.right := ui.size.width - 25.0
    position.top := 15.0
  }

  children += logo
  children += title
}