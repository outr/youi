package io.youi.client

import io.circe.{Decoder, Encoder, Json, Printer}
import io.circe.parser.decode
import io.circe.syntax._
import io.youi.http.{Content, Headers, HttpRequest, HttpResponse, Method, StringContent}
import io.youi.net.{ContentType, URL}

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

trait HttpClient {
  def defaultRetry: Int
  def defaultRetryDelay: FiniteDuration
  def connectionPool: ConnectionPool
  def dropNullValues: Boolean = false

  protected lazy val printer: Printer = Printer.spaces2.copy(dropNullValues = dropNullValues)

  def send(request: HttpRequest,
           retry: Int = defaultRetry,
           retryDelay: FiniteDuration = defaultRetryDelay): Future[HttpResponse]

  /**
    * Default error handler for restful bad response statuses.
    *
    * @tparam Response the type of response
    * @return throws a RuntimeException when an error occurs
    */
  protected def defaultErrorHandler[Response]: ErrorHandler[Response] = new ErrorHandler[Response] {
    override def apply(request: HttpRequest, response: HttpResponse, throwable: Option[Throwable]): Response = throwable match {
      case Some(t) => throw new RuntimeException(s"Error from server: ${response.status.message} (${response.status.code}) for ${request.url}.", t)
      case None => throw new RuntimeException(s"Error from server: ${response.status.message} (${response.status.code}) for ${request.url}.")
    }
  }

  /**
    * Builds on the send method by supporting basic restful calls that take a case class as the request and returns a
    * case class as the response.
    *
    * @param url the URL of the endpoint
    * @param request the request instance
    * @param headers the headers if any to provide
    * @param errorHandler error handling support if the response status is not Success
    * @param encoder circe encoding of the Request
    * @param decoder circe decoding of the Response
    * @tparam Request the request type
    * @tparam Response the response type
    * @return Future[Response]
    */
  def restful[Request, Response](url: URL,
                                 request: Request,
                                 headers: Headers = Headers.empty,
                                 errorHandler: ErrorHandler[Response] = defaultErrorHandler[Response],
                                 method: Method = Method.Post,
                                 processor: Json => Json = (json: Json) => json,
                                 retry: Int = defaultRetry,
                                 retryDelay: FiniteDuration = defaultRetryDelay)
                                (implicit encoder: Encoder[Request], decoder: Decoder[Response]): Future[Response] = {
    val requestJson = printer.pretty(processor(request.asJson))
    val httpRequest = HttpRequest(
      method = method,
      url = url,
      headers = headers,
      content = Some(StringContent(requestJson, ContentType.`application/json`))
    )
    send(httpRequest, retry, retryDelay).map { response =>
      val responseJson = response.content.map(content2String).getOrElse("")
      if (responseJson.isEmpty) throw new RuntimeException(s"No content received in response for $url.")
      decode[Response](responseJson) match {
        case Left(error) => errorHandler(httpRequest, response, Some(error))
        case Right(result) => result
      }
    }
  }

  protected def content2String(content: Content): String

  /**
    * Builds on the send method by supporting basic restful calls that calls a URL and returns a case class as the
    * response.
    *
    * @param url the URL of the endpoint
    * @param headers the headers if any to provide
    * @param errorHandler error handling support if the response status is not Success
    * @param decoder circe decoding of the Response
    * @tparam Response the response type
    * @return Future[Response]
    */
  def call[Response](url: URL,
                     method: Method = Method.Get,
                     headers: Headers = Headers.empty,
                     errorHandler: ErrorHandler[Response] = defaultErrorHandler[Response],
                     retry: Int = defaultRetry,
                     retryDelay: FiniteDuration = defaultRetryDelay)
                    (implicit decoder: Decoder[Response]): Future[Response] = {
    val request = HttpRequest(
      method = method,
      url = url,
      headers = headers
    )
    send(request, retry, retryDelay).map { response =>
      val responseJson = response.content.map(content2String).getOrElse("")
      if (response.status.isSuccess) {
        if (responseJson.isEmpty) throw new RuntimeException(s"No content received in response for $url.")
        decode[Response](responseJson) match {
          case Left(error) => errorHandler(request, response, Some(error))
          case Right(result) => result
        }
      } else {
        errorHandler(request, response, None)
      }
    }
  }
}

object HttpClient {
  var retries: Int = 0
  var retryDelay: FiniteDuration = 5.seconds
  var connectionPool: ConnectionPool = ConnectionPool.default

  def apply(defaultRetry: Int = retries,
            defaultRetryDelay: FiniteDuration = retryDelay,
            connectionPool: ConnectionPool = connectionPool): HttpClient = {
    ClientPlatform.createClient(defaultRetry, defaultRetryDelay, connectionPool)
  }
}