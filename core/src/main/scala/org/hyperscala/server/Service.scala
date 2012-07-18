package org.hyperscala.server

import javax.servlet.http.{HttpServletResponse, HttpServletRequest}

import scala.collection.JavaConversions._

import org.powerscala.reflect._
import org.powerscala.convert.json.Object2JSON
import java.util.UUID

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait Service[S <: Session] {
  protected def name: String

  def js = "/service/%s.js".format(name)
  def json = "/service/%s.json".format(name)

  final def matches(uri: String) = uri.startsWith("/service/%s".format(name))

  /**
   * Invoked immediately before the service is invoked.
   */
  def before(session: S): Unit = {}

  /**
   * Invoked immediately after the service is invoked.
   */
  def after(session: S): Unit = {}

  final def apply(session: S, request: HttpServletRequest, response: HttpServletResponse): Unit = {
    before(session)
    invoke(session, request, response)
    after(session)
  }

  protected def invoke(session: S, request: HttpServletRequest, response: HttpServletResponse): Unit
}

abstract class ServiceResponse[R, S <: Session] extends Service[S] {
  def process(session: S): R

  protected def invoke(session: S, request: HttpServletRequest, response: HttpServletResponse) = {
    val r = process(session)
    Service.respond(r, request, response)
  }
}

abstract class ServiceRequestResponse[T, R, S <: Session](implicit manifest: Manifest[T]) extends Service[S] {
  lazy val enhanced: EnhancedClass = manifest.erasure

  def process(session: S, ref: Option[T]): R

  protected def invoke(session: S, req: HttpServletRequest, resp: HttpServletResponse) = {
    val ref = convert(req)
    val response = process(session, ref)
    Service.respond(response, req, resp)
  }

  protected def generateRequestArgs(request: HttpServletRequest): Map[String, Any] = {
    request.getParameterMap.collect {
      case (name, values) if (enhanced.caseValue(name) != None) => {
        val caseValue = enhanced.caseValue(name).get
        caseValue.name -> convert(values, caseValue)
      }
    }.toMap
  }

  protected def convert(values: Array[String], caseValue: CaseValue): Any = caseValue.valueType.simpleName match {
    case "String" => values.head
    case "UUID" => UUID.fromString(values.head)
    case _ => throw new RuntimeException("Unable to convert %s with value %s".format(caseValue.valueType.name, values.head))
  }

  private def convert(request: HttpServletRequest) = {
    val args = generateRequestArgs(request)
    if (args.nonEmpty) {
      try {
        Some(enhanced.create[T](args))
      } catch {
        case t: Throwable => {
          t.printStackTrace()
          None
        }
      }
    } else {
      None
    }
  }
}

object Service {
  def respond(result: Any, req: HttpServletRequest, resp: HttpServletResponse) = {
    val json = result match {
      case null => "{}"
      case _ => Object2JSON.toJSON(result)
    }
    val filename = req.getRequestURI match {
      case s if (s.indexOf('/') != -1) => s.substring(s.lastIndexOf('/') + 1)
      case s => s
    }
    val (name, extension) = filename match {
      case s if (s.indexOf('.') != -1) => s.splitAt(s.lastIndexOf('.')) match {
        case (n, e) => n -> e.substring(1).toLowerCase
      }
      case s => s -> "json"
    }
    val response = extension match {
      case "json" => {
        resp.setContentType("application/json")
        json
      }
      case "js" => {
        resp.setContentType("application/javascript")
        "document.%s = %s;".format(name, json)
      }
    }
    val writer = resp.getWriter
    try {
      writer.write(response)
    } finally {
      writer.flush()
      writer.close()
    }
  }
}
