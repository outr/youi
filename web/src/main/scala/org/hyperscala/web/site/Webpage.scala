package org.hyperscala.web.site

import org.hyperscala.Page
import com.outr.webcommunicator.netty.handler.RequestHandler
import com.outr.webcommunicator.netty.NettyWebapp
import org.jboss.netty.channel.{MessageEvent, ChannelHandlerContext}
import org.jboss.netty.handler.codec.http.{HttpMethod, HttpRequest, CookieEncoder}
import com.google.common.net.HttpHeaders

import org.hyperscala.html._
import org.hyperscala.io.HTMLWriter
import java.io.{ByteArrayInputStream, ByteArrayOutputStream}
import org.jboss.netty.buffer.ChannelBuffer
import org.hyperscala.web.module.Module
import org.powerscala.hierarchy.{Parent, Element, ContainerView}
import org.hyperscala.web.session.MapSession
import org.hyperscala.css.StyleSheet
import org.powerscala.property.PropertyParent

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait Webpage extends Page with RequestHandler with Parent with PropertyParent {
  Page.instance.set(this)

  val name = () => getClass.getSimpleName

  protected[site] var webContext: WebContext = _

  val store = new MapSession {
    override def timeout = Double.MaxValue
  }

  def website = WebContext().website
  def headers = WebContext().headers
  def url = WebContext().url
  def cookies = WebContext().cookies

  val doctype = "<!DOCTYPE html>\r\n".getBytes
  val html = new tag.HTML
  val head = new tag.Head {
    val title = new tag.Title
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
      // TODO: actually stream instead of this crap!
      val output = new ByteArrayOutputStream()
      output.write(doctype)
      val writer = HTMLWriter(output)
      html.write(writer)
      output.flush()
      val bytes = output.toByteArray
      val input = new ByteArrayInputStream(bytes)

      // Stream page out
      val channel = context.getChannel
      channel.write(response)
      RequestHandler.writeInput(channel, input, chunkSize, closeOnFinish = true)
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