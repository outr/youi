//package io.youi.example.ui
//
//import io.youi.Color
//import io.youi.component.DrawableComponent
//import io.youi.drawable.{Group, TextDrawable, Transformation}
//import io.youi.example.screen.UIExampleScreen
//import io.youi.font.{CanvasFont, CanvasText, GoogleFont}
//import io.youi.image.Image
//import io.youi.net._
//import io.youi.paint.{Border, Stroke}
//
//import scala.concurrent.Future
//import scala.concurrent.ExecutionContext.Implicits.global
//
//class DrawableExample extends UIExampleScreen {
//  override def title: String = "Drawable Example"
//  override def path: Path = path"/examples/drawable.html"
//
//  override def createUI(): Future[Unit] = for {
//    image <- Image("/images/cuteness.jpg")
//    fnt <- GoogleFont.`Lobster`.`regular`.load()
//  } yield {
//    val component = new DrawableComponent {
//      border @= Border(Stroke(Color.Red, lineWidth = 2.0), radius = 5.0)
//      size.width @= image.width
//      size.height @= image.height
//      position.center := container.size.center
//      position.middle := container.size.middle
//
////      val font = CanvasFont("Arial", "normal", "normal", "normal")
//      val font = CanvasFont(fnt.font.family, "normal", "normal", fnt.name)
//      scribe.info(s"Font: $fnt")
//      val text = CanvasText(font, "Hello, Arial!", 36.0, Int.MaxValue, kerning = false)
//      val textDrawable = new TextDrawable(text, Color.White, Stroke.none)
//      drawable @= Group(
//        image,
//        Transformation(50.0, 25.0)(textDrawable)
//      )
//    }
//    container.children += component
//  }
//}
