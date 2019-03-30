package spec

import io.youi.client.HttpClient
import io.youi.client.intercept.Interceptor
import io.youi.http.content.StringContent
import io.youi.http.HttpStatus
import io.youi.net._
import org.scalatest.{AsyncWordSpec, Matchers}

import scala.concurrent.Future
import scala.concurrent.duration._

class HttpClientSpec extends AsyncWordSpec with Matchers {
  "HttpClient" should {
    "GET the user-agent" in {
      HttpClient.url(url"https://httpbin.org/user-agent").get.send().map { response =>
        response.status should be(HttpStatus.OK)
        val content = response.content.get.asInstanceOf[StringContent]
        content.value should include("user-agent")
      }
    }
    "call a URL multiple times with a rate limiter" in {
      var calls = 0
      val limiter = Interceptor.rateLimited(1.seconds)
      def callMultiple(counter: Int): Future[Unit] = {
        HttpClient.interceptor(limiter).url(url"https://httpbin.org/user-agent").get.send().flatMap { response =>
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
        elapsed should be(5000L +- 1000L)
      }
    }
    "call a URL and get a case class back" in {
      HttpClient.url(url"https://jsonplaceholder.typicode.com/todos/1").get.call[Placeholder].map { p =>
        p.userId should be(1)
        p.id should be(1)
        p.title should be("delectus aut autem")
        p.completed should be(false)
      }
    }
    "restful call to a URL" in {
      HttpClient
        .url(url"https://jsonplaceholder.typicode.com/posts")
        .restful[Placeholder, Placeholder](Placeholder(123, 456, "Test YouI", completed = false)).map { p =>
        p.userId should be(123)
        p.id should be(101)
        p.title should be("Test YouI")
        p.completed should be(false)
      }
    }
  }
}

case class Placeholder(userId: Int, id: Int, title: String, completed: Boolean)