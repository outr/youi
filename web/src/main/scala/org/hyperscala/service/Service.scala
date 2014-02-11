package org.hyperscala.service

import org.hyperscala.web.Website
import com.outr.net.http.session.Session

import org.powerscala.reflect._

import org.powerscala.json._
import scala.util.parsing.json.JSON
import com.outr.net.http.{HttpParameters, HttpHandler}
import com.outr.net.http.request.HttpRequest
import com.outr.net.http.response.{HttpResponseStatus, HttpResponse}
import com.outr.net.http.content.{ContentType, StringContent}
import org.powerscala.log.Logging

/**
 * Service exposes methods as web service end-points through JSON communication.
 *
 * @author Matt Hicks <matt@outr.com>
 */
trait Service {
  private lazy val endpoints = findEndpoints()

  /**
   * The base path used to build all end-points
   */
  def basePath: String

  /**
   * Registers the service end-points (methods) in this service to the supplied Website.
   *
   * @param site the site to register with
   * @tparam S the session type
   */
  def register[S <: Session](site: Website[S]) = {
    endpoints.foreach {
      case endpoint => site.addHandler(endpoint, endpoint.uri)
    }
  }

  private def findEndpoints() = {
    val clazz: EnhancedClass = getClass
    clazz.methods.collect {
      case m if m.isPublic && m.declaring == clazz && m.returnType.`type`.isCase => ServiceEndpoint(this, m.name, m)
    }
  }
}

object Service {
  def toCamelCase(name: String) = "-([a-z])".r.replaceAllIn(name, m => m.group(1).toUpperCase)
  def fromCamelCase(name: String) = "([A-Z])".r.replaceAllIn(name, m => "-" + m.group(0).toLowerCase)
}

case class ServiceEndpoint(service: Service, name: String, method: EnhancedMethod) extends HttpHandler with Logging {
  def uri = s"${service.basePath}$name"

  def callJSON(content: String) = {
    val args = if (method.args.isEmpty) {
      Map.empty[String, Any]
    } else {
      JSON.parseFull(content) match {
        case Some(v) => {
          val map = v.asInstanceOf[Map[String, Any]]
          map.map {
            case (key, value) => {
              val name = Service.toCamelCase(key)
              method.arg(name) match {
                case Some(methodArgument) => {
                  val arg = JSONConverter.parseJSON[Any](value)(Manifest.classType[Any](methodArgument.`type`.javaClass))
                  name -> arg
                }
                case None => throw new ServiceException(s"Failed to find argument: $name in $method")
              }
            }
          }
        }
        case None => throw new ServiceException(s"Failed to parse JSON: $content")
      }
    }
    val result = method[Any](service, args, requireValues = true)
    JSONConverter.generate(result, specifyClassName = false)
  }

  def callParameters(params: HttpParameters) = {
    val args = params.values.map {
      case (key, values) => {
        val name = Service.toCamelCase(key)
        method.arg(name) match {
          case Some(methodArgument) => {
            val value = if (values.isEmpty) {
              null
            } else if (values.tail.isEmpty) {
              values.head
            } else {
              values
            }
            val arg = methodArgument.`type`.convertTo[Any](name, value)
            Some(name -> arg)
          }
          case None => None   // Ignore extra parameters
        }
      }
    }.flatten.toMap
    val result = method[Any](service, args, requireValues = true)
    JSONConverter.generate(result, specifyClassName = false)
  }

  override def onReceive(request: HttpRequest, response: HttpResponse) = {
    val (responseString, status) = try {
      val content = request.contentString.getOrElse(null)
      if (content != null || method.args.isEmpty) {
        callJSON(content) -> HttpResponseStatus.OK
      } else {
        callParameters(request.url.parameters) -> HttpResponseStatus.OK
      }
    } catch {
      case exc: ServiceException => JSONConverter.generate(ServiceErrorResponse(ServiceError(exc.message, exc.code)), specifyClassName = false) -> HttpResponseStatus.InternalServerError
      case exc: MissingArgumentException => JSONConverter.generate(ServiceErrorResponse(ServiceError(exc.message, -1)), specifyClassName = false) -> HttpResponseStatus.InternalServerError
      case t: Throwable => {
        error(s"Error occurred on endpoint: $uri ($method)", t)
        JSONConverter.generate(ServiceErrorResponse(ServiceError("Internal error occurred", -1)), specifyClassName = false) -> HttpResponseStatus.InternalServerError
      }
    }
    response.copy(content = StringContent(responseString, contentType = ContentType.JSON), status = status)
  }
}

case class ServiceError(message: String, code: Int)

case class ServiceErrorResponse(error: ServiceError)