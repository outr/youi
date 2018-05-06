package io.youi.util

import io.youi.dom
import io.youi.spatial.Size
import org.scalajs.dom._

object Measurer {
  private lazy val container = {
    val span = dom.create[html.Span]("span")
    span.style.position = "absolute"
    span.style.visibility = "hidden"
    span.style.width = "auto"
    span.style.height = "auto"
    span.style.whiteSpace = "nowrap"
    document.body.appendChild(span)
    span
  }

  def measure(element: html.Element): Size = measure(element.outerHTML)

  def measure(htmlString: String): Size = {
    container.innerHTML = htmlString
    val e = container.firstElementChild.asInstanceOf[html.Element]
    e.style.width = "auto"
    e.style.height = "auto"
    e.style.whiteSpace = "noWrap"
    e.style.position = "static"
    e.style.visibility = "hidden"

    val bounding = e.getBoundingClientRect()
    Size(bounding.width, bounding.height)
  }
}