package io.youi.server.dsl

import cats.effect.IO
import io.youi.http.HttpConnection
import io.youi.net.IP

case class IPAddressFilter(allow: List[IP] = Nil, deny: List[IP] = Nil) extends ConnectionFilter {
  override def filter(connection: HttpConnection): IO[FilterResponse] = IO {
    val ip = connection.request.originalSource
    val allowed = if (allow.isEmpty) true else allow.contains(ip)
    val denied = if (deny.isEmpty) false else deny.contains(ip)
    if (allowed && !denied) {
      FilterResponse.Continue(connection)
    } else {
      FilterResponse.Stop(connection)
    }
  }
}