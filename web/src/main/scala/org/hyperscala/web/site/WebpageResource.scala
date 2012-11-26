package org.hyperscala.web.site

import com.outr.webcommunicator.netty.MutableWebResource
import org.powerscala.property.StandardProperty
import org.hyperscala.web.Scope
import org.jboss.netty.handler.codec.http.{HttpResponseStatus, HttpRequest}
import org.hyperscala.web.session.Session
import com.outr.webcommunicator.netty.handler.RequestHandler
import org.powerscala.property.event.PropertyChangingEvent
import org.powerscala.bus.Routing
import org.hyperscala.Unique

/**
 * @author Matt Hicks <matt@outr.com>
 */
class WebpageResource(implicit website: Website[_ <: Session]) extends MutableWebResource {
  val id = new StandardProperty[String]("id", Unique())
  val scope = new StandardProperty[Scope]("scope", Scope.Request)
  scope.listeners.synchronous {
    case evt: PropertyChangingEvent if (evt.newValue == null) => Routing.Stop
  }
  loader := load _

  override protected def apply(request: HttpRequest) = {
    val context = WebContext.create(website, request)
    WebContext(context, checkIn = true) {
      try {
        val option = super.apply(request)
        val handler = option.get
        handler match {
          case webpage: Webpage => context.webpage = webpage
          case _ => // Not a webpage
        }
        cache(handler)
        option
      } catch {
        case t: Throwable => {
          Webpage().errorThrown(t)
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
  def apply(path: String, createFunction: => RequestHandler, pageScope: Scope = Scope.Request)(implicit website: Website[_ <: Session]) = new WebpageResource {
    val link = path

    matchers += matches(path)
    creator := ((request: HttpRequest) => createFunction)
    scope := pageScope
  }
}
