package io.youi.example.ui.component

import cats.effect.unsafe.implicits.global
import io.youi._
import io.youi.app.screen.ScreenManager
import io.youi.component.support._
import io.youi.component.types.{Cursor, Display, PositionType, WhiteSpace}
import io.youi.component.{Component, Container, ImageView}
import io.youi.event.EventSupport
import io.youi.example.screen.UIExampleScreen
import io.youi.font.GoogleFont
import io.youi.paint.Paint
import reactify._
import spice.net._

class Header extends Container with SizeSupport { self =>
  size.width := ui.size.width
  size.height @= 75.0
  background := Paint.vertical(75.0).distributeColors(Color.White, Color.LightGray, Color.DarkGray)

  private val logo: ImageView = new ImageView with SizeSupport with MarginSupport with EventSupport {
    src @= "/images/youi.png"
    size.height @= 65.0
    margin.left @= 10.0
    margin.top @= 5.0

    event.click.on {
      History.pushPath(path"/ui-examples.html")
    }
  }

  private val heading = new Component(dom.create.span) with FontSupport with PositionSupport with MeasuredSupport with ContentSupport {
    font.size @= 20.pt
    color @= Color.Blue //application.colors.blue.dark
    ScreenManager().active.attachAndFire { screen =>
      content @= screen.title
    }
    whiteSpace @= WhiteSpace.NoWrap
    position.`type` @= PositionType.Absolute
    position.right := ui.size.width - 25.0
    position.top @= 15.0
  }

  private val link = new Component(dom.create.span) with FontSupport with PositionSupport with MeasuredSupport with EventSupport with ContentSupport {
    font.size @= 14.pt
    color @= Color.Blue //application.colors.blue.dark
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
    position.right := ui.size.width - 25.0
    position.top := heading.position.bottom - 10.0
  }

  GoogleFont.`Open Sans`.`600`.load().unsafeRunAsync {
    case Left(t) => scribe.error(s"Failed to load font", t)
    case Right(font) =>
      heading.font.weight @= font
      link.font.weight @= font
  }

  children ++= List(logo, heading, link)
}