package io.youi.component.editor

import io.youi.component.{Component, Container}
import io.youi.theme.mixins.HTMLFontTheme
import org.scalajs.dom.Event
import reactify.Var

class ContentEditor extends Container with HTMLFontTheme {
  element.contentEditable = "true"

  val value: Var[String] = Var(element.innerHTML)

  element.addEventListener("input", (_: Event) => {
    value := element.innerHTML
  })
  value.attach { html =>
    element.innerHTML = html
  }
}
