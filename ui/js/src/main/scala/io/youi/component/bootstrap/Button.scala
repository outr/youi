package io.youi.component.bootstrap

class Button(element: html.Button,
             existing: Boolean = false) extends Container(element, existing) with BootstrapComponent[html.Element] with ButtonTheme {
  def this() = {
    this(create[html.Button]("button"))
  }

  override protected def defaultParentTheme: Theme = Button

  override def componentType: String = "bootstrap.Button"

  element.classList.add("btn")
  element.setAttribute("type", "button")

  lazy val value: StyleProp[String] = style[String]("value", "", StyleConnect.content[String], updatesTransform = true)
}

object Button extends ButtonTheme {
  override protected def defaultParentTheme: Theme = HTMLComponent

  def existing(id: String, in: html.Element = document.body): Button = {
    new Button(in.byId[html.Button](id), existing = true)
  }
}