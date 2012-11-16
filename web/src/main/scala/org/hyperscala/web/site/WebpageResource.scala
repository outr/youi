package org.hyperscala.web.site

import com.outr.webcommunicator.netty._
import org.jboss.netty.channel.{MessageEvent, ChannelHandlerContext}
import org.jboss.netty.handler.codec.http.HttpRequest
import org.hyperscala.web.Scope
import org.hyperscala.web.session.Session

/**
 * @author Matt Hicks <matt@outr.com>
 */
case class WebpageResource(matcher: HttpRequest => Boolean,
                           loader: HttpRequest => Webpage)(implicit website: Website[_ <: Session]) extends WebResource {
  def request(webapp: NettyWebapp, context: ChannelHandlerContext, event: MessageEvent) = event.getMessage match {
    case request: HttpRequest if (matcher(request)) => {
      val context = WebContext.create(website, request)
      WebContext(context) {
        // Load the webpage
        val webpage = loader(request)
        context.webpage = webpage

        Some(webpage)
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
      case Scope.Request => request(creator)
      case Scope.Session => session(creator, identifier)
      case Scope.Application => session(creator, identifier)
    }
    WebpageResource(matcher, loader)(website)
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