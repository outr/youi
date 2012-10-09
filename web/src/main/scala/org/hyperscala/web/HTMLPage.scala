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
import resource.PageResource
import tag._
import scala.Some
import org.hyperscala
import hyperscala.io.HTMLWriter
import hyperscala.{Page, Markup}
import org.powerscala.concurrent.WorkQueue
import org.powerscala.event.Event
import java.util.concurrent.atomic.AtomicBoolean

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class HTMLPage extends PageResource with PropertyParent with Parent with Updatable with WorkQueue with Page {
  override def init(markup: Markup) {
    Website().init(markup)

    super.init(markup)
  }

  override def before(markup: Markup) {
    Website().before(markup)

    super.before(markup)
  }

  override def after(markup: Markup) {
    Website().after(markup)

    super.after(markup)
  }

  private val _initialized = new AtomicBoolean(false)

  def initialized = _initialized.get()

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
  def contents = body.contents

  val view = new ContainerView[HTMLTag](html)

  Element.assignParent(html, this)

  head.contents += new Meta(name = "generator", content = "Hyperscala")
  head.contents += new Meta(charset = "UTF-8")

  html.contents.addAll(head, body)

  title := getClass.getSimpleName.replaceAll("\\$", "")     // We always have to have a title

  private var tab = 1

  def servletRequest = website.servletRequest
  def servletResponse = website.servletResponse

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

  def apply(method: Method, request: HttpServletRequest, response: HttpServletResponse) = {
    Page.instance.set(this)     // TODO: extract this out into an interceptor
    try {
      doAllWork()   // Handle any enqueued work
      response.setContentType("text/html")
      var ignoreResponse = false
      var form: Form = null
      if (method == Method.Post) {    // TODO: extract all POST support out
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
      case t: Throwable => {
        errorOccurred(t)
        handleError(t, method, request, response)
      }
    } finally {
      Page.instance.set(null)
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
      case textArea: TextArea => textArea.content := value
      case button: Button => button.fire(FormSubmit(method))
      case select: Select => select.contents.collectFirst {
        case option: hyperscala.html.tag.Option if (option.value() == value) => option.selected := true
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
      // Initialize
      if (_initialized.compareAndSet(false, true)) {
        initialize()
      }

      output.write(doctype)
      val writer = HTMLWriter(output)
      html.write(writer)
    } finally {
      output.flush()
      output.close()
    }
  }

  protected def initialize() = {}

  /**
   * Responsible for logging any errors that occur on this page.
   *
   * Defaults to calling website.errorOccurred
   */
  def errorOccurred(t: Throwable) = {
    website.errorOccurred(t)
  }

  /**
   * Handles the errors that occur for this page. This should send any response back to the client that is necessary.
   *
   * Defaults to calling website.handleError
   */
  def handleError(t: Throwable, method: Method, request: HttpServletRequest, response: HttpServletResponse) = {
    website.handleError(t, method, request, response)
  }

  /**
   * Redirects the page for the next rendering. Additional rendering of the page will no longer redirect.
   */
  def sendRedirect(url: String): Unit = cached.set(redirectId) {
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
    val results = view.collect {
      case tag if (tag.id() == id && manifest.erasure.isAssignableFrom(tag.getClass)) => tag.asInstanceOf[T]
    }
    if (results.size > 1) {
      throw new RuntimeException("%s matches for byId(\"%s\")".format(results.size, id))
    }
    results.headOption
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

    def getOrElse(key: String, default: String) = apply(key) match {
      case null => default
      case value => value
    }

    def iterator = servletRequest.getParameterMap.map(m => m._1.toString -> m._2.asInstanceOf[Array[String]](0)).iterator
  }

  def cookies = website.cookies
  def cached = website.cached

  def fireLater(event: Event) = {
    WorkQueue.enqueue(this, () => fire(event))
  }

  def invokeLater(f: => Unit) = {
    WorkQueue.enqueue(this, () => f)
  }

  override def update(delta: Double) {
    val previous = HTMLPage()
    Page.instance.set(this)
    try {
      super.update(delta)

      doAllWork()
    } finally {
      Page.instance.set(previous)
    }
  }
}

object HTMLPage {
  def apply() = Page().asInstanceOf[HTMLPage]

  def apply(tag: HTMLTag) = tag.hierarchy.first.asInstanceOf[HTMLPage]
}