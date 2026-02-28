//package youi.example.ui.drawable
//
//import youi._
//import youi.app.screen.DrawableScreen
//import youi.drawable.Group
//import youi.example.ui.UIExampleScreen
//import youi.paint.{LineCap, LineJoin, Stroke}
//import youi.path._
//
//import scala.concurrent.Future
//
//object PathsExample extends UIExampleScreen with DrawableScreen {
//  override def name: String = "Paths Example"
//
//  override protected val drawable = Future.successful(Group(
//    BeginPath,
//    Path(
//      MoveTo(50.0, 50.0),
//      LineTo(150.0, 50.0),
//      LineTo(150.0, 150.0),
//      LineTo(50.0, 150.0),
//      LineTo(50.0, 50.0)
//    ),
//    ClosePath,
//    Fill(Color.Red.withAlpha(0.5)),
//    Path(
//      MoveTo(100.0, 100.0),
//      LineTo(200.0, 100.0),
//      LineTo(200.0, 200.0),
//      LineTo(100.0, 200.0),
//      LineTo(100.0, 100.0)
//    ),
//    Fill(Color.Green.withAlpha(0.5)),
//    Path(
//      MoveTo(150.0, 150.0),
//      LineTo(250.0, 150.0),
//      LineTo(250.0, 250.0),
//      LineTo(150.0, 250.0),
//      LineTo(150.0, 150.0)
//    ),
//    Fill(Color.Blue.withAlpha(0.5)),
//    Path.begin.move(10.0, 350.0).line(390.0, 350.0).close,
//    Stroke(Color.Red, None, 2.0, List(5.0, 10.0), 0.0, LineCap.Butt, LineJoin.Miter)
//  ))
//
//  override def path: String = "/examples/drawable/paths.html"
//}