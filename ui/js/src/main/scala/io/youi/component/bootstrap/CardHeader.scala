package io.youi.component.bootstrap

class CardHeader(element: html.Element) extends Container(element) with BootstrapComponent[html.Element] {
  def this() = {
    this(dom.create[html.Div]("div"))
  }

  override protected def defaultParentTheme: Theme = Container
  override def componentType: String = "bootstrap.CardHeader"

  override protected def init(): Unit = {
    super.init()

    element.classList.add("card-header")
  }
}