package org.hyperscala.service

import com.outr.net.URL
import com.outr.net.http.HttpHandler
import com.outr.net.http.content.{StringContent, ContentType, HttpContent}
import com.outr.net.http.request.HttpRequest
import com.outr.net.http.response.{HttpResponseStatus, HttpResponse}
import org.powerscala.event.Listenable
import org.powerscala.json.JSON
import org.powerscala.log.Logging
import org.powerscala.reflect.{MethodArgument, EnhancedMethod}

import scala.xml.XML

/**
 * @author Matt Hicks <matt@outr.com>
 */
case class ServiceEndpoint(service: Service, name: String, method: EnhancedMethod) extends HttpHandler with Listenable with Logging {
  val uri = if (service.path.endsWith("/")) s"${service.path}$name" else s"${service.path}/$name"

  override def onReceive(request: HttpRequest, response: HttpResponse) = {
    val invocation = service.beforeInvoke.fire(ServiceInvocation(this, request, response))
    handle(invocation.request, invocation.response)
  }

  private def handle(request: HttpRequest, response: HttpResponse) = {
    val content = request.content.map(contentToType)
    // TODO: support populating method arguments via:
    // TODO: JSON content
    // TODO: Headers

    def valueForArg(arg: MethodArgument) = {
      val name = arg.name
      val argType = arg.`type`
      // TODO: better support extending this and modularization through Processor
      if (request.url.parameters.contains(name)) {          // Parameters
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
      } else if (arg.hasDefault) {                          // Default arguments
        None
      } else {                                              // Fire an exception if the value is missing
        throw ServiceException(s"Unable to find value for ${uri}.$name.", ServiceException.MissingValues)
      }
    }

    val (status, returnValue) = try {
      val args = method.args.map(valueForArg).flatten.toMap
      HttpResponseStatus.OK -> method[Any](service, args)
    } catch {
      case exc: ServiceException => HttpResponseStatus.InternalServerError -> exc
      case t: Throwable => {
        error(s"Error occurred on endpoint: $uri ($method).", t)
        HttpResponseStatus.InternalServerError -> new ServiceException("An Internal Error Occurred.", ServiceException.InternalError)
      }
    }
    val responseContent = JSON.dontWriteExtras {
      StringContent(JSON.renderJSON(JSON.parseAndGet[Any](returnValue), pretty = service.formatted), ContentType.JSON)
    }
    response.copy(status = status, content = responseContent)
  }

  private def contentToType(content: HttpContent) = content.contentType match {
    case ContentType.JSON => JSON.parseJSON(content.asString)
    case ContentType.XML => org.json4s.Xml.toJson(XML.loadString(content.asString))
  }
}