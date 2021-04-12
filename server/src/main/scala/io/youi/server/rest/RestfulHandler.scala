package io.youi.server.rest

import io.youi.ValidationError
import io.youi.http.HttpConnection
import io.youi.http.content.{Content, StringContent}
import io.youi.net.{ContentType, URL}
import io.youi.server.dsl.PathFilter
import io.youi.server.handler.HttpHandler
import fabric.rw._
import scribe.Execution.global

import scala.collection.mutable.ListBuffer
import scala.concurrent.Future

class RestfulHandler[Request, Response](restful: Restful[Request, Response])
                                       (implicit reader: Reader[Request], writer: Writer[Response]) extends HttpHandler {
  override def handle(connection: HttpConnection): Future[HttpConnection] = {
    // Build JSON
    val future: Future[RestfulResponse[Response]] = RestfulHandler.jsonFromConnection(connection) match {
      case Left(err) => {
        Future.successful(restful.error(List(err), err.status))
      }
      case Right(json) => {
        // Decode request
        val req = json.as[Request]
        // Validations
        RestfulHandler.validate(req, restful.validations) match {
          case Left(errors) => {
            val status = errors.map(_.status).max
            Future.successful(restful.error(errors, status))
          }
          case Right(request) => try {
            restful(connection, request)
          } catch {
            case t: Throwable => {
              val err = ValidationError(s"Error while calling restful: ${t.getMessage}", ValidationError.Internal)
              Future.successful(restful.error(List(err), err.status))
            }
          }
        }
      }
    }

    future.map { result =>
      // Encode response
      val responseJsonString = JsonUtil.toJsonString(result.response)

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

  def store(connection: HttpConnection, json: Json): Unit = {
    val merged = Json.merge(connection.store.getOrElse[Json](key, Json.obj()), json)
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
      case Right(json) => Right(Json.merge(json, urlJson, pathJson, storeJson))
    }
  }

  def jsonFromContent(content: Content): Either[ValidationError, Json] = content match {
    case StringContent(jsonString, _, _) => {
      val json = Json.parse(jsonString)
      Right(json)
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
          Json.array(values.map(Json.string): _*)
        } else {
          Json.string(values.head)
        }
        key -> valuesJson
      }
    }
    Json.obj(entries: _*)
  }
}