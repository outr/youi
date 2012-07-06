package org.hyperscala.server

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}

import scala.collection.JavaConversions._

import org.powerscala.reflect._
import org.powerscala.convert.json.Object2JSON

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
abstract class Service[T, R](implicit manifest: Manifest[T]) extends HttpServlet {
  lazy val enhanced: EnhancedClass = manifest.erasure

  override def doPost(req: HttpServletRequest, resp: HttpServletResponse) {
    val session = req.getSession
    convert(req.getParameterMap) match {
      case Some(ref) => {
        val result = process(ref)
        session.setAttribute("%s.result".format(getClass.getName), result)
        respond(result, resp)
      }
      case None => respond(loadResult(req), resp)
    }
  }

  protected def loadResult(req: HttpServletRequest): R = {
    req.getSession.getAttribute("%s.result".format(getClass.getName)).asInstanceOf[R] match {
      case null => default
      case r => r
    }
  }

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse) {
    val session = req.getSession
    convert(req.getParameterMap) match {
      case Some(ref) => {
        val result = process(ref)
        session.setAttribute("%s.result".format(getClass.getName), result)
        respond(result, resp)
      }
      case None => respond(loadResult(req), resp)
    }
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
      case exc => {
        exc.printStackTrace()
        None
      }
    }
  }

  private def respond(result: R, resp: HttpServletResponse) = {
    val json = result match {
      case null => "{}"
      case _ => Object2JSON.toJSON(result)
    }
    resp.setContentType("application/json")
    val writer = resp.getWriter
    try {
      writer.write(json)
    } finally {
      writer.flush()
      writer.close()
    }
  }

  def default: R = null.asInstanceOf[R]

  def process(ref: T): R
}
