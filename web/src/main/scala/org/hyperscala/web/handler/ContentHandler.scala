package org.hyperscala.web.handler

import javax.servlet.http.{HttpServletResponse, HttpServletRequest}
import org.powerscala.Priority
import org.powerscala.hierarchy.Element
import org.hyperscala.html.attributes.Method

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait ContentHandler extends Element {
  def link: String

  def matches(uri: String): Boolean

  def apply(method: Method, request: HttpServletRequest, response: HttpServletResponse): Unit

  def priority: Priority = Priority.Normal
}
