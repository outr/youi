package io.youi.server.rest

import io.circe.parser._
import io.circe.syntax._
import io.circe.{Decoder, Encoder, Json, Printer}
import io.youi.ValidationError
import io.youi.http.{Content, HttpConnection, HttpRequest, StringContent}
import io.youi.net.{ContentType, URL}
import io.youi.server.dsl.PathFilter
import io.youi.server.handler.HttpHandler
import profig.JsonUtil

import scala.collection.mutable.ListBuffer
import scala.concurrent.Await

class RestfulHandler[Request, Response](restful: Restful[Request, Response])
                                       (implicit decoder: Decoder[Request], encoder: Encoder[Response]) extends HttpHandler {
  override def handle(connection: HttpConnection): Unit = {
    // Build JSON
    val result: RestfulResponse[Response] = RestfulHandler.jsonFromConnection(connection) match {
      case Left(err) => {
        restful.error(List(err), err.status)
      }
      case Right(json) => {
        // Decode request
        json.as[Request] match {
          case Left(error) => {
            val err = ValidationError(s"Error parsing ${error.getMessage()}: $json", ValidationError.RequestParsing)
            restful.error(List(err), err.status)
          }
          case Right(req) => {
            // Validations
            RestfulHandler.validate(req, restful.validations) match {
              case Left(errors) => {
                val status = errors.map(_.status).max
                restful.error(errors, status)
              }
              case Right(request) => try {
                val future = restful(connection, request)

                // Await result
                // TODO: add asynchronous HttpHandler support
                Await.result(future, restful.timeout)
              } catch {
                case t: Throwable => {
                  val err = ValidationError(s"Error while calling restful: ${t.getMessage}", ValidationError.Internal)
                  restful.error(List(err), err.status)
                }
              }
            }
          }
        }
      }
    }

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
  private val key: String = "restful"
  private lazy val printer = Printer.spaces2.copy(dropNullValues = false)

  def store(connection: HttpConnection, json: Json): Unit = {
    val merged = connection.store.getOrElse[Json](key, Json.obj()).deepMerge(json)
    connection.store.update[Json](key, merged)
  }

  def validate[Request](request: Request,
                        validations: List[RestfulValidation[Request]]): Either[List[ValidationError], Request] = {
    var errors = ListBuffer.empty[ValidationError]
    var r: Request = request
    validations.foreach { v =>
      v.validate(r) match {
        case Left(err) => errors += err
        case Right(req) => r = req
      }
    }
    if (errors.nonEmpty) {
      Left(errors.toList)
    } else {
      Right(r)
    }
  }

  def jsonFromConnection(connection: HttpConnection): Either[ValidationError, Json] = {
    val request = connection.request
    val contentJson = request.content.map(jsonFromContent).getOrElse(Right(Json.obj()))
    val urlJson = jsonFromURL(request.url)
    val pathJson = JsonUtil.toJson(PathFilter.argumentsFromConnection(connection))
    val storeJson = connection.store.getOrElse[Json](key, Json.obj())
    contentJson match {
      case Left(err) => Left(err)
      case Right(json) => Right(json.deepMerge(urlJson).deepMerge(pathJson).deepMerge(storeJson))
    }
  }

  def jsonFromContent(content: Content): Either[ValidationError, Json] = content match {
    case StringContent(jsonString, _, _) => parse(jsonString) match {
      case Left(failure) => {
        Left(ValidationError(s"Failed to parse JSON (${failure.getMessage()}): $jsonString", ValidationError.RequestParsing))
      }
      case Right(j) => Right(j)
    }
    case _ => {
      Left(ValidationError(s"Unsupported content for restful end-point: $content.", ValidationError.RequestParsing))
    }
  }

  def jsonFromURL(url: URL): Json = {
    val entries = url.parameters.map.toList.map {
      case (key, param) => {
        val values = param.values
        val valuesJson = if (values.length > 1) {
          Json.arr(values.map(Json.fromString): _*)
        } else {
          Json.fromString(values.head)
        }
        key -> valuesJson
      }
    }
    Json.obj(entries: _*)
  }
}