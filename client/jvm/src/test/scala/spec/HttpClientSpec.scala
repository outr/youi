package spec

import io.youi.client.HttpClient
import io.youi.client.intercept.Interceptor
import io.youi.http.{HttpRequest, HttpStatus, StringContent}
import io.youi.net._
import org.scalatest.{Assertion, AsyncWordSpec, Matchers}

import scala.concurrent.Future
import scala.concurrent.duration._

class HttpClientSpec extends AsyncWordSpec with Matchers {
  "HttpClient" should {
    lazy val client = HttpClient()

    "GET the user-agent" in {
      client.send(HttpRequest(url = url"https://httpbin.org/user-agent")).map { response =>
        response.status should be(HttpStatus.OK)
        val content = response.content.get.asInstanceOf[StringContent]
        content.value should include("user-agent")
      }
    }
    "call a URL multiple times with a rate limiter" in {
      var calls = 0
      val limiter = Interceptor.rateLimited(1.seconds)

      def callMultiple(counter: Int): Future[Unit] = {
        client.send(
          request = HttpRequest(url = url"https://httpbin.org/user-agent"),
          interceptor = limiter
        ).flatMap { response =>
          response.status should be(HttpStatus.OK)
          calls += 1
          if (counter > 0) {
            callMultiple(counter - 1)
          } else {
            Future.successful(())
          }
        }
      }

      val start = System.currentTimeMillis()
      callMultiple(5).flatMap { _ =>
        calls should be(6)
        val elapsed = System.currentTimeMillis() - start
        elapsed should be(5000L +- 750L)
      }
    }
  }
}
