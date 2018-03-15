package io.youi.server

import io.youi.http.{Content, HttpConnection, Method, Status}
import io.youi.net.ContentType
import io.youi.server.chain.{Continue, HttpLink, Result, Stop}

import scala.language.implicitConversions
import scala.xml.Elem

package object chain {
  def ip(allow: Seq[String] = Nil, deny: Seq[String] = Nil): IPAddressLink = new IPAddressLink(allow.toSet, deny.toSet)
  def ipAllow(allow: String*): IPAddressLink = ip(allow = allow)
  def ipDeny(deny: String*): IPAddressLink = ip(deny = deny)

  def method(allow: Seq[Method] = Nil, deny: Seq[Method] = Nil): MethodLink = new MethodLink(allow.toSet, deny.toSet)
  def methodAllow(allow: Method*): MethodLink = method(allow = allow)
  def methodDeny(deny: Method*): MethodLink = method(deny = deny)

  def path(allow: Seq[String] = Nil, deny: Seq[String] = Nil): PathLink = new PathLink(allow.toSet, deny.toSet)
  def pathAllow(allow: String*): PathLink = path(allow = allow)
  def pathDeny(deny: String*): PathLink = path(deny = deny)

  def respond(content: Content, contentType: ContentType, status: Status = Status.OK): RespondLink = new RespondLink(content, status)

  implicit def string2Content(value: String): Content = Content.string(value, ContentType.`text/plain`)
  implicit def xml2Content(value: Elem): Content = Content.xml(value, ContentType.`text/xml`)
}

class IPAddressLink(allow: Set[String] = Set.empty, deny: Set[String] = Set.empty) extends HttpLink {
  override def link(connection: HttpConnection): Result = {
    val ip = connection.request.source.addressString
    val allowed = if (allow.isEmpty) true else allow.contains(ip)
    val denied = if (deny.isEmpty) false else deny.contains(ip)
    if (allowed && !denied) {
      Continue
    } else {
      Stop
    }
  }
}

class MethodLink(allow: Set[Method] = Set.empty, deny: Set[Method] = Set.empty) extends HttpLink {
  override def link(connection: HttpConnection): Result = {
    val method = connection.request.method
    val allowed = if (allow.isEmpty) true else allow.contains(method)
    val denied = if (deny.isEmpty) false else deny.contains(method)
    if (allowed && !denied) {
      Continue
    } else {
      Stop
    }
  }
}

class PathLink(allow: Set[String] = Set.empty, deny: Set[String] = Set.empty) extends HttpLink {
  override def link(connection: HttpConnection): Result = {
    val path = connection.request.url.path.encoded
    val allowed = if (allow.isEmpty) true else allow.contains(path)
    val denied = if (deny.isEmpty) false else deny.contains(path)
    if (allowed && !denied) {
      Continue
    } else {
      Stop
    }
  }
}

class RespondLink(content: Content, status: Status) extends HttpLink {
  override def link(connection: HttpConnection): Result = {
    connection.update { response =>
      response.copy(status = status, content = Some(content))
    }

    Continue
  }
}