package io.youi.component

class HTMLTextArea(protected val element: html.TextArea,
                    val existing: Boolean = false) extends HTMLComponent[html.TextArea] with HTMLTextInputTheme {
  def this() = {
    this(create[html.TextArea]("textarea"))
  }

  override protected def init(): Unit = {
    super.init()

    element.addEventListener("input", (_: Event) => {
      valueChanging = true
      try {
        value @= element.value
      } finally {
        valueChanging = false
      }
    })
  }

  override protected def defaultParentTheme: Theme = HTMLTextArea

  override def componentType: String = "HTMLTextArea"
}

object HTMLTextArea extends HTMLTextAreaTheme {
  override protected def defaultParentTheme: Theme = HTMLComponent

  def existing(id: String, in: html.Element = document.body): HTMLTextArea = {
    new HTMLTextArea(in.byId[html.TextArea](id), existing = true)
  }
}
