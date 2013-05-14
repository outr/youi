package org.hyperscala.web.site

import org.powerscala.property.Property
import org.hyperscala.web.Scope
import org.jboss.netty.handler.codec.http.{HttpResponseStatus, HttpRequest}
import com.outr.webcommunicator.netty._
import com.outr.webcommunicator.netty.handler.RequestHandler
import org.hyperscala.Unique
import org.jboss.netty.channel.ChannelHandlerContext
import org.powerscala.log.Logging

/**
 * @author Matt Hicks <matt@outr.com>
 */
class WebpageResource(autoRegister: Boolean = true) extends MutableWebResource()(Website()) with Logging {
  if (autoRegister) {
    Website().register(this)
  }

  protected var _link: String = null

  val id = new Property[String](default = Some(Unique()))
  def link = _link
  val scope = new Property[Scope](default = Some(Scope.Request))
  scope.changing.on {     // Scope should never be allowed to be set to null
    case evt => evt match {
      case null => None
      case _ => Some(evt)
    }
  }
  loader := load _

  def this(path: String,
            createFunction: => RequestHandler,
            pageScope: Scope,
            pre: (HttpRequest => RequestResult)*) = {
    this()
    _link = path

    matchers += matches(path)
    creator := ((request: HttpRequest) => createFunction)
    preChecks := pre.toList
    scope := pageScope
  }

  override protected def apply(context: ChannelHandlerContext, request: HttpRequest) = {
    try {
      val option = super.apply(context, request)
      if (option.nonEmpty) {                                          // Matched handler
        val handler = option.get
        handler match {
          case webpage: Webpage => WebContext.webpage := webpage
          case _ => // Not a webpage
        }
        cache(handler)
        WebContext.checkIn()
      }
      option
    } catch {
      case t: Throwable => {
        WebContext.webpage.get() match {
          case Some(page) => page.errorThrown(t)
          case None => Website().errorThrown(null, t)
        }
        val errorPage = Website().errorPage(request, t)
        if (errorPage.nonEmpty) {
          errorPage
        } else {
          Some(RequestHandler.responder(HttpResponseStatus.INTERNAL_SERVER_ERROR))
        }
      }
    }
  }

  private def load(request: HttpRequest): RequestHandler = scope() match {
    case Scope.Request => null
    case Scope.Page => {
      val url = request2URL(request)
      url.parameters.get("pageId") match {
        case Some(pageIds) => {
          val pageId = pageIds.head
          Website().session.getOrElse[RequestHandler](pageId, null)
        }
        case None => null
      }
    }
    case Scope.Session => Website().session.getOrElse[RequestHandler](id(), null)
    case Scope.Application => Website().application.getOrElse[RequestHandler](id(), null)
  }

  private def cache(handler: RequestHandler) = scope() match {
    case Scope.Request => // We don't cache requests
    case Scope.Page => Website().session(Unique()) = handler        // Unique reference for each page request
    case Scope.Session => Website().session(id()) = handler         // WebpageResource.id in session scope
    case Scope.Application => Website().application(id()) = handler // WebpageResource.id in application scope
  }
}

object WebpageResource {
  def apply(path: String,
            createFunction: => RequestHandler,
            pageScope: Scope,
            pre: (HttpRequest => RequestResult)*) = new WebpageResource(path, createFunction, pageScope, pre: _*)
}
