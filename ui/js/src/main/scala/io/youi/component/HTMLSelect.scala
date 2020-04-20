package io.youi.component

class HTMLSelect(protected val element: html.Select,
                 val existing: Boolean = false) extends HTMLComponent[html.Select] with HTMLSelectTheme {
  lazy val items: Var[Vector[String]] = Var(Vector.empty)

  def this() = {
    this(create[html.Select]("select"))
  }

  override protected def init(): Unit = {
    super.init()

    element.addEventListener("change", (_: Event) => {
      valueChanging = true
      try {
        value @= element.value
      } finally {
        valueChanging = false
      }
    })

    items.attachAndFire { v =>
      element.innerHTML = ""
      v.foreach { text =>
        val option = create[html.Option]("option")
        option.value = text
        option.innerHTML = text
        option.selected = value() == text
        element.appendChild(option)
      }
    }
  }

  override def componentType: String = "HTMLSelect"
}

object HTMLSelect extends HTMLSelectTheme {
  override protected def defaultParentTheme: Theme = HTMLComponent

  def existing(id: String, in: html.Element = document.body): HTMLSelect = {
    new HTMLSelect(in.byId[html.Select](id), existing = true)
  }
}