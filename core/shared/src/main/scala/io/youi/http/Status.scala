package io.youi.http

class Status private(val code: Int, val message: String) {
  Status.synchronized {
    Status.codeMap += code -> this
  }

  def isInformation: Boolean = code >= 100 && code < 200
  def isSuccess: Boolean = code >= 200 && code < 300
  def isRedirection: Boolean = code >= 300 && code < 400
  def isClientError: Boolean = code >= 400 && code < 500
  def isServerError: Boolean = code >= 500

  def isError: Boolean = isClientError || isServerError

  def apply(message: String) = new Status(code, message)

  override def equals(obj: scala.Any): Boolean = obj match {
    case that: Status => code == that.code
    case _ => false
  }

  override def toString = s"HttpResponseStatus($code: $message)"
}

object Status {
  private var codeMap = Map.empty[Int, Status]

  val Continue = new Status(100, "Continue")
  val SwitchingProtocols = new Status(101, "Switching Protocols")
  val Processing = new Status(102, "Processing")

  val OK = new Status(200, "OK")
  val Created = new Status(201, "Created")
  val Accepted = new Status(202, "Accepted")
  val NonAuthoritativeInformation = new Status(203, "Non-Authoritative Information")
  val NoContent = new Status(204, "No Content")
  val ResetContent = new Status(205, "Reset Content")
  val PartialContent = new Status(206, "Partial Content")
  val MultiStatus = new Status(207, "Multi-Status")

  val MultipleChoices = new Status(300, "Multiple Choices")
  val MovedPermanently = new Status(301, "Moved Permanently")
  val Found = new Status(302, "Found")
  val SeeOther = new Status(303, "See Other")
  val NotModified = new Status(304, "Not Modified")
  val UseProxy = new Status(305, "Use Proxy")
  val TemporaryRedirect = new Status(307, "Temporary Redirect")

  val BadRequest = new Status(400, "Bad Request")
  val Unauthorized = new Status(401, "Unauthorized")
  val PaymentRequired = new Status(402, "Payment Required")
  val Forbidden = new Status(403, "Forbidden")
  val NotFound = new Status(404, "Not Found")
  val MethodNotAllowed = new Status(405, "Method Not Allowed")
  val NotAcceptable = new Status(406, "Not Acceptable")
  val ProxyAuthenticationRequired = new Status(407, "Proxy Authentication Required")
  val RequestTimeout = new Status(408, "Request Timeout")
  val Conflict = new Status(409, "Conflict")
  val Gone = new Status(410, "Gone")
  val LengthRequired = new Status(411, "Length Required")
  val PreconditionFailed = new Status(412, "Precondition Failed")
  val RequestEntityTooLarge = new Status(413, "Request Entity Too Large")
  val RequestURITooLong = new Status(414, "Request-URI Too Long")
  val UnsupportedMediaType = new Status(415, "Unsupported Media Type")
  val RequestedRangeNotSatisfiable = new Status(416, "Requested Range Not Satisfiable")
  val ExpectationFailed = new Status(417, "Expectation Failed")
  val UnprocessableEntity = new Status(422, "Unprocessable Entity")
  val Locked = new Status(423, "Locked")
  val FailedDependency = new Status(424, "Failed Dependency")
  val UnorderedCollection = new Status(425, "Unordered Collection")
  val UpgradeRequired = new Status(426, "Upgrade Required")
  val PreconditionRequired = new Status(428, "Precondition Required")
  val TooManyRequests = new Status(429, "Too Many Requests")
  val RequestHeaderFieldsTooLarge = new Status(431, "Request Header Fields Too Large")

  val InternalServerError = new Status(500, "Internal Server Error")
  val NotImplemented = new Status(501, "Not Implemented")
  val BadGateway = new Status(502, "Bad Gateway")
  val ServiceUnavailable = new Status(503, "Service Unavailable")
  val GatewayTimeout = new Status(504, "Gateway Timeout")
  val HTTPVersionNotSupported = new Status(505, "HTTP Version Not Supported")
  val VariantAlsoNegotiates = new Status(506, "Variant Also Negotiates")
  val InsufficientStorage = new Status(507, "Insufficient Storage")
  val NotExtended = new Status(510, "Not Extended")
  val NetworkAuthenticationRequired = new Status(511, "Network Authentication Required")

  def getByCode(code: Int): Option[Status] = codeMap.get(code)
  def byCode(code: Int): Status = getByCode(code).getOrElse(throw new RuntimeException(s"Unable to find HttpResponseStatus by code: $code"))

  def apply(code: Int, message: String): Status = new Status(code, message)
}
