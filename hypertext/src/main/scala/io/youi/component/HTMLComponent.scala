//package io.youi.component
//
//import io.youi._
//import io.youi.theme.HTMLComponentTheme
//import reactify._
//
//import org.scalajs.dom._
//
//class HTMLComponent[C <: hypertext.Component](val component: C) extends Component {
//  override lazy val theme: Var[HTMLComponentTheme] = Var(HTMLComponent)
//
//  init()
//
//  override protected def init(): Unit = {
//    super.init()
//
//    size.measured.width := component.size.actual.width
//    size.measured.height := component.size.actual.height
//    component.visible := actual.visibility
//    if (Option(component.element.parentElement).isEmpty) {
//      document.body.appendChild(component.element)
//      delta.attach(component.update)
//    }
//    component.position.x := actual.x
//    component.position.y := actual.y
//    // TODO: support rotation
//  }
//}
//
//object HTMLComponent extends HTMLComponentTheme