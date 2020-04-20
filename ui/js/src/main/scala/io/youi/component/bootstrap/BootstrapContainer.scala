package io.youi.component.bootstrap

class BootstrapContainer(element: html.Element) extends Container(element) with BootstrapComponent[html.Element] {
  def this() = {
    this(dom.create[html.Div]("div"))
  }

  override protected def defaultParentTheme: Theme = Container
  override def componentType: String = "bootstrap.Container"

  override protected def init(): Unit = {
    super.init()

    classList += "container"
  }
}
