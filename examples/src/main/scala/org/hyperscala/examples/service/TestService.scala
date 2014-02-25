package org.hyperscala.examples.service

import org.hyperscala.service.Service

/**
 * @author Matt Hicks <matt@outr.com>
 */
object TestService extends Service {
  override def basePath = "/service/"

  beforeInvoke.on {
    case i => i.args match {
      case Some(args) => i.copy(args = Some(args + ("user" -> "Anonymous")))
      case None => i
    }
  }

  def simple(name: String) = SimpleResponse(s"Hello $name!")

  def greeting(name: Option[String] = None, age: Int = 21, user: String = null) = SimpleResponse(s"Hello ${name.getOrElse("World")}! $age isn't that old! User: $user")
}

case class SimpleRequest(message: String)

case class SimpleResponse(message: String)