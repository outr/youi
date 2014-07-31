package org.hyperscala.service

import com.outr.net.URL
import com.outr.net.http.request.HttpRequest
import com.outr.net.http.response.{HttpResponseStatus, HttpResponse}
import com.outr.net.http.session.MapSession
import org.hyperscala.web.Website
import org.scalatest.{Matchers, WordSpec}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ServiceSpec extends WordSpec with Matchers {
  "SimpleService" should {
    "properly register with TestSite" in {
      SimpleService.register(TestSite)
      TestSite.init()
    }
    "have exactly one endpoint" in {
      SimpleService.endpoints.size should equal(1)
    }
    "have the proper URI for the endpoint" in {
      SimpleService.endpoints.head.uri should equal("/test/reverse")
    }
    "invoke the 'reverse' endpoint" in {
      val request = HttpRequest(URL("http://localhost/test/reverse?name=Hello%20World"))
      val response = TestSite.onReceive(request, HttpResponse(status = HttpResponseStatus.NotFound))
      response.status should equal(HttpResponseStatus.OK)
      response.content shouldNot equal(null)
      response.content.asString should equal("\"dlroW olleH\"")
    }
    "invoke the 'reverse' endpoint without name" in {
      val request = HttpRequest(URL("http://localhost/test/reverse"))
      val response = TestSite.onReceive(request, HttpResponse(status = HttpResponseStatus.NotFound))
      response.status should equal(HttpResponseStatus.InternalServerError)
      response.content shouldNot equal(null)
      response.content.asString should equal("""{"message":"Unable to find value for /test/reverse.name.","code":501}""")
    }
    "invoke the 'reverse' endpoint with 'fail' to cause an arbitrary failure" in {
      val request = HttpRequest(URL("http://localhost/test/reverse?name=fail"))
      val response = TestSite.onReceive(request, HttpResponse(status = HttpResponseStatus.NotFound))
      response.status should equal(HttpResponseStatus.InternalServerError)
      response.content shouldNot equal(null)
      response.content.asString should equal("""{"message":"Intended Failure","code":100}""")
    }
  }
  "ComplexService" should {
    "properly register with TestSite" in {
      ComplexService.register(TestSite)
    }
    "have exactly two endpoints" in {
      ComplexService.endpoints.size should equal(2)
    }
    "have the proper URI for the endpoints" in {
      ComplexService.endpoint("query").get.uri should equal("/complex/query")
      ComplexService.endpoint("byId").get.uri should equal("/complex/byId")
    }
    "invoke the 'byId' endpoint" in {
      val request = HttpRequest(URL("http://localhost/complex/byId?id=5"))
      val response = TestSite.onReceive(request, HttpResponse(status = HttpResponseStatus.NotFound))
      response.status should equal(HttpResponseStatus.OK)
      response.content.asString should equal("""{"option":{"name":"Jim Doe","id":5}}""")
    }
    "invoke the 'query' endpoint" in {
      val request = HttpRequest(URL("http://localhost/complex/query?search=.*Dee"))
      val response = TestSite.onReceive(request, HttpResponse(status = HttpResponseStatus.NotFound))
      response.status should equal(HttpResponseStatus.OK)
      response.content.asString should equal("""[{"name":"Bill Dee","id":7},{"name":"Ross Dee","id":8}]""")
    }
  }
}

object SimpleService extends Service {
  override val path = "/test"

  @endpoint
  def reverse(name: String) = if (name == "fail") {
    throw new ServiceException("Intended Failure", 100)
  } else {
    name.reverse
  }
}

object ComplexService extends Service {
  override val path = "/complex"

  val people = List(
    Person("John Doe", 1),
    Person("Jane Doe", 2),
    Person("Joe Doe", 3),
    Person("Pete Doe", 4),
    Person("Jim Doe", 5),
    Person("Bob Doe", 6),
    Person("Bill Dee", 7),
    Person("Ross Dee", 8),
    Person("Ray Doe", 9),
    Person("Me Doe", 10)
  )

  @endpoint
  def query(search: Option[String] = None, maxResults: Int = 10) = {
    people.filter {
      case p => search.isEmpty || p.name.matches(search.get)
    }.take(maxResults)
  }

  @endpoint
  def byId(id: Int) = people.find(p => p.id == id)
}

case class Person(name: String, id: Int)

object TestSite extends Website[MapSession] {
  override protected def createSession(request: HttpRequest, id: String) = new MapSession(id, this)
}
