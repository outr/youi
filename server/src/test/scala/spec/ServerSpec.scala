package spec

import cats.effect.IO
import cats.effect.testing.scalatest.AsyncIOSpec
import fabric.parse.JsonParser
import io.youi.ValidationError
import io.youi.http._
import io.youi.http.content.{Content, StringContent}
import io.youi.net._
import io.youi.server.Server
import io.youi.server.dsl._
import io.youi.server.handler.HttpHandler
import io.youi.server.rest.{Restful, RestfulResponse}
import fabric.rw._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AsyncWordSpec

class ServerSpec extends AsyncWordSpec with AsyncIOSpec with Matchers {
  Server.config("implementation").store("io.youi.server.test.TestServerImplementation")

  object server extends Server

  "TestHttpApplication" should {
    "configure the TestServer" in {
      server.handler.matcher(path.exact("/test.html")).wrap(new HttpHandler {
        override def handle(connection: HttpConnection): IO[HttpConnection] = IO {
          connection.modify { response =>
            response.withContent(Content.string("test!", ContentType.`text/plain`))
          }
        }
      })
      server.handlers.size should be(1)
    }
    "configure Restful endpoint" in {
      server.handler(
        filters(
          path"/test/reverse" / ReverseService,
          path"/test/reverse/:value" / ReverseService,
          path"/test/time" / ServerTimeService
        )
      )
      server.handlers.size should be(2)
    }
    "receive OK for test.html" in {
      server.handle(HttpConnection(server, HttpRequest(url = URL("http://localhost/test.html")))).map { connection =>
        connection.response.status should be(HttpStatus.OK)
      }
    }
    "receive NotFound for other.html" in {
      server.handle(HttpConnection(server, HttpRequest(url = URL("http://localhost/other.html")))).map { connection =>
        connection.response.status should be(HttpStatus.NotFound)
      }
    }
    "reverse a String with the Restful endpoint via POST" in {
      val content = Content.json(ReverseRequest("Testing").json)
      server.handle(HttpConnection(server, HttpRequest(
        method = HttpMethod.Post,
        url = URL("http://localhost/test/reverse"),
        content = Some(content)
      ))).map { connection =>
        connection.response.status should be(HttpStatus.OK)
        val jsonString = connection.response.content.get.asInstanceOf[StringContent].value
        val response = JsonParser.parse(jsonString).as[ReverseResponse]
        response.errors should be(Nil)
        response.reversed should be(Some("gnitseT"))
      }
    }
    "reverse a String with the Restful endpoint via GET" in {
      server.handle(HttpConnection(server, HttpRequest(
        method = HttpMethod.Get,
        url = URL("http://localhost/test/reverse?value=Testing")
      ))).map { connection =>
        connection.response.status should be(HttpStatus.OK)
        val jsonString = connection.response.content.get.asInstanceOf[StringContent].value
        val response = JsonParser.parse(jsonString).as[ReverseResponse]
        response.errors should be(Nil)
        response.reversed should be(Some("gnitseT"))
      }
    }
    "reverse a String with the Restful endpoint via GET with path-based arg" in {
      server.handle(HttpConnection(server, HttpRequest(
        method = HttpMethod.Get,
        url = URL("http://localhost/test/reverse/Testing")
      ))).map { connection =>
        connection.response.status should be(HttpStatus.OK)
        val jsonString = connection.response.content.get.asInstanceOf[StringContent].value
        val response = JsonParser.parse(jsonString).as[ReverseResponse]
        response.errors should be(Nil)
        response.reversed should be(Some("gnitseT"))
      }
    }
    "call a Restful endpoint that takes Unit as the request" in {
      val begin = System.currentTimeMillis()
      server.handle(HttpConnection(server, HttpRequest(
        method = HttpMethod.Get,
        url = URL("http://localhost/test/time")
      ))).map { connection =>
        connection.response.status should be(HttpStatus.OK)
        val jsonString = connection.response.content.get.asInstanceOf[StringContent].value
        val response = JsonParser.parse(jsonString).as[Long]
        response should be >= begin
      }
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
    override def apply(connection: HttpConnection, request: ReverseRequest): IO[RestfulResponse[ReverseResponse]] = {
      IO.pure(RestfulResponse(ReverseResponse(Some(request.value.reverse), Nil), HttpStatus.OK))
    }

    override def error(errors: List[ValidationError], status: HttpStatus): RestfulResponse[ReverseResponse] = {
      RestfulResponse(ReverseResponse(None, errors), status)
    }
  }

  object ServerTimeService extends Restful[Unit, Long] {
    override def apply(connection: HttpConnection, request: Unit): IO[RestfulResponse[Long]] = {
      IO.pure(RestfulResponse(System.currentTimeMillis(), HttpStatus.OK))
    }

    override def error(errors: List[ValidationError], status: HttpStatus): RestfulResponse[Long] = {
      RestfulResponse(0L, status)
    }
  }
}