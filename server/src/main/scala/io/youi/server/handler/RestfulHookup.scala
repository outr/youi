package io.youi.server.handler
import com.outr.hookup.{Hookup, HookupServer}
import io.circe.Json
import io.circe.parser._
import io.youi.http.content.Content
import io.youi.http.{HttpConnection, HttpRequest}
import io.youi.net.{ContentType, Path}

import scala.concurrent.Future
import scribe.Execution.global

class RestfulHookup[H <: Hookup](hookup: HookupServer[H], prefixes: (Class[_], String)*) extends HttpHandler {
  private val prefixMap = prefixes.map {
    case (c, p) => c.getName -> p
  }.toMap
  private val instance = hookup("restful")
  private val endpoints = instance.callables.values.flatMap { h =>
    val prefix = prefixMap.get(h.interfaceName).map(_.split('.').toList).getOrElse(Nil)
    h.callables.values.map { callable =>
      val pathList = prefix ::: List(callable.name)
      val path = Path.parse(pathList.mkString("/", "/", "/"))
      path -> callable
    }
  }.toMap

  override def handle(connection: HttpConnection): Future[HttpConnection] = {
    endpoints.get(connection.request.url.path) match {
      case Some(callable) => {
        val request = request2JSON(connection.request)
        callable.call(request).map { response =>
          connection.modify(_.withContent(Content.json(response, pretty = true)))
        }
      }
      case None => Future.successful(connection)
    }
  }

  private def request2JSON(request: HttpRequest): Json = {
    val params = Json.obj(request.url.parameters.map.toList.map {
      case (key, param) => key -> (param.values match {
        case entry :: Nil => Json.fromString(entry)
        case entries => Json.arr(entries.map(Json.fromString): _*)
      })
    }: _*)
    val body = request.content.map {
      case content if content.contentType == ContentType.`application/json` => parse(content.asString) match {
        case Left(pf) => throw pf
        case Right(json) => json
      }
      case content => throw new RuntimeException(s"Unsupported content-type: ${content.contentType}")
    }.getOrElse(Json.obj())
    body.deepMerge(params)
  }
}
