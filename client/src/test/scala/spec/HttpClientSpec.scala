package spec

import io.youi.client.HttpClient
import io.youi.http.{HttpRequest, HttpStatus, StringContent}
import io.youi.net._
import org.scalatest.{AsyncWordSpec, Matchers}

class HttpClientSpec extends AsyncWordSpec with Matchers {
  "HttpClient" should {
    lazy val client = new HttpClient

    "GET the user-agent" in {
      client.send(HttpRequest(url = url"https://httpbin.org/user-agent")).map { response =>
        response.status should be(HttpStatus.OK)
        val content = response.content.get.asInstanceOf[StringContent]
        content.value should include("user-agent")
      }
    }
  }
}
