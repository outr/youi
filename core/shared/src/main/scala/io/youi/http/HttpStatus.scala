package io.youi.http

import fabric.rw._

case class HttpStatus(code: Int, message: String) extends Ordered[HttpStatus] {
  HttpStatus.synchronized {
    HttpStatus.codeMap += code -> this
  }

  def isInformation: Boolean = code >= 100 && code < 200
  def isSuccess: Boolean = code >= 200 && code < 300
  def isRedirection: Boolean = code >= 300 && code < 400
  def isClientError: Boolean = code >= 400 && code < 500
  def isServerError: Boolean = code >= 500

  def isError: Boolean = isClientError || isServerError

  def apply(message: String): HttpStatus = copy(message = message)

  override def equals(obj: scala.Any): Boolean = obj match {
    case that: HttpStatus => code == that.code
    case _ => false
  }

  override def compare(that: HttpStatus): Int = this.code.compare(that.code)

  override def toString = s"HttpResponseStatus($code: $message)"
}

object HttpStatus {
  private var codeMap = Map.empty[Int, HttpStatus]

  implicit val rw: ReaderWriter[HttpStatus] = ccRW

  val Continue = new HttpStatus(100, "Continue")
  val SwitchingProtocols = new HttpStatus(101, "Switching Protocols")
  val Processing = new HttpStatus(102, "Processing")

  val OK = new HttpStatus(200, "OK")
  val Created = new HttpStatus(201, "Created")
  val Accepted = new HttpStatus(202, "Accepted")
  val NonAuthoritativeInformation = new HttpStatus(203, "Non-Authoritative Information")
  val NoContent = new HttpStatus(204, "No Content")
  val ResetContent = new HttpStatus(205, "Reset Content")
  val PartialContent = new HttpStatus(206, "Partial Content")
  val MultiStatus = new HttpStatus(207, "Multi-Status")

  val MultipleChoices = new HttpStatus(300, "Multiple Choices")
  val MovedPermanently = new HttpStatus(301, "Moved Permanently")
  val Found = new HttpStatus(302, "Found")
  val SeeOther = new HttpStatus(303, "See Other")
  val NotModified = new HttpStatus(304, "Not Modified")
  val UseProxy = new HttpStatus(305, "Use Proxy")
  val TemporaryRedirect = new HttpStatus(307, "Temporary Redirect")

  val BadRequest = new HttpStatus(400, "Bad Request")
  val Unauthorized = new HttpStatus(401, "Unauthorized")
  val PaymentRequired = new HttpStatus(402, "Payment Required")
  val Forbidden = new HttpStatus(403, "Forbidden")
  val NotFound = new HttpStatus(404, "Not Found")
  val MethodNotAllowed = new HttpStatus(405, "Method Not Allowed")
  val NotAcceptable = new HttpStatus(406, "Not Acceptable")
  val ProxyAuthenticationRequired = new HttpStatus(407, "Proxy Authentication Required")
  val RequestTimeout = new HttpStatus(408, "Request Timeout")
  val Conflict = new HttpStatus(409, "Conflict")
  val Gone = new HttpStatus(410, "Gone")
  val LengthRequired = new HttpStatus(411, "Length Required")
  val PreconditionFailed = new HttpStatus(412, "Precondition Failed")
  val RequestEntityTooLarge = new HttpStatus(413, "Request Entity Too Large")
  val RequestURITooLong = new HttpStatus(414, "Request-URI Too Long")
  val UnsupportedMediaType = new HttpStatus(415, "Unsupported Media Type")
  val RequestedRangeNotSatisfiable = new HttpStatus(416, "Requested Range Not Satisfiable")
  val ExpectationFailed = new HttpStatus(417, "Expectation Failed")
  val UnprocessableEntity = new HttpStatus(422, "Unprocessable Entity")
  val Locked = new HttpStatus(423, "Locked")
  val FailedDependency = new HttpStatus(424, "Failed Dependency")
  val UnorderedCollection = new HttpStatus(425, "Unordered Collection")
  val UpgradeRequired = new HttpStatus(426, "Upgrade Required")
  val PreconditionRequired = new HttpStatus(428, "Precondition Required")
  val TooManyRequests = new HttpStatus(429, "Too Many Requests")
  val RequestHeaderFieldsTooLarge = new HttpStatus(431, "Request Header Fields Too Large")

  val InternalServerError = new HttpStatus(500, "Internal Server Error")
  val NotImplemented = new HttpStatus(501, "Not Implemented")
  val BadGateway = new HttpStatus(502, "Bad Gateway")
  val ServiceUnavailable = new HttpStatus(503, "Service Unavailable")
  val GatewayTimeout = new HttpStatus(504, "Gateway Timeout")
  val HTTPVersionNotSupported = new HttpStatus(505, "HTTP Version Not Supported")
  val VariantAlsoNegotiates = new HttpStatus(506, "Variant Also Negotiates")
  val InsufficientStorage = new HttpStatus(507, "Insufficient Storage")
  val NotExtended = new HttpStatus(510, "Not Extended")
  val NetworkAuthenticationRequired = new HttpStatus(511, "Network Authentication Required")

  def getByCode(code: Int): Option[HttpStatus] = codeMap.get(code)
  def byCode(code: Int): HttpStatus = getByCode(code).getOrElse(throw new RuntimeException(s"Unable to find HttpResponseStatus by code: $code"))
}
