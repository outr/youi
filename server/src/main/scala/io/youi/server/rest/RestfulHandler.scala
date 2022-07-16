package io.youi.server.rest

import cats.effect.IO
import fabric._
import fabric.parse.Json
import io.youi.ValidationError
import io.youi.http.HttpConnection
import io.youi.http.content.{Content, StringContent}
import io.youi.net.{ContentType, URL}
import io.youi.server.dsl.PathFilter
import io.youi.server.handler.HttpHandler
import fabric.rw._
import scribe.Execution.global

import scala.collection.mutable.ListBuffer

class RestfulHandler[Request, Response](restful: Restful[Request, Response])
                                       (implicit writer: Writer[Request], reader: Reader[Response]) extends HttpHandler {
  override def handle(connection: HttpConnection): IO[HttpConnection] = {
    // Build JSON
    val io: IO[RestfulResponse[Response]] = RestfulHandler.jsonFromConnection(connection) match {
      case Left(err) => {
        IO.pure(restful.error(List(err), err.status))
      }
      case Right(json) => {
        // Decode request
        val req = json.as[Request]
        // Validations
        RestfulHandler.validate(req, restful.validations) match {
          case Left(errors) => {
            val status = errors.map(_.status).max
            IO.pure(restful.error(errors, status))
          }
          case Right(request) => try {
            restful(connection, request)
          } catch {
            case t: Throwable => {
              val err = ValidationError(s"Error while calling restful: ${t.getMessage}", ValidationError.Internal)
              IO.pure(restful.error(List(err), err.status))
            }
          }
        }
      }
    }

    io.map { result =>
      // Encode response
      val responseJsonString = Json.format(result.response.toValue)

      // Attach content
      connection.modify { httpResponse =>
        httpResponse
          .withContent(Content.string(responseJsonString, ContentType.`application/json`))
          .withStatus(result.status)
      }
    }
  }
}

object RestfulHandler {
  private val key: String = "restful"

  def store(connection: HttpConnection, json: Value): Unit = {
    val merged = Value.merge(connection.store.getOrElse[Value](key, obj()), json)
    connection.store.update[Value](key, merged)
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

  def jsonFromConnection(connection: HttpConnection): Either[ValidationError, Value] = {
    val request = connection.request
    val contentJson = request.content.map(jsonFromContent).getOrElse(Right(obj()))
    val urlJson = jsonFromURL(request.url)
    val pathJson = PathFilter.argumentsFromConnection(connection).toValue
    val storeJson = connection.store.getOrElse[Value](key, obj())
    contentJson match {
      case Left(err) => Left(err)
      case Right(json) => Right(Value.merge(json, urlJson, pathJson, storeJson))
    }
  }

  def jsonFromContent(content: Content): Either[ValidationError, Value] = Right(Json.parse(content.asString))

  def jsonFromURL(url: URL): Value = {
    val entries = url.parameters.map.toList.map {
      case (key, param) => {
        val values = param.values
        val valuesJson = if (values.length > 1) {
          arr(values.map(str): _*)
        } else {
          str(values.head)
        }
        key -> valuesJson
      }
    }
    obj(entries: _*)
  }
}