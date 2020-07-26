package io.youi.net

case class URLParseFailure(message: String, failureCode: Int, cause: Option[Throwable] = None)

object URLParseFailure {
  val QuickFail: Int = 1
  val InvalidHost: Int = 2
  val EmailAddress: Int = 3
  val InvalidTopLevelDomain: Int = 4
  val Exception: Int = 5
}