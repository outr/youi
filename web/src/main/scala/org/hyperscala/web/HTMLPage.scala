package org.hyperscala.web

import javax.servlet.http.{Cookie, HttpServletResponse, HttpServletRequest}
import org.hyperscala.html._
import org.powerscala.property.{PropertyParent, Property}
import org.powerscala.hierarchy.{ContainerView, Parent, Element}

import collection.JavaConversions._
import org.powerscala.event.ActionEvent

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
  head.contents += new Meta(charSet = "UTF-8")

  html.contents.addAll(head, body)

  title := getClass.getSimpleName     // We always have to have a title

  private var tab = 1

  private def servletRequest = website.servletRequest
  private def servletResponse = website.servletResponse

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
            byName[HTMLTag](key.asInstanceOf[String]) match {
              case Some(tag) => {
                if (form == null) {
                  tag.hierarchy.backward[Form]() match {
                    case null => // Odd, but not impossible
                    case f => form = f
                  }
                }
                updateValue(tag, values.asInstanceOf[Array[String]])
              }
              case None => //println("Unable to find %s = %s".format(key, values.asInstanceOf[Array[String]].head))
            }
          }
        }
        if (request.getParameter("sendResponse") == "false") {
          ignoreResponse = true
        }
        if (form != null) {
          form.fire(ActionEvent("submit"))
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
    val value = values.head
    tag match {
      case input: Input => input.value := value
      case textArea: TextArea => textArea.contents.replaceWith(value)
      case button: Button => button.fire(ActionEvent("submit"))
      case select: Select => select.contents.collectFirst {
        case option: Option if (option.value() == value) => option.selected := true
      }
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

  def allByName[T <: HTMLTag](name: String) = view.collect {
    case tag if (tag.name() == name) => tag
  }
  def byName[T <: HTMLTag](name: String) = allByName[T](name).headOption
  def byId[T <: HTMLTag](id: String) = view.find(tag => tag.id() == id).asInstanceOf[scala.Option[T]]

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

  object cookies extends Seq[Cookie] {
    def length = servletRequest.getCookies.length

    def apply(idx: Int) = servletRequest.getCookies()(idx)

    def iterator = servletRequest.getCookies.iterator

    def add(name: String,
                  value: String,
                  comment: String = null,
                  domain: String = null,
                  maxAge: Int = -1,
                  path: String = null,
                  secure: Boolean = false,
                  version: Int = 0) = {
      val cookie = new Cookie(name, value)
      if (comment != null) cookie.setComment(comment)
      if (domain != null) cookie.setDomain(domain)
      cookie.setMaxAge(maxAge)
      if (path != null) cookie.setPath(path)
      cookie.setSecure(secure)
      cookie.setVersion(version)
      servletResponse.addCookie(cookie)
      cookie
    }

    def delete(name: String) = add(name, "", maxAge = 0)

    def apply(name: String) = cookies.find(c => c.getName == name)
  }

  object cached {
    /**
     * Caches the content supplied in the specified Scope based on the key provided. The content will be reused until
     * the "clear" method is invoked with the key.
     */
    def apply[T](key: String, scope: Scope = Scope.Session)(f: => T) = {
      val storage = scope match {
        case Scope.Application => website
        case Scope.Session => website.session
        case Scope.Request => null
      }
      val stored = if (storage != null) {
        storage.get[T](key)
      } else {
        None
      }
      val content = stored match {
        case Some(value) => value
        case None => f
      }
      if (storage != null) {
        storage(key) = content
      }
      content
    }

    def get[T](key: String, scope: Scope = Scope.Session) = {
      val storage = scope match {
        case Scope.Application => website
        case Scope.Session => website.session
        case Scope.Request => null
      }
      if (storage != null) {
        storage.get[T](key)
      } else {
        None
      }
    }

    /**
     * Updates the value cached value with the supplied function and then returns it.
     */
    def set[T](key: String, scope: Scope = Scope.Session)(f: => T) = {
      val storage = scope match {
        case Scope.Application => website
        case Scope.Session => website.session
        case Scope.Request => null
      }
      val content = f
      if (storage != null) {
        storage(key) = content
      }
      content
    }

    /**
     * Clears a cached item from the scope to be recreated upon next load.
     */
    def clear(key: String, scope: Scope = null) = {
      val storage = scope match {
        case Scope.Application => website
        case Scope.Session => website.session
        case _ => null
      }
      if (storage != null) {
        storage.remove(key)
      } else { // No scope defined, so remove from all scopes
        website.remove(key)
        website.session.remove(key)
      }
    }
  }
}

object HTMLPage {
  private val instance = new ThreadLocal[HTMLPage]

  def apply() = instance.get()

  def apply(tag: HTMLTag) = tag.hierarchy.first.asInstanceOf[HTMLPage]
}