package io.youi.component.editor

import io.youi.component.{Component, Container}
import io.youi.theme.mixins.HTMLFontTheme
import reactify.Var

class ContentEditor extends Container with HTMLFontTheme {
  element.contentEditable = "true"

  val value: Var[String] = Component.prop[String](this, element.innerHTML, (s: String) => element.innerHTML = s)
}
