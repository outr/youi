package io.youi

import fabric.rw._
import spice.http.HttpStatus

object ValidationError {
  val General: Int = 0
  val RequestParsing: Int = 1
  val Internal: Int = 2

  implicit val rw: RW[ValidationError] = RW.gen
}

case class ValidationError(message: String,
                           code: Int = ValidationError.General,
                           status: HttpStatus = HttpStatus.InternalServerError)