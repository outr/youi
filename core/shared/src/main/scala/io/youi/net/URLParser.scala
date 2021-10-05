package io.youi.net

import scala.util.Try

object URLParser {
  def apply(s: String,
            absolutizePath: Boolean = true,
            validateTLD: Boolean = true,
            defaultProtocol: Protocol = Protocol.Https): Either[URLParseFailure, URL] = {
    if ((s.contains('.') || s.contains(":")) && !s.startsWith(":") && !s.endsWith(".")) {
      val (protocolOption, stage1) = extractProtocol(s)
      val (hostSection, pathSection) = separateHostAndPath(stage1)
      val (host, port) = separateHostAndPort(hostSection)
      val (stage2, fragment) = extractFragment(pathSection)
      val (stage3, parameters) = extractParameters(stage2)
      val path = Path.parse(stage3)

      if (host.contains("..")) {
        Left(URLParseFailure(s"$s has an invalid host", URLParseFailure.InvalidHost))
      } else if (protocolOption.isEmpty && host.contains('@') && !host.contains(':')) {
        Left(URLParseFailure(s"$s appears to be an email address", URLParseFailure.EmailAddress, None))
      } else {
        val protocol = protocolOption.getOrElse(defaultProtocol)
        val url = URL(
          protocol = protocol,
          host = host,
          port = port.orElse(protocol.defaultPort).getOrElse(-1),
          path = path,
          parameters = parameters,
          fragment = fragment
        )

        if (url.ip.isEmpty && url.host.count(_ == ':') > 1) {
          Left(URLParseFailure(s"Invalid host: ${url.host}", URLParseFailure.InvalidHost))
        } else if (validateTLD) {
          url.tld match {
            case Some(tld) if !TopLevelDomains.isValid(tld) => Left(URLParseFailure(s"Invalid top-level domain: [$tld]", URLParseFailure.InvalidTopLevelDomain))
            case _ => Right(url)
          }
        } else {
          Right(url)
        }
      }
    } else {
      Left(URLParseFailure(s"$s is not a valid URL", URLParseFailure.QuickFail))
    }
  }

  def extractProtocol(s: String): (Option[Protocol], String) = if (s.contains("://")) {
    val index = s.indexOf("://")
    val content = s.substring(0, index)
    val protocol = Protocol(content)
    (Some(protocol), s.substring(index + 3))
  } else if (s.startsWith("//")) {
    (Some(Protocol.Https), s.substring(2))
  } else {
    (None, s)
  }

  def separateHostAndPath(s: String): (String, String) = if (s.contains('/')) {
    val index = s.indexOf('/')
    (s.substring(0, index), s.substring(index))
  } else {
    (s, "")
  }

  def separateHostAndPort(s: String): (String, Option[Int]) = if (s.contains(':')) {
    val index = s.lastIndexOf(':')
    val host = s.substring(0, index)
    val port = Try(s.substring(index + 1).toInt).toOption
    if (port.isEmpty) {
      (s, None)
    } else {
      (host, port)
    }
  } else {
    (s, None)
  }

  def extractFragment(s: String): (String, Option[String]) = if (s.contains('#')) {
    val index = s.indexOf('#')
    (s.substring(0, index), Some(s.substring(index + 1)))
  } else {
    (s, None)
  }

  def extractParameters(s: String): (String, Parameters) = if (s.contains('?')) {
    val index = s.indexOf('?')
    val pre = s.substring(0, index)
    val post = s.substring(index + 1)
    val params = Parameters.parse(post)
    (pre, params)
  } else {
    (s, Parameters.empty)
  }
}