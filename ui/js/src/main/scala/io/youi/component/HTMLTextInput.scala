package io.youi.component

class HTMLTextInput(protected val element: html.Input,
                    val existing: Boolean = false) extends HTMLComponent[html.Input] with HTMLTextInputTheme {
  def this() = {
    this(create[html.Input]("input"))
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

  override protected def defaultParentTheme: Theme = HTMLTextInput

  override def componentType: String = "HTMLTextInput"
}

object HTMLTextInput extends HTMLTextInputTheme {
  override protected def defaultParentTheme: Theme = HTMLComponent

  def existing(id: String, in: html.Element = document.body): HTMLTextInput = {
    new HTMLTextInput(in.byId[html.Input](id), existing = true)
  }
}