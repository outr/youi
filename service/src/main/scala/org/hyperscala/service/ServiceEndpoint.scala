package org.hyperscala.service

import java.lang.reflect.InvocationTargetException

import com.outr.net.URL
import com.outr.net.http.HttpHandler
import com.outr.net.http.content.{StringContent, ContentType, HttpContent}
import com.outr.net.http.request.HttpRequest
import com.outr.net.http.response.{HttpResponseStatus, HttpResponse}
import org.powerscala.event.Listenable
import org.powerscala.json.JSON
import org.powerscala.log.Logging
import org.powerscala.reflect.{EnhancedClass, MethodArgument, EnhancedMethod}

import scala.annotation.tailrec
import scala.xml.XML

/**
 * @author Matt Hicks <matt@outr.com>
 */
case class ServiceEndpoint(service: Service, name: String, method: EnhancedMethod) extends HttpHandler with Listenable with Logging {
  val uri = {
    var path = service.path
    if (!service.path.endsWith("/")) path = s"$path/"
    if (!service.path.startsWith("/")) path = s"/$path"
    s"$path$name"
  }

  // TODO: better support extending this and modularization through Processor
  private def extractArgument(request: HttpRequest, response: HttpResponse, name: String, argType: EnhancedClass) = if (request.url.parameters.contains(name)) {          // Parameters
    val v = request.url.parameters(name) match {
      case List(entry) => entry
      case list => list
    }
    Some(name -> argType.convertTo[Any](name, v))
  } else if (request.store.contains(name)) {            // Pull from storage
    Some(name -> request.store[Any](name))
  } else if (argType.hasType(classOf[HttpRequest])) {   // HttpRequest
    Some(name -> request)
  } else if (argType.hasType(classOf[HttpResponse])) {  // HttpResponse
    Some(name -> response)
  } else if (argType.hasType(classOf[URL])) {           // URL
    Some(name -> request.url)
  } else {
    None
  }

  override def onReceive(request: HttpRequest, response: HttpResponse) = {
    val invocation = service.beforeInvoke.fire(ServiceInvocation(this, request, response))
    handle(invocation.request, invocation.response)
  }

  private def handle(request: HttpRequest, response: HttpResponse) = {
    val content = request.content.map(contentToType)
    // TODO: support populating method arguments via:
    // TODO: JSON content
    // TODO: Headers

    val formatJSON = extractArgument(request, response, "formatJSON", classOf[String]).map(t => t._2.toString.toBoolean).getOrElse(service.formatted)

    def valueForArg(arg: MethodArgument) = {
      val name = arg.name
      val argType = arg.`type`
      val argument = extractArgument(request, response, name, argType)
      if (argument.isEmpty && !arg.hasDefault) {
        throw ServiceException(s"Unable to find value for ${uri}.$name.", ServiceException.MissingValues)
      }
      argument
    }

    val (status, returnValue) = try {
      val args = method.args.map(valueForArg).flatten.toMap
      HttpResponseStatus.OK -> method[Any](service, args)
    } catch {
      case t: Throwable => {
        rootCause(t) match {
          case exc: ServiceException => HttpResponseStatus.InternalServerError -> exc
          case _ => {
            error(s"Error occurred on endpoint: $uri ($method).", t)
            HttpResponseStatus.InternalServerError -> new ServiceException("An Internal Error Occurred.", ServiceException.InternalError)
          }
        }
      }
    }
    val responseContent = JSON.dontWriteExtras {
      StringContent(JSON.renderJSON(JSON.parseAndGet[Any](returnValue), pretty = formatJSON), ContentType.JSON)
    }
    response.copy(status = status, content = responseContent)
  }

  @tailrec
  private def rootCause(t: Throwable): Throwable = if (t.getCause == null) {
    t
  } else {
    rootCause(t.getCause)
  }

  private def contentToType(content: HttpContent) = content.contentType match {
    case ContentType.JSON => JSON.parseJSON(content.asString)
    case ContentType.XML => org.json4s.Xml.toJson(XML.loadString(content.asString))
  }
}