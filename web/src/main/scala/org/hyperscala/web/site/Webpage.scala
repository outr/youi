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
import org.hyperscala.web.module.Module
import org.powerscala.hierarchy.{Parent, Element, ContainerView}
import org.hyperscala.web.session.MapSession
import org.hyperscala.css.StyleSheet
import org.powerscala.property.PropertyParent
import org.powerscala.reflect.CaseValue

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Webpage extends Page with RequestHandler with Parent with PropertyParent {
  Page.instance.set(this)

  val name = () => getClass.getSimpleName

  def defaultTitle = CaseValue.generateLabel(getClass.getSimpleName.replaceAll("\\$", ""))

  protected[site] var webContext: WebContext = _

  val store = new MapSession {
    override def timeout = Double.MaxValue
  }

  def website = WebContext().website
  def headers = WebContext().headers
  def url = WebContext().url
  def cookies = WebContext().cookies

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

  private var modules = Set.empty[Module]
  def require(module: Module) = synchronized {
    if (!modules.contains(module)) {
      modules += module
      module.load(this)
    }
  }

  /**
   * Returns all the HTMLTags that currently reference the supplied StyleSheet in the entire Webpage hierarchy.
   */
  def tagsByStyleSheet(ss: StyleSheet) = view.collect {
    case tag if (tag.style.loaded && tag.style() == ss) => tag
  }

  def apply(webapp: NettyWebapp, context: ChannelHandlerContext, event: MessageEvent) = {
    WebContext(webContext) {
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

      // Generate HTML from page
      // TODO: stream content back rather than loading into a buffer first
//      val buffer = ChannelBuffers.dynamicBuffer()
//      val output = new ChannelBufferOutputStream(buffer)
//      output.write(doctype)
//      val writer = HTMLWriter(output)
//      html.write(writer)
//
//      // Stream data back
//      val channel = context.getChannel
//      channel.write(response)
//      channel.write(output.buffer()).addListener(ChannelFutureListener.CLOSE)

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
}

object Webpage {
  def apply() = WebContext().webpage
}