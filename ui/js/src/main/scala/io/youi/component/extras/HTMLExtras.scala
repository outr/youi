package io.youi.component.extras

class HTMLExtras[E <: html.Element](val element: E) {
  private def style: CSSStyleDeclaration = window.getComputedStyle(element)
}
