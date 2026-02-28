package youi

import org.scalajs.dom._

object CSSManager {
  private lazy val style: html.Style = {
    val e = dom.create.style
    document.head.appendChild(e)
    e
  }
  private lazy val styleSheet: CSSStyleSheet = style.sheet.asInstanceOf[CSSStyleSheet]

  def insertRule(selector: String): CSSStyleDeclaration = {
    val index = styleSheet.insertRule(s"$selector {}")
    styleSheet.cssRules(index).asInstanceOf[CSSPageRule].style
  }
}