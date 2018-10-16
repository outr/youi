package io.youi.component.bootstrap

import io.youi.component.Container
import io.youi.dom
import io.youi.theme.Theme
import org.scalajs.dom.html

class CardBody(element: html.Element) extends Container(element) with BootstrapComponent[html.Element] {
  def this() = {
    this(dom.create[html.Div]("div"))
  }

  override protected def defaultParentTheme: Theme = Container
  override def componentType: String = "bootstrap.CardBody"

  override protected def init(): Unit = {
    super.init()

    element.classList.add("card-body")
  }
}