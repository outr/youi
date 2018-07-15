package io.youi.server.dsl

import io.youi.http.HttpConnection

object PathPart {
  private val Key: String = "PathPart"

  def fulfilled(connection: HttpConnection): Boolean = connection.store.getOrElse[List[String]](Key, Nil).isEmpty

  def take(connection: HttpConnection, part: String): Option[HttpConnection] = {
    val parts = connection.store.getOrElse(Key, connection.request.url.path.parts)
    if (parts.nonEmpty && parts.head == part) {
      connection.store(Key) = parts.tail
      Some(connection)
    } else {
      None
    }
  }
}
