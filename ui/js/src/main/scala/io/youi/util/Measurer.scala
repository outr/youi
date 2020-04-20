package io.youi.util

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

  def measure(component: Component, size: Size): Size = {
    val width = if (component.size.width().`type` == DimType.Auto) "auto" else s"${component.size.width().value}px"
    val height = if (component.size.height().`type` == DimType.Auto) "auto" else s"${component.size.height().value}px"
    measure(HTMLComponent.element(component), width, height, size)
  }

  // TODO: create element pool and reuse
  def measure(element: html.Element,
              width: String,
              height: String,
              size: Size): Size = measureHTML(element.outerHTML, width, height, size)

  def measureHTML(htmlString: String, width: String, height: String, size: Size): Size = {
    container.innerHTML = htmlString
    val e = container.firstElementChild.asInstanceOf[html.Element]
    e.style.width = width
    if (width != "auto") e.style.display = "block"
    e.style.height = height
    e.style.position = "static"

    val bounding = e.getBoundingClientRect()
    container.innerHTML = ""
    size.set(bounding.width, bounding.height)
  }
}