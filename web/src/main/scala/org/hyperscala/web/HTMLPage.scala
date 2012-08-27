package org.hyperscala.web

import event.FormSubmit
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}
import org.hyperscala.html._
import attributes.Method
import org.powerscala.property.{PropertyParent, Property}
import org.powerscala.hierarchy.{ContainerView, Parent, Element}

import collection.JavaConversions._
import java.io.OutputStream
import org.powerscala.Updatable

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class HTMLPage extends Page with PropertyParent with Parent with Updatable {
  HTMLPage.instance.set(this)

  val doctype = "<!DOCTYPE html>\r\n".getBytes
  val name = Property[String]("name", null)
  val title = Property[String]("title", null)
  val website = Website()

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
  head.contents += new Meta(charset = "UTF-8")

  html.contents.addAll(head, body)

  title := getClass.getSimpleName     // We always have to have a title

  private var tab = 1

  private def servletRequest = website.servletRequest
  private def servletResponse = website.servletResponse

  /**
   * Ensures that the script referenced via url is loaded in the head.
   *
   * @param url the url of the script
   * @param regex optional regular expression match
   * @return true if the script was added
   */
  def ensureScript(url: String, regex: String = null) = {
    val hasScript = head.contents.find {
      case script: Script if ((regex != null && script.src().matches(regex)) || script.src() == url) => true
      case _ => false
    }.nonEmpty
    if (!hasScript) {
      head.contents += new Script(src = url)
      true
    } else {
      false
    }
  }

  def service(method: Method, request: HttpServletRequest, response: HttpServletResponse) = {
    HTMLPage.instance.set(this)
    try {
      response.setContentType("text/html")
      var ignoreResponse = false
      var form: Form = null
      if (method == Method.Post) {
        request.getParameterMap.foreach {
          case (key, values) => {
//            println("Key: %s, Value: %s".format(key, values.asInstanceOf[Array[String]].head))
            if (key.toString.endsWith("SendResponse") && values.asInstanceOf[Array[String]].head == "false") {
              ignoreResponse = true
            }
            byName[HTMLTag](key.asInstanceOf[String]) match {
              case Some(tag) if (renderable(tag)) => {      // Only apply to tags that are rendered to the page
                if (form == null) {
                  tag.hierarchy.backward[Form]() match {
                    case null => println("WARNING: Unable to find form for %s".format(key)) // Odd, but not impossible
                    case f => form = f
                  }
                }
                val v = values.asInstanceOf[Array[String]]
//                println("Updating %s for %s with %s".format(tag, key, v.mkString(", ")))
                updateValue(method, tag, v)
              }
              case _ => println("Unable to find %s = %s".format(key, values.asInstanceOf[Array[String]].head))
            }
          }
        }
        if (form != null) {
          form.fire(FormSubmit(method))
        }
      }
      refresh()
      if (!ignoreResponse) {
        cached.poll[String](redirectId) match {
          case Some(redirect) => response.sendRedirect(redirect)
          case None => {
            sendResponse(method, request, response)
          }
        }
      }
    } catch {
      case t: Throwable => handleException(t, method, request, response)
    } finally {
      HTMLPage.instance.set(null)
    }
  }

  protected def renderable(tag: HTMLTag): Boolean = if (tag.render) {
    tag.parent match {
      case parent: HTMLTag => renderable(parent)
      case _ => true
    }
  } else {
    false
  }

  protected def updateValue(method: Method, tag: HTMLTag, values: Array[String]) = {
    val value = values.head
    tag match {
      case input: Input => input.value := value
      case textArea: TextArea => textArea.contents.replaceWith(value)
      case button: Button => button.fire(FormSubmit(method))
      case select: Select => select.contents.collectFirst {
        case option: Option if (option.value() == value) => option.selected := true
      }
      case _ => throw new RuntimeException("Unsupported tag: %s".format(tag))
      // TODO: add support for other inputs
    }
  }

  protected def sendResponse(method: Method, request: HttpServletRequest, response: HttpServletResponse) = {
    val output = response.getOutputStream
    write(output)
  }

  def write(output: OutputStream) = {
    try {
      output.write(doctype)
      html.stream(output)
    } finally {
      output.flush()
      output.close()
    }
  }

  final def errorOccurred(t: Throwable) = {
    website.errorOccurred(t)
  }

  protected def handleException(t: Throwable, method: Method, request: HttpServletRequest, response: HttpServletResponse) = {
    errorOccurred(t)
    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, t.getMessage)
  }

  /**
   * Redirects the page for the next rendering. Additional rendering of the page will no longer redirect.
   */
  def sendRedirect(url: String) = cached.set(redirectId) {
    url
  }

  def hasRedirect = cached.get(redirectId) != None

  private def redirectId = "%s.redirect".format(getClass.getName)

  /**
   * Called every time the page is loaded into the browser.
   */
  def refresh(): Unit = {}

  def allByName[T <: HTMLTag](name: String)(implicit manifest: Manifest[T]) = view.collect {
    case tag if (tag.name() == name && manifest.erasure.isAssignableFrom(tag.getClass)) => tag.asInstanceOf[T]
  }
  def byName[T <: HTMLTag](name: String)(implicit manifest: Manifest[T]) = allByName[T](name).headOption
  def byId[T <: HTMLTag](id: String)(implicit manifest: Manifest[T]) = {
    view.find(tag => tag.id() == id && manifest.erasure.isAssignableFrom(tag.getClass)).asInstanceOf[scala.Option[T]]
  }

  def nextTab = {
    val next = tab
    tab += 1
    next
  }

  object parameters {
    def apply(key: String) = servletRequest.getParameter(key)

    def get(key: String) = apply(key) match {
      case null => None
      case value => Some(value)
    }

    def iterator = servletRequest.getParameterMap.map(m => m._1.toString -> m._2.asInstanceOf[Array[String]](0)).iterator
  }

  def cookies = website.cookies
  def cached = website.cached
}

object HTMLPage {
  private val instance = new ThreadLocal[HTMLPage]

  def apply() = instance.get()

  def apply(tag: HTMLTag) = tag.hierarchy.first.asInstanceOf[HTMLPage]
}