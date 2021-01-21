package io.youi

import io.youi.http.HttpStatus

import profig._

object ValidationError {
  val General: Int = 0
  val RequestParsing: Int = 1
  val Internal: Int = 2

  implicit val rw: ReadWriter[ValidationError] = macroRW
}

case class ValidationError(message: String,
                           code: Int = ValidationError.General,
                           status: HttpStatus = HttpStatus.InternalServerError)