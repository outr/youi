package org.hyperscala.web

import javax.servlet.http.{HttpServletResponse, HttpServletRequest}
import org.hyperscala.html._
import org.powerscala.property.{PropertyParent, Property}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class HTMLPage extends Page with PropertyParent {
  val doctype = "<!DOCTYPE html>\r\n".getBytes
  val name = Property[String]("name", null)
  val title = Property[String]("title", null)

  val parent = null

  val html = new HTML
  val head = new Head {
    contents += new Title {
      content.bind(HTMLPage.this.title)
    }
  }
  val body = new Body

  head.contents += new Meta(name = "generator", content = "Hyperscala")
  head.contents += new Meta(charSet = "UTF-8")

  html.contents.addAll(head, body)

  def service(method: Method, request: HttpServletRequest, response: HttpServletResponse) = {
    response.setContentType("text/html")
    val output = response.getOutputStream
    try {
      output.write(doctype)
      html.stream(output)
    } finally {
      output.flush()
      output.close()
    }
  }
}
