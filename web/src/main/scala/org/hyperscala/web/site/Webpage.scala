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
import org.hyperscala.web.module.ModularPage
import org.powerscala.hierarchy.{Parent, Element, ContainerView}
import org.hyperscala.web.session.MapSession
import org.hyperscala.css.StyleSheet
import org.powerscala.property.PropertyParent
import org.powerscala.reflect.CaseValue
import org.powerscala.concurrent.Time._
import org.powerscala.concurrent.WorkQueue
import org.powerscala.event.Event

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Webpage extends Page with ModularPage with RequestHandler with Parent with PropertyParent with Temporal with WorkQueue with Contextual {
  Page.instance.set(this)

  val name = () => getClass.getSimpleName

  def defaultTitle = CaseValue.generateLabel(getClass.getSimpleName.replaceAll("\\$", ""))

  val store = new MapSession {
    override def timeout = Double.MaxValue
  }

  def website = Website()
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

  /**
   * Returns all the HTMLTags that currently reference the supplied StyleSheet in the entire Webpage hierarchy.
   */
  def tagsByStyleSheet(ss: StyleSheet) = view.collect {
    case tag if (tag.style.loaded && tag.style() == ss) => tag
  }

  def apply(webapp: NettyWebapp, context: ChannelHandlerContext, event: MessageEvent) = {
    val request = event.getMessage.asInstanceOf[HttpRequest]
    if (request.getMethod == HttpMethod.POST) {
      processPost(request.getContent)
    }
    val response = RequestHandler.createResponse(contentType = "text/html", sendExpiration = false)

    // Add modified or created cookies
    val encoder = new CookieEncoder(true)
    webContext.cookies.modified.foreach {
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
    var lastWriteFuture: ChannelFuture = null
    val writer = (s: String) => lastWriteFuture = channel.write(ChannelBuffers.wrappedBuffer(s.getBytes()))
    // Write the doctype
    writer(doctype)
    // Stream the page back
    val htmlWriter = HTMLWriter(writer)
    html.write(htmlWriter)
    lastWriteFuture.addListener(ChannelFutureListener.CLOSE)
    pageLoaded()
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
      case tag => tag.rendered()
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

  def fireLater(event: Event) = {
    WorkQueue.enqueue(this, () => fire(event))
  }

  def invokeLater(f: => Unit) = {
    WorkQueue.enqueue(this, () => f)
  }

  override def update(delta: Double) {
    super.update(delta)

    doAllWork()
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