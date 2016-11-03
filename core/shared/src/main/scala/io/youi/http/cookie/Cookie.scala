package io.youi.http.cookie

sealed trait Cookie {
  def name: String
  def value: String
}

case class RequestCookie(name: String, value: String) extends Cookie

case class ResponseCookie(name: String,
                          value: String,
                          expires: Option[Long] = None,
                          maxAge: Option[Long] = None,
                          domain: Option[String] = None,
                          path: String = "/",
                          secure: Boolean = false,
                          httpOnly: Boolean = false) extends Cookie