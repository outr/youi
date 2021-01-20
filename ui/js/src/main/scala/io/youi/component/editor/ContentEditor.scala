package io.youi.component.editor

class ContentEditor extends Container with HTMLFontTheme {
  element.contentEditable = "true"

  val value: Var[String] = Var(element.innerHTML)

  private var changing = false
  element.addEventListener("input", (_: Event) => {
    changing = true
    try {
      value @= element.innerHTML
    } finally {
      changing = false
    }
  })
  value.attach { html =>
    if (!changing) {
      element.innerHTML = html
    }
    invalidateTransform()
  }
}