package spec

import io.youi.ValidationError
import io.youi.http._
import io.youi.net._
import io.youi.server.Server
import io.youi.server.dsl._
import io.youi.server.handler.HttpHandler
import io.youi.server.rest.{Restful, RestfulResponse}
import org.scalatest.{Matchers, WordSpec}
import io.circe.generic.auto._
import io.youi.http.content.{Content, StringContent}
import profig.JsonUtil

import scala.concurrent.Future

class ServerSpec extends WordSpec with Matchers {
  Server.config("implementation").store("io.youi.server.test.TestServerImplementation")

  object server extends Server

  "TestHttpApplication" should {
    "configure the TestServer" in {
      server.handler.matcher(path.exact("/test.html")).wrap(new HttpHandler {
        override def handle(connection: HttpConnection): Unit = connection.update { response =>
          response.withContent(Content.string("test!", ContentType.`text/plain`))
        }
      })
    }
    "configure Restful endpoint" in {
      server.handler(
        filters(
          path"/test/reverse" / ReverseService,
          path"/test/reverse/:value" / ReverseService,
          path"/test/time" / ServerTimeService
        )
      )
    }
    "receive OK for test.html" in {
      val connection = new HttpConnection(server, HttpRequest(url = URL("http://localhost/test.html")))
      server.handle(connection)
      connection.response.status should equal(HttpStatus.OK)
    }
    "receive NotFound for other.html" in {
      val connection = new HttpConnection(server, HttpRequest(url = URL("http://localhost/other.html")))
      server.handle(connection)
      connection.response.status should equal(HttpStatus.NotFound)
    }
    "reverse a String with the Restful endpoint via POST" in {
      val content = Content.string(JsonUtil.toJsonString(ReverseRequest("Testing")), ContentType.`application/json`)
      val connection = new HttpConnection(server, HttpRequest(
        method = Method.Post,
        url = URL("http://localhost/test/reverse"),
        content = Some(content)
      ))
      server.handle(connection)
      connection.response.status should equal(HttpStatus.OK)
      connection.response.content shouldNot equal(None)
      val jsonString = connection.response.content.get.asInstanceOf[StringContent].value
      val response = JsonUtil.fromJsonString[ReverseResponse](jsonString)
      response.errors should be(Nil)
      response.reversed should be(Some("gnitseT"))
    }
    "reverse a String with the Restful endpoint via GET" in {
      val connection = new HttpConnection(server, HttpRequest(
        method = Method.Get,
        url = URL("http://localhost/test/reverse?value=Testing")
      ))
      server.handle(connection)
      connection.response.status should equal(HttpStatus.OK)
      connection.response.content shouldNot equal(None)
      val jsonString = connection.response.content.get.asInstanceOf[StringContent].value
      val response = JsonUtil.fromJsonString[ReverseResponse](jsonString)
      response.errors should be(Nil)
      response.reversed should be(Some("gnitseT"))
    }
    "reverse a String with the Restful endpoint via GET with path-based arg" in {
      val connection = new HttpConnection(server, HttpRequest(
        method = Method.Get,
        url = URL("http://localhost/test/reverse/Testing")
      ))
      server.handle(connection)
      connection.response.status should equal(HttpStatus.OK)
      connection.response.content shouldNot equal(None)
      val jsonString = connection.response.content.get.asInstanceOf[StringContent].value
      val response = JsonUtil.fromJsonString[ReverseResponse](jsonString)
      response.errors should be(Nil)
      response.reversed should be(Some("gnitseT"))
    }
    "call a Restful endpoint that takes Unit as the request" in {
      val begin = System.currentTimeMillis()
      val connection = new HttpConnection(server, HttpRequest(
        method = Method.Get,
        url = URL("http://localhost/test/time")
      ))
      server.handle(connection)
      connection.response.status should equal(HttpStatus.OK)
      connection.response.content shouldNot equal(None)
      val jsonString = connection.response.content.get.asInstanceOf[StringContent].value
      val response = JsonUtil.fromJsonString[Long](jsonString)
      response should be >= begin
    }
  }

  case class ReverseRequest(value: String)

  case class ReverseResponse(reversed: Option[String], errors: List[ValidationError])

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