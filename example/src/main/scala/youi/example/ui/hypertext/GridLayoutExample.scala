//package youi.example.ui.hypertext
//
//import youi.Color
//import youi.hypertext.border.BorderStyle
//import youi.hypertext.style.Overflow
//import youi.hypertext.{Button, Component, Container}
//import youi.layout.GridLayout
//
//import scala.concurrent.Future
//
//object GridLayoutExample extends HTMLScreen {
//  override def name: String = "HTML GridLayout Example"
//
//  override protected def load(): Future[Unit] = super.load().map { _ =>
//    val box1: Component = createBox("box1", Color.Red)
//    val box2: Component = createBox("box2", Color.Green)
//    val box3: Component = createBox("box3", Color.Blue)
//    val box4: Component = createBox("box4", Color.Magenta)
//    val box5: Component = createBox("box5", Color.Cyan)
//    val box6: Component = createBox("box6", Color.SandyBrown)
//    val box7: Component = createBox("box7", Color.Orange)
//    val box8: Component = createBox("box8", Color.DarkSlateGray)
//
//    val layoutContainer = new Container {
//      id := "main"
//
//      layoutManager := new GridLayout {
//        columns := 3
//        config.default.margin.top := Some(5.0)
//        config.default.margin.left := Some(5.0)
//      }
//      backgroundColor := Color.Black
//      size.height := 500.0
//      position.left := 100.0
//      position.top := 100.0
//      overflow.x := Overflow.Hidden
//      overflow.y := Overflow.Auto
//
//      children += box1
//      children += box2
//      children += box3
//      children += box4
//      children += box5
//      children += box6
//      children += box7
//      children += box8
//    }
//    container.children += layoutContainer
//
//    container.children += new Button {
//      text := "Jump to Middle"
//
//      event.click.attach { _ =>
//        layoutContainer.scrollbar.vertical.percentage := 0.5
//      }
//    }
//  }
//
//  private def createBox(name: String, c: Color): Component = new Container {
//    id := name
//    size.width := 350.0
//    size.height := 250.0
//    backgroundColor := c
//    border.color := Some(Color.DeepPink)
//    border.size := Some(1.0)
//    border.style := Some(BorderStyle.Solid)
//    border.radius := 5.0
//  }
//
//  override def path: String = "/examples/html/gridlayout.html"
//}
