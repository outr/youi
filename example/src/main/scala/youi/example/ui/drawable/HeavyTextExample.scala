//package youi.example.ui.drawable
//
//import youi.Color
//import youi.app.screen.DrawableScreen
//import youi.drawable._
//import youi.drawable.stats.RenderTimer
//import youi.example.ui.UIExampleScreen
//import youi.font.{GoogleFont, OpenTypeFont}
//import youi.paint.Paint
//
//import scala.concurrent.Future
//
//object HeavyTextExample extends UIExampleScreen with DrawableScreen {
//  override def name: String = "Heavy Text Example"
//
//  override protected val drawable: Future[RenderTimer] = OpenTypeFont.fromURL(GoogleFont.`Open Sans`.`regular`).map { font =>
//    def row(y: Double, size: Double, fill: Paint): Group = {
//      val chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789~!@#$%^&*()-=_+{}[]:".map { c =>
//        font(c.toString, size).toDrawable(fill)
//      }.zipWithIndex.map {
//        case (c, index) => Transformation(x = (size * 0.7) * index, y = y)(c)
//      }
//      Group(chars: _*)
//    }
//    def rows = (0 until 80).map { index =>
//      row(10.0 * index, 26.0, Color.random)
//    }
//    RenderTimer(
//      Group(rows: _*)
//    )
//  }
//
//  override def path: String = "/examples/drawable/heavy-text.html"
//}