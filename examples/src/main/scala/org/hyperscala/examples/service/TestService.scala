package org.hyperscala.examples.service

import org.hyperscala.service.Service

/**
 * @author Matt Hicks <matt@outr.com>
 */
object TestService extends Service {
  override def basePath = "/service/"

  def simple(name: String) = SimpleResponse(s"Hello $name!")
}

case class SimpleRequest(message: String)

case class SimpleResponse(message: String)