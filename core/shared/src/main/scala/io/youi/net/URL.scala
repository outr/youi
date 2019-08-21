package io.youi.net

import io.circe.Decoder.Result
import io.circe.{Decoder, DecodingFailure, Encoder, HCursor, Json}

import scala.reflect.macros.blackbox
import scala.util.matching.Regex
import scala.language.experimental.macros

case class URL(protocol: Protocol = Protocol.Http,
               host: String = "localhost",
               port: Int = 80,
               path: Path = Path.empty,
               parameters: Parameters = Parameters.empty,
               fragment: Option[String] = None) extends Location {
  def replaceBase(base: String): URL = URL(s"$base${encoded.pathAndArgs}")
  def replacePathAndParams(pathAndParams: String): URL = URL(s"$base$pathAndParams")

  def withPart(part: String): URL = if (part.indexOf("://") != -1) {
    URL(part)
  } else if (part.startsWith("?")) {
    copy(parameters = Parameters.parse(part))
  } else if (part.startsWith("/") || part.startsWith("..")) {
    val index = part.indexOf('?')
    if (index == -1) {
      withPath(part).copy(parameters = Parameters.empty)
    } else {
      val path = part.substring(0, index)
      val params = part.substring(index + 1)
      withPath(path).copy(parameters = Parameters.parse(params))
    }
  } else {
    throw new RuntimeException(s"Unable to parse URL part: $part")
  }

  def withPath(path: String, absolutize: Boolean = true): URL = {
    val updated = this.path.append(path).absolute
    copy(path = updated)
  }

  def withPath(path: Path): URL = copy(path = path)

  def withFragment(fragment: String): URL = copy(fragment = Option(fragment))
  def withoutFragment(): URL = copy(fragment = None)

  def withParam(key: String, value: String, append: Boolean = true): URL = {
    copy(parameters = parameters.withParam(key, value, append))
  }
  def withParams(params: Map[String, String], append: Boolean = false): URL = {
    var u = this
    params.foreach {
      case (key, value) => u = u.withParam(key, value, append)
    }
    u
  }
  def appendParam(key: String, value: String): URL = copy(parameters = parameters.appendParam(key, value))
  def replaceParam(key: String, values: List[String]): URL = copy(parameters = parameters.replaceParam(key, values))
  def removeParam(key: String): URL = copy(parameters = parameters.removeParam(key))

  def paramList(key: String): List[String] = parameters.values(key)
  def param(key: String): Option[String] = paramList(key).headOption
  def clearParams(): URL = copy(parameters = Parameters.empty)

  lazy val base: String = {
    val b = new StringBuilder
    b.append(protocol.scheme)
    b.append("://")
    b.append(host)
    if (!protocol.defaultPort.contains(port) && port != -1) {
      b.append(s":$port")       // Not using the default port for the protocol
    }
    b.toString()
  }

  lazy val encoded: URLParts = new URLParts(encoded = true)
  lazy val decoded: URLParts = new URLParts(encoded = false)

  /**
    * Encodes this URL as a complete path. This is primarily useful for caching to a file while avoiding duplicates with
    * the same file name. For example:
    *
    * http://www.example.com/some/path/file.txt
    *
    * Would be encoded to:
    *
    * /www.example.com/some/path/file.txt
    *
    * @param includePort whether the port should be included as a part of the path. Defaults to false.
    */
  def asPath(includePort: Boolean = false): String = if (includePort) {
    s"/$host/$port${path.encoded}"
  } else {
    s"/$host${path.encoded}"
  }

  override def equals(obj: scala.Any): Boolean = obj match {
    case url: URL => url.toString == toString
    case _ => false
  }

  override def toString: String = encoded.asString

  class URLParts(encoded: Boolean) {
    def base: String = URL.this.base
    lazy val pathAndArgs: String = {
      val b = new StringBuilder
      b.append(path)
      b.append(if (encoded) parameters.encoded else parameters.decoded)
      fragment.foreach { f =>
        b.append('#')
        b.append(f)
      }
      b.toString()
    }
    lazy val asString: String = s"$base$pathAndArgs"

    override def toString: String = asString
  }
}

object URL {
  implicit val encoder: Encoder[URL] = new Encoder[URL] {
    override def apply(url: URL): Json = Json.fromString(url.toString)
  }
  implicit val decoder: Decoder[URL] = new Decoder[URL] {
    override def apply(c: HCursor): Result[URL] = c.value.asString match {
      case Some(s) => Right(URL(s))
      case None => Left(DecodingFailure(s"Cannot decode URL from ${c.value}", Nil))
    }
  }

  def build(protocol: String,
            host: String,
            port: Int,
            path: String,
            parameters: List[(String, List[String])],
            fragment: Option[String]): URL = {
    val params = Parameters(parameters.map(t => t._1 -> Param(t._2)))
    URL(Protocol(protocol), host, port, Path.parse(path), params, fragment)
  }

  def apply(url: String): URL = apply(url, absolutizePath = true)

  def get(url: String): Option[URL] = get(url, absolutizePath = true)

  def apply(url: String, absolutizePath: Boolean): URL = get(url, absolutizePath)
    .getOrElse(throw MalformedURLException(s"Unable to parse URL: [$url].", url))

  def get(url: String, absolutizePath: Boolean): Option[URL] = try {
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
      hostAndPort -> protocol.defaultPort.getOrElse(-1)
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
      Parameters.empty
    } else {
      val endIndex = if (hashIndex == -1) url.length else hashIndex
      val query = url.substring(questionIndex + 1, endIndex)
      Parameters.parse(query)
    }
    val fragment = if (hashIndex != -1) {
      Some(url.substring(hashIndex + 1))
    } else {
      None
    }
    Some(URL(protocol = protocol, host = host, port = port, path = path, parameters = parameters, fragment = fragment))
  } catch {
    case t: Throwable => {
      scribe.warn(s"Unable to parse URL [$url]. Exception: ${t.getMessage}", t)
      None
    }
  }

  private val unreservedCharacters = Set('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
    'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
    'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
    '-', '_', '.', '~'
  )

  private val encodedRegex = """%([a-zA-Z0-9]{2})""".r

  def encode(part: String): String = part.map {
    case c if unreservedCharacters.contains(c) => c
    case c => s"%${c.toLong.toHexString.toUpperCase}"
  }.mkString

  def decode(part: String): String = encodedRegex.replaceAllIn(part, (m: Regex.Match) => {
    val code = Integer.parseInt(m.group(1), 16)
    code.toChar.toString
  })

  def interpolate(c: blackbox.Context)(args: c.Expr[Any]*): c.Expr[URL] = {
    import c.universe._

    c.prefix.tree match {
      case Apply(_, List(Apply(_, rawParts))) => {
        val parts = rawParts map { case t @ Literal(Constant(const: String)) => (const, t.pos) }

        val b = new StringBuilder
        parts.zipWithIndex.foreach {
          case ((raw, _), index) => {
            if (index > 0) {
              c.abort(
                c.enclosingPosition,
                "URL interpolation can only contain string literals. Use URL.apply for runtime parsing."
              )
            }
            b.append(raw)
          }
        }
        val url = URL(b.toString())
        val protocol = url.protocol.scheme
        val host = url.host
        val port = url.port
        val path = url.path.decoded
        val parameters = url.parameters.entries.map(t => t._1 -> t._2.values)
        val fragment = url.fragment
        c.Expr[URL](q"URL.build(protocol = $protocol, host = $host, port = $port, path = $path, parameters = $parameters, fragment = $fragment)")
      }
      case _ => c.abort(c.enclosingPosition, "Bad usage of url interpolation.")
    }
  }
}