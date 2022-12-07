package spec

import testy._
import io.youi.ValidationError
import io.youi.client.HttpClient
import io.youi.http._
import io.youi.http.content.Content
import io.youi.net._
import io.youi.server.Server
import io.youi.server.dsl._
import io.youi.server.handler.HttpHandler
import io.youi.server.rest.{Restful, RestfulResponse}
import fabric.rw._
import profig.Profig

import scala.concurrent.Future
import scribe.Execution.global

class UndertowServerSpec extends Spec {
  implicit object FutureAsync extends AsyncSupport[Future[Unit]] {
    override def apply(async: Future[Unit]): Future[Unit] = async
  }

  "UndertowServerSpec" should {
    object server extends Server
    val client = HttpClient.url(url"http://localhost:8080")

    "configure the server" in {
      Profig.initConfiguration()
      server.handler.matcher(path.exact("/test.txt")).wrap(new HttpHandler {
        override def handle(connection: HttpConnection): Future[HttpConnection] = Future.successful {
          connection.modify { response =>
            response.withContent(Content.string("test!", ContentType.`text/plain`))
          }
        }
      })
      server.handler(
        filters(
          path"/test/reverse" / ReverseService,
          path"/test/reverse/:value" / ReverseService,
          path"/test/time" / ServerTimeService
        )
      )
      server.handlers.size should be(2)
    }
    "start the server" async {
      server.start().map { _ =>
        server.isRunning should be(true)
      }
    }
    "receive OK for test.txt" in {
      client
        .path(path"/test.txt")
        .send()
        .map { response =>
          response.status should be(HttpStatus.OK)
          val content = response.content.get.asString
          content should be("test!")
        }
    }
    "receive NotFound for test.html" in {
      client
        .path(path"/test.html")
        .send()
        .map { response =>
          response.status should be(HttpStatus.NotFound)
        }
    }
    "reverse a String with the Restful endpoint via POST" in {
      client
        .path(path"/test/reverse")
        .restful[ReverseRequest, ReverseResponse](ReverseRequest("testing"))
        .map { response =>
          response.errors should be(Nil)
          response.reversed should be(Some("gnitset"))
        }
    }
    "reverse a String with the Restful endpoint via GET" in {
      client
        .path(path"/test/reverse")
        .params("value" -> "testing")
        .call[ReverseResponse]
        .map { response =>
          response.errors should be(Nil)
          response.reversed should be(Some("gnitset"))
        }
    }
    "reverse a String with the Restful endpoint via GET with path-based arg" in {
      client
        .path(path"/test/reverse/testing")
        .call[ReverseResponse]
        .map { response =>
          response.errors should be(Nil)
          response.reversed should be(Some("gnitset"))
        }
    }
    "call a Restful endpoint that takes Unit as the request" in {
      val begin = System.currentTimeMillis()
      client
        .path(path"/test/time")
        .call[Long]
        .map { time =>
          time should be >= begin
        }
    }
    "stop the server" in {
      server.stop()
      server.isRunning should be(false)
    }
  }

  case class ReverseRequest(value: String)

  object ReverseRequest {
    implicit val rw: RW[ReverseRequest] = RW.gen
  }

  case class ReverseResponse(reversed: Option[String], errors: List[ValidationError])

  object ReverseResponse {
    implicit val rw: RW[ReverseResponse] = RW.gen
  }

  object ReverseService extends Restful[ReverseRequest, ReverseResponse] {
    override def apply(connection: HttpConnection, request: ReverseRequest): Future[RestfulResponse[ReverseResponse]] = {
      Future.successful(RestfulResponse(ReverseResponse(Some(request.value.reverse), Nil), HttpStatus.OK))
    }

    override def error(errors: List[ValidationError], status: HttpStatus): RestfulResponse[ReverseResponse] = {
      RestfulResponse(ReverseResponse(None, errors), status)
    }
  }

  object ServerTimeService extends Restful[Unit, Long] {
    override def apply(connection: HttpConnection, request: Unit): Future[RestfulResponse[Long]] = {
      Future.successful(RestfulResponse(System.currentTimeMillis(), HttpStatus.OK))
    }

    override def error(errors: List[ValidationError], status: HttpStatus): RestfulResponse[Long] = {
      RestfulResponse(0L, status)
    }
  }
}
