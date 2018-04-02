package io.youi.server.rest

import io.youi.ValidationError

trait RestfulValidation[Request] {
  def validate(request: Request): Either[ValidationError, Request]
}

object RestfulValidation {
  def nonEmpty[Request, T](f: Request => Option[T],
                           error: => ValidationError): RestfulValidation[Request] = apply(r => f(r).isEmpty, error)

  def apply[Request, T](f: Request => Boolean, error: => ValidationError): RestfulValidation[Request] = {
    new RestfulValidation[Request] {
      override def validate(request: Request): Either[ValidationError, Request] = {
        if (f(request)) {
          Right(request)
        } else {
          Left(error)
        }
      }
    }
  }
}