package io.youi.http.cookie

sealed trait SameSite

object SameSite {
  case object Normal extends SameSite
  case object Lax extends SameSite
  case object Strict extends SameSite
}