package io.youi.net

import io.youi.util.URLUtil

case class URL(protocol: Protocol = Protocol.Http,
               host: String = "localhost",
               port: Int = 80,
               path: Path = Path.empty,
               parameters: Parameters = Parameters.empty,
               fragment: Option[String] = None) {
  def replaceBase(base: String): URL = URL(s"$base${encoded.pathAndArgs}")
  def replacePathAndParams(pathAndParams: String): URL = URL(s"$base$pathAndParams")

  def withPath(path: String, absolutize: Boolean = true): URL = {
    val updated = this.path.append(path).absolute
    copy(path = updated)
  }

  def withParam(key: String, value: String, append: Boolean = true): URL = {
    copy(parameters = parameters.withParam(key, value, append))
  }

  def paramList(key: String): List[String] = parameters.values(key)
  def param(key: String): Option[String] = paramList(key).headOption

  lazy val base: String = {
    val b = new StringBuilder
    b.append(protocol.scheme)
    b.append("://")
    b.append(host)
    if (!protocol.defaultPort.contains(port)) {
      b.append(s":$port")       // Not using the default port for the protocol
    }
    b.toString()
  }

  lazy val encoded: URLParts = new URLParts(encoded = true)
  lazy val decoded: URLParts = new URLParts(encoded = false)

  override def toString: String = encoded.asString

  class URLParts(encoded: Boolean) {
    def base: String = URL.this.base
    lazy val pathAndArgs: String = {
      val b = new StringBuilder
      b.append(path)
      b.append(if (encoded) parameters.encoded else parameters.decoded)
      b.toString()
    }
    lazy val asString: String = s"$base$pathAndArgs"

    override def toString: String = asString
  }
}

object URL {
  def apply(url: String): URL = apply(url, absolutizePath = true)

  def apply(url: String, absolutizePath: Boolean): URL = {
    val colonIndex1 = url.indexOf(':')
    val protocol = Protocol(url.substring(0, colonIndex1))
    val slashIndex = url.indexOf('/', colonIndex1 + 3)
    val hostAndPort = if (slashIndex == -1) {
      url.substring(colonIndex1 + 3)
    } else {
      url.substring(colonIndex1 + 3, slashIndex)
    }
    val colonIndex2 = hostAndPort.indexOf(':')
    val (host, port) = if (colonIndex2 == -1) {
      hostAndPort -> protocol.defaultPort.getOrElse(throw new RuntimeException(s"Unknown port for $url."))
    } else {
      hostAndPort.substring(0, colonIndex2) -> hostAndPort.substring(colonIndex2 + 1).toInt
    }
    val questionIndex = url.indexOf('?')
    val hashIndex = url.indexOf('#')
    val pathString = if (slashIndex == -1) {
      "/"
    } else if (questionIndex == -1 && hashIndex == -1) {
      url.substring(slashIndex)
    } else if (questionIndex != -1) {
      url.substring(slashIndex, questionIndex)
    } else {
      url.substring(slashIndex, hashIndex)
    }
    val path = Path.parse(pathString, absolutizePath)
    val parameters = if (questionIndex == -1) {
      Map.empty[String, Param]
    } else {
      val endIndex = if (hashIndex == -1) url.length else hashIndex
      val query = url.substring(questionIndex + 1, endIndex)
      var map = Map.empty[String, Param]
      query.split('&').map(param => param.trim.splitAt(param.indexOf('='))).collect {
        case (key, value) if key.nonEmpty => URLUtil.decode(key) -> URLUtil.decode(value.substring(1))
        case (key, value) if value.nonEmpty => "query" -> URLUtil.decode(value)
      }.foreach {
        case (key, value) => map.get(key) match {
          case Some(param) => map += key -> Param(param.values ::: List(value))
          case None => map += key -> Param(List(value))
        }
      }
      map
    }
    val fragment = if (hashIndex != -1) {
      Some(url.substring(hashIndex + 1))
    } else {
      None
    }
    URL(protocol = protocol, host = host, port = port, path = path, parameters = new Parameters(parameters), fragment = fragment)
  }
}