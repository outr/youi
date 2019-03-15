package io.youi.client

import io.circe.{Json, Printer}
import io.youi.client.intercept.{Interceptor, RateLimiter}
import io.youi.http.cookie.RequestCookie
import io.youi.http._
import io.youi.http.content.{Content, StringContent}
import io.youi.net.{ContentType, Path, URL}

import scala.concurrent.duration.FiniteDuration
import scala.concurrent.{ExecutionContext, Future}
import scala.language.experimental.macros
import scala.reflect.macros.blackbox

case class HttpClient(request: HttpRequest,
                      implementation: HttpClientImplementation,
                      retries: Int,
                      retryDelay: FiniteDuration,
                      sessionManager: Option[SessionManager],
                      interceptor: Interceptor,
                      dropNullValuesInJson: Boolean) {
  protected lazy val printer: Printer = Printer.spaces2.copy(dropNullValues = dropNullValuesInJson)

  def modify(f: HttpRequest => HttpRequest): HttpClient = copy(request = f(request))

  def url(url: URL): HttpClient = modify(_.copy(url = url))
  def path(path: Path): HttpClient = modify(_.copy(url = request.url.withPath(path)))
  def params(params: (String, String)*): HttpClient = modify(_.copy(url = request.url.withParams(params.toMap)))
  def param[T](name: String, value: T, default: T): HttpClient = if (value != default) {
    value match {
      case s: String => params(name -> s)
      case b: Boolean => params(name -> b.toString)
      case i: Int => params(name -> i.toString)
      case l: List[Any] => params(name -> l.mkString(","))
      case s: Some[Any] => param[Any](name, s.head, default)
      case None => this
      case _ => throw new RuntimeException(s"Unsupported param type: $value (${value.getClass.getSimpleName})")
    }
  } else {
    this
  }
  def appendParams(params: (String, String)*): HttpClient = modify(_.copy(url = request.url.withParams(params.toMap, append = true)))

  def method(method: Method): HttpClient = modify(_.copy(method = method))
  def get: HttpClient = method(Method.Get)
  def post: HttpClient = method(Method.Post)
  def header(header: Header): HttpClient = modify(r => r.copy(headers = r.headers.withHeader(header)))
  def header(key: String, value: String): HttpClient = header(Header(HeaderKey(key), value))

  def retries(retries: Int): HttpClient = copy(retries = retries)
  def sessionManager(sessionManager: SessionManager): HttpClient = copy(sessionManager = Some(sessionManager))
  def clearSessionManager(): HttpClient = copy(sessionManager = None)
  def interceptor(interceptor: Interceptor): HttpClient = copy(interceptor = interceptor)
  def dropNullValuesInJson(dropNullValuesInJson: Boolean): HttpClient = copy(dropNullValuesInJson = dropNullValuesInJson)

  def content(content: Content): HttpClient = modify(_.copy(content = Some(content)))
  def json(json: Json): HttpClient = content(StringContent(printer.pretty(json), ContentType.`application/json`))

  /**
    * Sends an HttpRequest and receives an asynchronous HttpResponse future.
    *
    * @return Future[HttpResponse]
    */
  final def send(retries: Int = this.retries)(implicit executionContext: ExecutionContext): Future[HttpResponse] = {
    val updatedHeaders = sessionManager match {
      case Some(sm) => {
        val cookieHeaders = sm.session.cookies.map { cookie =>
          RequestCookie(name = cookie.name, value = cookie.value).http
        } ::: Headers.Request.`Cookie`.value(request.headers).map(_.http).distinct
        request.headers.withHeaders(Headers.Request.`Cookie`.key, cookieHeaders)
      }
      case None => request.headers
    }
    val future = for {
      updatedRequest <- interceptor.before(request.copy(headers = updatedHeaders))
      response <- implementation.send(updatedRequest, executionContext)
      updatedResponse <- interceptor.after(updatedRequest, response)
    } yield {
      updatedResponse
    }
    future.recoverWith {
      case t: Throwable if retries > 0 => {
        scribe.warn(s"Request to ${request.url} failed (${t.getMessage}). Retrying after $retryDelay...")
        RateLimiter.delayedFuture(retryDelay, send(retries - 1))
      }
    }.map { response =>
      sessionManager.foreach { sm =>
        val cookies = response.cookies
        sm(cookies)
      }

      response
    }
  }

  /**
    * Builds on the send method by supporting basic restful calls that calls a URL and returns a case class as the
    * response.
    *
    * @tparam Response the response type
    * @return Future[Response]
    */
  def call[Response]: Future[Response] = macro HttpClient.autoCall[Response]

  /**
    * Builds on the send method by supporting basic restful calls that take a case class as the request and returns a
    * case class as the response.
    *
    * @param request the request object to convert to JSON and send
    * @tparam Request the request type
    * @tparam Response the response type
    * @return Future[Response]
    */
  def restful[Request, Response](request: Request): Future[Response] = macro HttpClient.autoRestful[Request, Response]
}

object HttpClient extends HttpClient(
  request = HttpRequest(),
  implementation = ClientPlatform.implementation(),
  retries = HttpClientConfig.default().retries,
  retryDelay = HttpClientConfig.default().retryDelay,
  sessionManager = HttpClientConfig.default().sessionManager,
  interceptor = HttpClientConfig.default().interceptor,
  dropNullValuesInJson = HttpClientConfig.default().dropNullValuesInJson
) {
  def autoCall[Response](c: blackbox.Context)
                        (implicit r: c.WeakTypeTag[Response]): c.Expr[Future[Response]] = {
    import c.universe._

    val client = c.prefix.tree

    c.Expr[Future[Response]](
      q"""
         import _root_.io.youi.client._

         $client.send().map { response =>
           try {
             val responseJson = response.content.map($client.implementation.content2String).getOrElse("")
             if (response.status.isSuccess) {
               if (responseJson.isEmpty) throw new ClientException(s"No content received in response for $${$client.request.url}.", $client.request, response, None)
               _root_.profig.JsonUtil.fromJsonString[$r](responseJson)
             } else {
              throw new ClientException("HttpStatus was not successful", $client.request, response, None)
             }
           } catch {
             case t: Throwable => throw new ClientException("Response processing error", $client.request, response, Some(t))
           }
         }
       """)
  }

  def autoRestful[Request, Response](c: blackbox.Context)
                                    (request: c.Expr[Request])
                                    (implicit req: c.WeakTypeTag[Request], res: c.WeakTypeTag[Response]): c.Expr[Future[Response]] = {
    import c.universe._

    val client = c.prefix.tree

    c.Expr[Future[Response]](
      q"""
         import _root_.io.youi.client._
         import _root_.profig.JsonUtil

         val requestJson = JsonUtil.toJson[$req]($request)
         $client.post.json(requestJson).send().map { response =>
           try {
             val responseJson = response.content.map($client.implementation.content2String).getOrElse("")
             if (response.status.isSuccess) {
               if (responseJson.isEmpty) throw new ClientException(s"No content received in response for $${$client.request.url}.", $client.request, response, None)
               _root_.profig.JsonUtil.fromJsonString[$res](responseJson)
             } else {
              throw new ClientException("HttpStatus was not successful", $client.request, response, None)
             }
           } catch {
             case t: Throwable => throw new ClientException("Response processing error", $client.request, response, Some(t))
           }
         }
      """)
  }
}