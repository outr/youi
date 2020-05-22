package io.youi.font

class FontAwesomeView(protected val element: html.Element,
                      val existing: Boolean = false) extends HTMLComponent[html.Element] with FontAwesomeViewTheme {
  def this() = {
    this(create[html.Element]("i"))
  }

  override protected def defaultParentTheme: Theme = FontAwesomeView

  override def componentType: String = "FontAwesomeView"
}

object FontAwesomeView extends FontAwesomeViewTheme {
  override protected def defaultParentTheme: Theme = HTMLComponent

  def existing(id: String, in: html.Element = document.body): FontAwesomeViewTheme = {
    new FontAwesomeView(in.byId[html.Element](id), existing = true)
  }
}