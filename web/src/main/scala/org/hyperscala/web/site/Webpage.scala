package org.hyperscala.web.site

import org.hyperscala.{Markup, Tag, Page}
import com.outr.webcommunicator.netty.handler.RequestHandler
import com.outr.webcommunicator.netty.{ChannelStringWriter, NettyWebapp}
import org.jboss.netty.channel.{ChannelFutureListener, MessageEvent, ChannelHandlerContext}
import org.jboss.netty.handler.codec.http.{HttpMethod, HttpRequest, CookieEncoder}
import com.google.common.net.HttpHeaders

import org.hyperscala.html._
import org.hyperscala.io.HTMLWriter
import org.jboss.netty.buffer.ChannelBuffer
import org.powerscala.hierarchy.{Parent, Element, ContainerView}
import org.hyperscala.web.session.MapSession
import org.hyperscala.css.StyleSheet
import org.powerscala.property.PropertyParent
import org.powerscala.reflect._
import org.powerscala.concurrent.Time._
import org.powerscala.concurrent.WorkQueue
import org.powerscala.event.Event
import java.io.IOException
import org.hyperscala.context.Contextual
import org.hyperscala.module.ModularPage
import org.hyperscala.svg.SVGTag
import java.util.concurrent.atomic.AtomicBoolean
import org.powerscala.Updatable

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Webpage extends Page with ModularPage with RequestHandler with Parent with PropertyParent with Temporal with WorkQueue with Contextual {
  WebContext.webpage := this

  private val _rendered = new AtomicBoolean(false)
  def rendered = _rendered.get()

  val name = () => getClass.getSimpleName

  def defaultTitle = CaseValue.generateLabel(getClass.getSimpleName.replaceAll("\\$", ""))

  val store = new MapSession {
    override def timeout = Double.MaxValue
  }

  def website = Website()
  def headers = WebContext.headers()
  def url = WebContext.url()
  def cookies = WebContext.cookies()
  def localAddress = WebContext.localAddress()
  def remoteAddress = WebContext.remoteAddress()

  /**
   * Invokes the supplied function on all matching tags immediately and invokes on all new tags created at init time.
   *
   * @param f the function to invoke
   * @param manifest defines the erasured generic type of the matching T
   * @tparam T the filtered tag type to apply to the function
   */
  def live[T <: HTMLTag](f: T => Unit)(implicit manifest: Manifest[T]) = {
    // First we walk through the hierarchical structure
    html.byTag[T](manifest).foreach {
      case t => f(t)
    }
    // Now we intercept init to determine when new items are created
    intercept.init {
      case m: Markup if (m.getClass.hasType(manifest.erasure)) => f(m.asInstanceOf[T])
    }
  }

  /**
   * The amount of time in seconds this webpage will continue to be cached in memory without any communication.
   *
   * Defaults to 2 minutes.
   */
  def timeout = 2.minutes

  val doctype = "<!DOCTYPE html>\r\n"
  val html = new tag.HTML
  val head = new tag.Head {
    title := defaultTitle
    val generator = new tag.Meta(name = "generator", content = "Hyperscala")
    val charset = new tag.Meta(charset = "UTF-8")

    contents.addAll(generator, charset)
  }
  val body = new tag.Body

  val contents = List(html)

  html.contents.addAll(head, body)
  Element.assignParent(html, this)

  def title = head.title

  val view = new ContainerView[Tag](html)
  val updatables = new ContainerView[Updatable](html)

  /**
   * Returns all the HTMLTags that currently reference the supplied StyleSheet in the entire Webpage hierarchy.
   */
  def tagsByStyleSheet(ss: StyleSheet) = view.collect {
    case tag: HTMLTag if (tag.style() == ss) => tag
  }

  def apply(webapp: NettyWebapp, context: ChannelHandlerContext, event: MessageEvent) = {
    val request = event.getMessage.asInstanceOf[HttpRequest]
    if (request.getMethod == HttpMethod.POST) {
      processPost(request.getContent)
    }
    val response = RequestHandler.createResponse(contentType = "text/html; charset=UTF-8", sendExpiration = false)

    // Add modified or created cookies
    val encoder = new CookieEncoder(true)
    cookies.modified.foreach {
      case cookie => {
        encoder.addCookie(cookie.toNettyCookie)
        response.addHeader(HttpHeaders.SET_COOKIE, encoder.encode())
      }
    }

    doAllWork()
    pageLoading()
    val channel = context.getChannel
    // Write the HttpResponse
    channel.write(response)
    // Set up the writer
    try {
      val output = new ChannelStringWriter(channel, buffer = 1024)
      val writer = (s: String) => output.write(s)
//      val writer = (s: String) => lastWriteFuture = channel.write(ChannelBuffers.wrappedBuffer(s.getBytes()))
      // Write the doctype
      writer(doctype)
      // Stream the page back
      val htmlWriter = HTMLWriter(writer)
      html.write(htmlWriter)
      val future = output.finish()
      future.addListener(ChannelFutureListener.CLOSE)
      pageLoaded()
    } catch {
      case exc: IOException => {
        warn("IOException occurred while writing webpage (%s) with message: %s".format(getClass.getSimpleName, exc.getMessage))
        try {
          channel.close()
        } catch {
          case t: Throwable => // Ignore closing errors
        }
      }
    }
  }

  /**
   * Called before the page is (re)loaded.
   */
  def pageLoading() = {}

  /**
   * Called after the page is (re)loaded.
   */
  def pageLoaded() = {
    view.foreach {
      case tag: HTMLTag => tag.rendered()
      case tag: SVGTag => tag.rendered()
    }
    _rendered.set(true)
  }

  /**
   * Executes the supplied function in this Webpage's context. Useful if pages are cross-communicating.
   *
   * @param f function to invoke in context
   * @tparam T the return value of the function
   * @return T
   */
  def context[T](f: => T): T = WebContext.contextualize[T](this)(f)

  protected def processPost(content: ChannelBuffer) = {}

  override def errorThrown(t: Throwable) = website.errorThrown(this, t)

  override protected[web] def checkIn() {
    super.checkIn()

    if (WebContext.session != null) {
      WebContext.session().checkIn()    // Always update the session when the page gets a check-in
    }
  }

  def fireLater(event: Event) = {
    WorkQueue.enqueue(this, () => fire(event))
  }

  def invokeLater(f: => Unit) = {
    WorkQueue.enqueue(this, () => f)
  }

  override def update(delta: Double) {
    super.update(delta)

    doAllWork()
    updatables.foreach {
      case u => u.update(delta)
    }
  }

  override def dispose() {
    super.dispose()

    Website().session.removeByValue(this)
    bus.clear()
  }
}

object Webpage {
  def apply() = WebContext.webpage()
}