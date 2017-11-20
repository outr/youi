package io.youi.component.extras

import org.scalajs.dom._
import org.scalajs.dom.raw.CSSStyleDeclaration

class HTMLExtras(element: html.Element) {
  private def style: CSSStyleDeclaration = window.getComputedStyle(element)


}
