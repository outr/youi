package io.youi.server.rest

import io.circe.parser._
import io.circe.syntax._
import io.circe.{Decoder, Encoder, Json, Printer}
import io.youi.http.{Content, HttpConnection, Method, StringContent}
import io.youi.net.ContentType
import io.youi.server.handler.HttpHandler

import scala.concurrent.{Await, Future}

class RestfulHandler[Request, Response](restful: Restful[Request, Response])
                                       (implicit decoder: Decoder[Request], encoder: Encoder[Response]) extends HttpHandler {
  override def handle(connection: HttpConnection): Unit = {
    // Build JSON
    val json: Json = (connection.request.method match {
      case Method.Get => {
        Some(Json.obj(connection.request.url.parameters.entries.map {
          case (key, param) => key -> Json.fromString(param.value)
        }: _*))
      }
      case _ => connection.request.content match {
        case Some(content) => content match {
          case StringContent(jsonString, _, _) => parse(jsonString) match {
            case Left(failure) => {
              scribe.warn(s"Failed to parse JSON: $jsonString", failure)
              None
            }
            case Right(j) => Some(j)
          }
          case _ => {
            scribe.error(s"Unsupported content for restful end-point: $content.")
            None
          }
        }
        case None => None     // Ignore calls to this end-point that have no content
      }
    }).getOrElse(Json.obj())

    // Decode request
    val future: Future[RestfulResponse[Response]] = json.as[Request] match {
      case Left(error) => Future.successful(restful.error(s"Error parsing $json", Some(error)))
      case Right(request) => restful(connection, request)
    }

    // Await result
    // TODO: add asynchronous HttpHandler support
    val result = Await.result(future, restful.timeout)

    // Encode response
    val responseJson = result.response.asJson
    val responseJsonString = RestfulHandler.printer.pretty(responseJson)

    // Attach content
    connection.update { httpResponse =>
      httpResponse
        .withContent(Content.string(responseJsonString, ContentType.`application/json`))
        .withStatus(result.status)
    }
  }
}

object RestfulHandler {
  private lazy val printer = Printer.spaces2.copy(dropNullValues = false)
}