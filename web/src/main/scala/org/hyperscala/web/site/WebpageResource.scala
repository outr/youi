package org.hyperscala.web.site

import com.outr.webcommunicator.netty._
import handler.RequestHandler
import org.jboss.netty.channel.{MessageEvent, ChannelHandlerContext}
import org.jboss.netty.handler.codec.http.{HttpResponseStatus, HttpRequest}
import org.hyperscala.web.Scope
import org.hyperscala.web.session.Session
import org.hyperscala.Unique

/**
 * @author Matt Hicks <matt@outr.com>
 */
case class WebpageResource(matcher: HttpRequest => Boolean,
                           loader: HttpRequest => Webpage)(implicit website: Website[_ <: Session]) extends WebResource {
  def request(webapp: NettyWebapp, context: ChannelHandlerContext, event: MessageEvent) = event.getMessage match {
    case request: HttpRequest if (matcher(request)) => {
      val context = WebContext.create(website, request)
      WebContext(context, checkIn = true) {
        try {
          // Load the webpage
          val webpage = loader(request)
          context.webpage = webpage
          Some(webpage)
        } catch {
          // TODO: add security exception handling here
          case t: Throwable => {
            Webpage().errorThrown(t)
            Some(RequestHandler.responder(HttpResponseStatus.INTERNAL_SERVER_ERROR))
          }
        }
      }
    }
    case _ => None
  }
}

object WebpageResource {
  def regex(regex: String,
            scope: Scope = Scope.Request,
            identifier: String = null)(creator: => Webpage)(implicit website: Website[_ <: Session]) = {
    val matcher = (request: HttpRequest) => request.path.matches(regex)
    val loader = scope match {
      case Scope.Page => page(creator)
      case Scope.Request => request(creator)
      case Scope.Session => session(creator, identifier)
      case Scope.Application => session(creator, identifier)
    }
    WebpageResource(matcher, loader)(website)
  }

  def page(creator: => Webpage) = (request: HttpRequest) => {
    val p: Webpage = creator
    val id = Unique()
    Website().session(id) = p     // Temporarily store the page
    p
  }

  def request(creator: => Webpage) = (request: HttpRequest) => creator

  def session(creator: => Webpage, identifier: String = null) = {
    var id = identifier
    (request: HttpRequest) => {
      val s = Website().session
      val page = if (id == null) {
        val p: Webpage = creator
        id = p.getClass.getSimpleName
        p
      } else {
        s.get[Webpage](id) match {
          case Some(p) => p
          case None => creator
        }
      }
      s(id) = page
      page
    }
  }

  def application(creator: => Webpage, identifier: String = null) = {
    var id = identifier
    (request: HttpRequest) => {
      val s = Website().application
      val page = if (id == null) {
        val p: Webpage = creator
        id = p.getClass.getSimpleName
        p
      } else {
        s.get[Webpage](id) match {
          case Some(p) => p
          case None => creator
        }
      }
      s(id) = page
      page
    }
  }
}