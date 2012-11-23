package org.hyperscala.web.site

import org.hyperscala.Page
import com.outr.webcommunicator.netty.handler.RequestHandler
import com.outr.webcommunicator.netty.NettyWebapp
import org.jboss.netty.channel.{ChannelFuture, ChannelFutureListener, MessageEvent, ChannelHandlerContext}
import org.jboss.netty.handler.codec.http.{HttpMethod, HttpRequest, CookieEncoder}
import com.google.common.net.HttpHeaders

import org.hyperscala.html._
import org.hyperscala.io.HTMLWriter
import org.jboss.netty.buffer.{ChannelBuffers, ChannelBuffer}
import org.hyperscala.web.module.{Interface, Module}
import org.powerscala.hierarchy.{Parent, Element, ContainerView}
import org.hyperscala.web.session.MapSession
import org.hyperscala.css.StyleSheet
import org.powerscala.property.PropertyParent
import org.powerscala.reflect.CaseValue
import org.powerscala.concurrent.Time._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Webpage extends Page with RequestHandler with Parent with PropertyParent with Temporal {
  Page.instance.set(this)

  val name = () => getClass.getSimpleName

  def defaultTitle = CaseValue.generateLabel(getClass.getSimpleName.replaceAll("\\$", ""))

  protected[web] var webContext: WebContext = _

  val store = new MapSession(Website()) {
    override def timeout = Double.MaxValue
  }

  def website = WebContext().website
  def headers = WebContext().headers
  def url = WebContext().url
  def cookies = WebContext().cookies

  /**
   * The amount of time in seconds this webpage will continue to be cached in memory without any communication.
   *
   * Defaults to 2 minutes.
   */
  def timeout = 2.minutes

  val doctype = "<!DOCTYPE html>\r\n"
  val html = new tag.HTML
  val head = new tag.Head {
    val title = new tag.Title(content = defaultTitle)
    val generator = new tag.Meta(name = "generator", content = "Hyperscala")
    val charset = new tag.Meta(charset = "UTF-8")

    contents.addAll(title, generator, charset)
  }
  val body = new tag.Body

  val contents = List(html)

  html.contents.addAll(head, body)
  Element.assignParent(html, this)

  def title = head.title.content

  val view = new ContainerView[HTMLTag](html)

  private var interfaces = Map.empty[String, Module]
  private var modules = Map.empty[String, Module]
  private var initializedModules = Set.empty[String]
  private var pageRendered = false
  intercept.beforeRender {
    case html: tag.HTML => synchronized {
      pageRendered = true
      if (modules.size != initializedModules.size) {
        modules.values.foreach {
          case module => {
            if (!initializedModules.contains(module.name)) {    // Module not initialized yet
              module.load(this)
              module.interfaces.foreach {
                case interface => {
                  println("Removing interface: %s".format(interface.name))
                  interfaces -= interface.name
                }
              }
              initializedModules += module.name
            }
          }
        }
      }
      if (interfaces.size > 0) {
        interfaces.foreach {          // Check for defaults
          case (interface, default) => if (default != null) {
            require(default)
            interfaces -= interface
          }
        }
        if (interfaces.size > 0) {    // Still unimplemented interfaces
          throw new RuntimeException("Unimplemented interface(s) found: %s".format(interfaces.keys.mkString(", ")))
        }
      }
    }
  }
  def require(module: Module) = synchronized {
    modules.get(module.name) match {
      case Some(current) => current.version.compare(module.version) match {
        case -1 => // Nothing changes, the current is the newer version
        case 0 => // Nothing changes, they are both the same
        case 1 => {
          if (initializedModules.contains(module.name)) {
            throw new RuntimeException("Module %s already initialized on page with earlier version (%s)".format(module.name, module.version))
          }
          modules += module.name -> module
          if (pageRendered) {   // Page has already rendered, so load immediately
            module.load(this)
            initializedModules += module.name
          }
        }
      }
      case None => {
        modules += module.name -> module
        if (pageRendered) {   // Page has already rendered, so load immediately
          module.load(this)
          initializedModules += module.name
        }
      }
    }
  }
  def require(interface: Interface, default: Module = null): Unit = synchronized {
    if (pageRendered) {
      if (modules.values.find(m => m.interfaces.find(i => i.name == interface.name).nonEmpty).isEmpty) {
        // Interface not already loaded
        if (default == null) {
          throw new RuntimeException("Unimplemented interface found: %s".format(interface.name))
        } else {
          require(default)
        }
      }
    } else interfaces.get(interface.name) match {
      case Some(module) if (module != null && default != null && module.version.compare(default.version) == 1) => // Don't replace with older module
      case Some(module) if (default == null) => // Don't replace with a null default
      case _ => interfaces += interface.name -> default
    }
  }

  /**
   * Returns all the HTMLTags that currently reference the supplied StyleSheet in the entire Webpage hierarchy.
   */
  def tagsByStyleSheet(ss: StyleSheet) = view.collect {
    case tag if (tag.style.loaded && tag.style() == ss) => tag
  }

  def apply(webapp: NettyWebapp, context: ChannelHandlerContext, event: MessageEvent) = {
    WebContext(webContext, checkIn = true) {
      val request = event.getMessage.asInstanceOf[HttpRequest]
      if (request.getMethod == HttpMethod.POST) {
        processPost(request.getContent)
      }
      val response = RequestHandler.createResponse(contentType = "text/html", sendExpiration = false)

      // Add modified or created cookies
      val encoder = new CookieEncoder(true)
      webContext.cookies.modified.foreach {
        case cookie => encoder.addCookie(cookie.toNettyCookie)
      }
      response.setHeader(HttpHeaders.SET_COOKIE, encoder.encode())

      val channel = context.getChannel
      // Write the HttpResponse
      channel.write(response)
      // Set up the writer
      var lastWriteFuture: ChannelFuture = null
      val writer = (s: String) => lastWriteFuture = channel.write(ChannelBuffers.wrappedBuffer(s.getBytes()))
      // Write the doctype
      writer(doctype)
      // Stream the page back
      val htmlWriter = HTMLWriter(writer)
      html.write(htmlWriter)
      lastWriteFuture.addListener(ChannelFutureListener.CLOSE)
    }
  }

  protected def processPost(content: ChannelBuffer) = {}

  override def errorThrown(t: Throwable) = if (WebContext.inContext) {
    website.errorThrown(this, t)
  } else {
    error("Error occurred on page: %s".format(this), t)
  }

  override protected[web] def checkIn() {
    super.checkIn()

    webContext.session.checkIn()    // Always update the session when the page gets a check-in
  }

  override def dispose() {
    super.dispose()

    Website().session.removeByValue(this)
    bus.clear()
  }
}

object Webpage {
  def apply() = WebContext().webpage
}