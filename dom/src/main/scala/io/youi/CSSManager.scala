package io.youi

import org.scalajs.dom._
import org.scalajs.dom.raw.{CSSPageRule, CSSStyleDeclaration, CSSStyleSheet}

object CSSManager {
  private lazy val style: html.Style = {
    val e = dom.create.style
    document.head.appendChild(e)
    e
  }
  private lazy val styleSheet: CSSStyleSheet = style.sheet.asInstanceOf[CSSStyleSheet]

  def insertRule(selector: String): CSSStyleDeclaration = {
    styleSheet.insertRule(s"$selector {}")
    val index = styleSheet.cssRules.length - 1
    styleSheet.cssRules(index).asInstanceOf[CSSPageRule].style
  }
}