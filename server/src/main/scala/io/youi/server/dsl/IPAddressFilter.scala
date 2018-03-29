package io.youi.server.dsl

import io.youi.http.HttpConnection
import io.youi.net.IP

case class IPAddressFilter(allow: List[IP] = Nil, deny: List[IP] = Nil) extends ConnectionFilter {
  override def filter(connection: HttpConnection): Option[HttpConnection] = {
    val ip = connection.request.source
    val allowed = if (allow.isEmpty) true else allow.contains(ip)
    val denied = if (deny.isEmpty) false else deny.contains(ip)
    if (allowed && !denied) {
      Some(connection)
    } else {
      None
    }
  }
}