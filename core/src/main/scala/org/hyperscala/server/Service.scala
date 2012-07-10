package org.hyperscala.server

import javax.servlet.http.{HttpServletResponse, HttpServletRequest}

import scala.collection.JavaConversions._

import org.powerscala.reflect._
import org.powerscala.convert.json.Object2JSON

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
abstract class Service[T, R, S <: Session](implicit manifest: Manifest[T]) {
  lazy val enhanced: EnhancedClass = manifest.erasure

  def matches(uri: String): Boolean

  def process(session: S, ref: Option[T]): R

  def apply(session: S, req: HttpServletRequest, resp: HttpServletResponse) = {
    val ref = convert(req.getParameterMap)
    val response = process(session, ref)
    respond(response, req, resp)
  }

  private def convert(map: java.util.Map[String, Array[String]]) = {
    val args = map.toMap.collect {
      case (name, values) if (enhanced.caseValue(name) != None) => {
        val caseValue = enhanced.caseValue(name).get
        name -> values.head
      }
    }
    try {
      Some(enhanced.create[T](args))
    } catch {
      case exc: IllegalArgumentException => None
      case exc => {
        exc.printStackTrace()
        None
      }
    }
  }

  private def respond(result: R, req: HttpServletRequest, resp: HttpServletResponse) = {
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
