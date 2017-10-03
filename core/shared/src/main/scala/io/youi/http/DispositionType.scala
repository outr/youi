package io.youi.http

sealed abstract class DispositionType(val value: String)

object DispositionType {
  case object Inline extends DispositionType("inline")
  case object Attachment extends DispositionType("attachment")
  case object FormData extends DispositionType("form-data")
}