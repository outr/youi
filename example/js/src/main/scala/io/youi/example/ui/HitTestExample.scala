//package io.youi.example.ui
//
//import io.youi._
//import io.youi.component.Container
//import io.youi.example.screen.UIExampleScreen
//import io.youi.net._
//
//import scala.concurrent.Future
//
//class HitTestExample extends UIExampleScreen {
//  override def title: String = "Hit Test"
//  override def path: Path = path"/examples/hit.html"
//
//  override def createUI(): Future[Unit] = Future.successful {
//    container.children += Container(
//      new Box(Color.Cyan) {                       // Top-Left
//        position.left @= 50.0
//        position.top @= 50.0
//      },
//      new Box(Color.Magenta) {                    // Top-Right
//        position.right := container.size.width - 50.0
//        position.top @= 50.0
//        opacity @= 0.5
//      },
//      new Box(Color.Yellow) {                     // Bottom-Left
//        position.left @= 50.0
//        position.bottom := container.size.height - 50.0
//      },
//      new Box(Color.Black) {                      // Bottom-Right
//        position.right := container.size.width - 50.0
//        position.bottom := container.size.height - 50.0
//      },
//      new Box(Color.DarkRed) {                    // Center
//        position.center := container.size.center
//        position.middle := container.size.middle
//        size.width @= 200.0
//        size.height @= 200.0
//
//        children += new Box(Color.Green) {
//          position.x @= 100.0
//          position.y @= 100.0
//        }
//      }
//    )
//  }
//
//  class Box(color: Color) extends Container {
//    background @= color.withAlpha(0.5)
//    size.width @= 100.0
//    size.height @= 100.0
//
//    event.pointer.overState.attach {
//      case true => background @= color
//      case false => background @= color.withAlpha(0.5)
//    }
//  }
//}