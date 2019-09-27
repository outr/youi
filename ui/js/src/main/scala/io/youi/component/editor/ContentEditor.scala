package io.youi.component.editor

import io.youi.component.Container
import io.youi.theme.mixins.HTMLFontTheme
import org.scalajs.dom.Event
import reactify.Var

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
