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
  HTMLPage.instance.set(this)

  private var redirectPage: String = null
  protected[web] var disposed = false

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

  title := getClass.getSimpleName     // We always have to have a title

  def service(method: Method, request: HttpServletRequest, response: HttpServletResponse) = {
    HTMLPage.instance.set(this)
    try {
      response.setContentType("text/html")
      var ignoreResponse = false
      if (method == Method.Post) {
        request.getParameterMap.foreach {
          case (key, values) => byName[HTMLTag](key.asInstanceOf[String]) match {
            case Some(tag) => updateValue(tag, values.asInstanceOf[Array[String]])
            case None => //println("Unable to find %s".format(key))
          }
        }
        if (request.getParameter("sendResponse") == "false") {
          ignoreResponse = true
        }
      }
      refresh()
      if (!ignoreResponse) {
        if (redirectPage != null) {     // Send redirect
          response.sendRedirect(redirectPage)
          redirectPage = null
        } else {
          sendResponse(method, request, response)
        }
      }
    } catch {
      case t: Throwable => handleException(t, method, request, response)
    } finally {
      HTMLPage.instance.set(null)
    }
  }

  protected def updateValue(tag: HTMLTag, values: Array[String]) = {
    tag match {
      case input: Input => input.value := values.head
      case textArea: TextArea => textArea.contents.replaceWith(values.head)
      case _ => throw new RuntimeException("Unsupported tag: %s".format(tag))
      // TODO: add support for other inputs
    }
  }

  protected def sendResponse(method: Method, request: HttpServletRequest, response: HttpServletResponse) = {
    val output = response.getOutputStream
    try {
      output.write(doctype)
      html.stream(output)
    } finally {
      output.flush()
      output.close()
    }
  }

  protected def handleException(t: Throwable, method: Method, request: HttpServletRequest, response: HttpServletResponse) = {
    t.printStackTrace()
    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, t.getMessage)
  }

  /**
   * Redirects the page for the next rendering. Additional rendering of the page will no longer redirect.
   */
  def sendRedirect(url: String) = redirectPage = url

  /**
   * Called every time the page is loaded into the browser.
   */
  def refresh(): Unit = {}

  def dispose() = disposed = true

  def byName[T <: HTMLTag](name: String) = view.find(tag => tag.name() == name).asInstanceOf[scala.Option[T]]
  def byId[T <: HTMLTag](id: String) = view.find(tag => tag.id() == id).asInstanceOf[scala.Option[T]]
}

object HTMLPage {
  private val instance = new ThreadLocal[HTMLPage]

  def apply() = instance.get()

  def apply(tag: HTMLTag) = tag.hierarchy.first.asInstanceOf[HTMLPage]
}