//package youi.example.ui.hypertext
//
//import youi._
//import youi.example.ui.UIExampleScreen
//import youi.hypertext.Container
//
//trait HTMLScreen extends UIExampleScreen {
//  lazy val container: Container = {
//    val c = Container.cached(content)
//    AnimationFrame.delta.attach(c.update)
//    c
//  }
//}
