package org.hyperscala.web.site

import com.outr.webcommunicator.netty.{RequestResult, MutableWebResource}
import org.powerscala.property.StandardProperty
import org.hyperscala.web.Scope
import org.jboss.netty.handler.codec.http.{HttpResponseStatus, HttpRequest}
import org.hyperscala.web.session.Session
import com.outr.webcommunicator.netty.handler.RequestHandler
import org.powerscala.property.event.PropertyChangingEvent
import org.powerscala.bus.Routing
import org.hyperscala.Unique
import org.jboss.netty.channel.ChannelHandlerContext

/**
 * @author Matt Hicks <matt@outr.com>
 */
class WebpageResource(implicit website: Website[_ <: Session]) extends MutableWebResource {
  website.register(this)

  protected var _link: String = null

  val id = new StandardProperty[String]("id", Unique())
  def link = _link
  val scope = new StandardProperty[Scope]("scope", Scope.Request)
  scope.listeners.synchronous {
    case evt: PropertyChangingEvent if (evt.newValue == null) => Routing.Stop
  }
  loader := load _

  def this(path: String,
            createFunction: => RequestHandler,
            pageScope: Scope,
            pre: (HttpRequest => RequestResult)*)
           (implicit website: Website[_ <: Session]) = {
    this()
    _link = path

    matchers += matches(path)
    creator := ((request: HttpRequest) => createFunction)
    preChecks := pre.toList
    scope := pageScope
  }

  override protected def apply(context: ChannelHandlerContext, request: HttpRequest) = {
    WebContext.wrap {
      WebContext.parse(context, request)
      try {
        val option = super.apply(context, request)
        if (option.nonEmpty) {
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
          Some(RequestHandler.responder(HttpResponseStatus.INTERNAL_SERVER_ERROR))
        }
      }
    }
  }

  private def load(request: HttpRequest): RequestHandler = scope() match {
    case Scope.Request => null
    case Scope.Page => null
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
            pre: (HttpRequest => RequestResult)*)
           (implicit website: Website[_ <: Session]) = new WebpageResource(path, createFunction, pageScope, pre: _*)(website)
}
