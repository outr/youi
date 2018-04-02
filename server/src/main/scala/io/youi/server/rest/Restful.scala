package io.youi.server.rest

import io.circe.{Decoder, Encoder}
import io.youi.ValidationError
import io.youi.http.{HttpConnection, HttpStatus}
import io.youi.server.handler.HttpHandler

import scala.concurrent.duration.Duration
import scala.concurrent.Future
import scala.language.experimental.macros

trait Restful[Request, Response] {
  def apply(connection: HttpConnection, request: Request): Future[RestfulResponse[Response]]

  def validations: List[RestfulValidation[Request]]

  def error(errors: List[ValidationError], status: HttpStatus): RestfulResponse[Response]

  def timeout: Duration = Duration.Inf

  protected def ok(response: Response): RestfulResponse[Response] = this.response(response, HttpStatus.OK)

  protected def response(response: Response, status: HttpStatus): RestfulResponse[Response] = {
    RestfulResponse(response, status)
  }
}

object Restful {
  def apply[Request, Response](restful: Restful[Request, Response])
                              (implicit decoder: Decoder[Request], encoder: Encoder[Response]): HttpHandler = {
    new RestfulHandler[Request, Response](restful)(decoder, encoder)
  }
}