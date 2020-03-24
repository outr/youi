package io.youi.component.util

import io.youi.dom
import org.scalajs.dom.{document, html}
import reactify.Var

object Measurer {
  private lazy val container = {
    val span = dom.create[html.Span]("span")
    span.style.position = "absolute"
    span.style.visibility = "hidden"
    span.style.width = "auto"
    span.style.height = "auto"
    document.body.appendChild(span)
    span
  }

  def measureHTML(htmlString: String, width: String, height: String, w: Var[Double], h: Var[Double]): Unit = {
    container.innerHTML = htmlString
    val e = container.firstElementChild.asInstanceOf[html.Element]
    e.style.width = width
    if (width != "auto") e.style.display = "block"
    e.style.height = height
    e.style.position = "static"

    val bounding = e.getBoundingClientRect()
    container.innerHTML = ""
    w @= bounding.width
    h @= bounding.height
  }
}
