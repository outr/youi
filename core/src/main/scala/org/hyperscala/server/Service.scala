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
    val ref = convert(req.getParameterMap)
    val result = process(ref)
    session.setAttribute("%s.result".format(getClass.getName), result)
    respond(result, resp)
  }

  protected def loadResult(req: HttpServletRequest): R = {
    req.getSession.getAttribute("%s.result".format(getClass.getName)).asInstanceOf[R] match {
      case null => default
      case r => r
    }
  }

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse) {
    val result = loadResult(req)
    respond(result, resp)
  }

  private def convert(map: java.util.Map[String, Array[String]]) = {
    val args = map.toMap.collect {
      case (name, values) if (enhanced.caseValue(name) != None) => {
        val caseValue = enhanced.caseValue(name).get
        name -> values.head
      }
    }
    enhanced.create[T](args)
  }

  private def respond(result: R, resp: HttpServletResponse) = {
    val json = Object2JSON.toJSON(result)
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
