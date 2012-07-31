package org.hyperscala.web

import javax.servlet.http.{HttpServletResponse, HttpServletRequest}
import org.hyperscala.html._
import org.powerscala.property.{PropertyParent, Property}
import org.powerscala.hierarchy.{ContainerView, Parent, Element}

import collection.JavaConversions._

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class HTMLPage extends Page with PropertyParent with Parent {
  val doctype = "<!DOCTYPE html>\r\n".getBytes
  val name = Property[String]("name", null)
  val title = Property[String]("title", null)

  val html = new HTML
  val head = new Head {
    contents += new Title {
      content.bind(HTMLPage.this.title)
    }
  }
  val body = new Body
  val contents = body.contents

  val view = new ContainerView[HTMLTag](html)

  Element.assignParent(html, this)

  head.contents += new Meta(name = "generator", content = "Hyperscala")
  head.contents += new Meta(charSet = "UTF-8")

  html.contents.addAll(head, body)

  def service(method: Method, request: HttpServletRequest, response: HttpServletResponse) = {
    response.setContentType("text/html")
    if (method == Method.Post) {
      request.getParameterMap.foreach {
        case (key, values) => byName[HTMLTag](key) match {
          case Some(tag) => tag match {
            case input: Input => input.value := values.head
            case textArea: TextArea => textArea.contents.replaceWith(values.head)
            // TODO: add support for other inputs
          }
          case None => //println("Unable to find %s".format(key))
        }
      }
    }
    refresh()
    val output = response.getOutputStream
    try {
      output.write(doctype)
      html.stream(output)
    } finally {
      output.flush()
      output.close()
    }
  }

  /**
   * Called every time the page is loaded into the browser.
   */
  def refresh(): Unit = {}

  def byName[T <: HTMLTag](name: String) = view.find(tag => tag.name() == name).asInstanceOf[scala.Option[T]]
  def byId[T <: HTMLTag](id: String) = view.find(tag => tag.id() == id).asInstanceOf[scala.Option[T]]
}
