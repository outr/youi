package io.youi.net

case class URLParseFailure(message: String, failureType: URLParseFailure.Type, cause: Option[Throwable] = None)

object URLParseFailure {
  sealed trait Type

  case object QuickFail extends Type
  case object InvalidHost extends Type
  case object EmailAddress extends Type
  case object InvalidTopLevelDomain extends Type
  case object Exception extends Type
}