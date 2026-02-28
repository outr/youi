//package youi.example.ui.hypertext
//
//import youi.hypertext.style.Image
//import youi.hypertext.{ImageView, Label}
//import youi.{Color, VirtualSizeSupport, ui}
//
//import scala.concurrent.Future
//
//object VirtualSizeExample extends VirtualSizeSupport with HTMLScreen {
//  override def name: String = "HTML Virtual Size Example"
//  override def path: String = "/examples/html/virtual.html"
//
//  override protected def load(): Future[Unit] = super.load().map { _ =>
//    container.children += new ImageView {
//      image := Image("/images/1024.jpg")
//      position.left := 0.vx
//      position.top := 0.vy
//      size.width := 1024.vw
//      size.height := 768.vh
//    }
//    container.children += new Label {
//      text := "1024x768"
//      color := Color.White
//      font.family := "sans-serif"
//      font.size := 66.5.vf
//      position.left := 44.vx
//      position.middle := ui.middle
//    }
//  }
//}
