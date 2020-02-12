package io.youi.server.handler

import java.io.File

import io.circe.parser._
import io.circe.syntax._
import io.circe.{Decoder, Encoder, Json, Printer}
import io.youi.Priority
import io.youi.http._
import io.youi.http.content.{Content, StringContent}
import io.youi.net.{ContentType, Path, URL, URLMatcher}
import io.youi.server.Server
import io.youi.server.validation.{ValidationResult, Validator}
import io.youi.stream.delta.Delta
import io.youi.stream.{HTMLParser, Selector}

import scala.concurrent.Future
import scribe.Execution.global

case class HttpHandlerBuilder(server: Server,
                              urlMatcher: Option[URLMatcher] = None,
                              requestMatchers: Set[HttpRequest => Boolean] = Set.empty,
                              cachingManager: CachingManager = CachingManager.Default,
                              priority: Priority = Priority.Normal,
                              validators: List[Validator] = Nil) {
  def priority(priority: Priority): HttpHandlerBuilder = copy(priority = priority)

  def matcher(urlMatcher: URLMatcher): HttpHandlerBuilder = copy(urlMatcher = Some(urlMatcher))

  def requestMatcher(requestMatcher: HttpRequest => Boolean): HttpHandlerBuilder = copy(requestMatchers = requestMatchers + requestMatcher)

  def methodMatcher(method: HttpMethod): HttpHandlerBuilder = requestMatcher(request => request.method == method)

  def caching(cachingManager: CachingManager): HttpHandlerBuilder = copy(cachingManager = cachingManager)

  def withValidation(validators: Validator*): HttpHandlerBuilder = copy(validators = validators.toList ::: this.validators)

  def resource(f: => Content): HttpHandler = resource((_: URL) => Some(f))

  def resource(f: URL => Option[Content]): HttpHandler = {
    handle { connection =>
      f(connection.request.url).map { content =>
        SenderHandler(content, caching = cachingManager).handle(connection)
      }.getOrElse(Future.successful(connection))
    }
  }

  def file(directory: File, pathTransform: String => String = (s: String) => s): HttpHandler = {
    handle { connection =>
      val path = pathTransform(connection.request.url.path.decoded)
      val file = new File(directory, path)
      if (file.isFile) {
        SenderHandler(Content.file(file), caching = cachingManager).handle(connection)
      } else {
        Future.successful(connection)
      }
    }
  }

  def classLoader(directory: String = "", pathTransform: String => String = (s: String) => s): HttpHandler = {
    val dir = if (directory.endsWith("/")) {
      directory.substring(directory.length - 1)
    } else {
      directory
    }
    handle { connection =>
      val path = pathTransform(connection.request.url.path.decoded)
      val resourcePath = s"$dir$path" match {
        case s if s.startsWith("/") => s.substring(1)
        case s => s
      }
      Option(getClass.getClassLoader.getResource(resourcePath)).map { url =>
        val file = new File(url.getFile)
        if (!file.isDirectory) {
          SenderHandler(Content.classPath(url), caching = cachingManager).handle(connection)
        } else {
          Future.successful(connection)
        }
      }.getOrElse(Future.successful(connection))
    }
  }

  def stream(baseDirectory: File, basePath: String, deltas: HttpConnection => List[Delta] = _ => Nil): HttpHandler = handle { connection =>
    val url = connection.request.url
    val path = url.path.decoded
    if (path.startsWith(basePath)) {
      val clippedPath = path.substring(basePath.length)
      val file = new File(baseDirectory, clippedPath)
      if (file.exists()) {
        val parser = HTMLParser.cache(file)
        val selector = url.param("selector").map(Selector.parse)
        val mods = deltas(connection)
        val html = parser.stream(mods, selector)
        val content = StringContent(html, ContentType.`text/html`, file.lastModified())
        val handler = SenderHandler(content, caching = cachingManager)
        handler.handle(connection)
      } else {
        Future.successful(connection)
      }
    } else {
      Future.successful(connection)
    }
  }

  def handle(f: HttpConnection => Future[HttpConnection]): HttpHandler = wrap(new HttpHandler {
    override def handle(connection: HttpConnection): Future[HttpConnection] = f(connection)
  })

  def validation(validator: HttpConnection => Future[ValidationResult]): HttpHandler = validation(new Validator {
    override def validate(connection: HttpConnection): Future[ValidationResult] = validator(connection)
  })

  def validation(validators: Validator*): HttpHandler = wrap(new ValidatorHttpHandler(validators.toList))

  def redirect(path: Path): HttpHandler = handle { connection =>
    Future.successful(HttpHandler.redirect(connection, path.encoded))
  }

  def content(content: => Content): HttpHandler = handle { connection =>
    Future.successful {
      connection.modify { response =>
        response.withContent(content)
      }
    }
  }

  def restful[Request, Response](handler: Request => Response)
                                (implicit decoder: Decoder[Request], encoder: Encoder[Response]): HttpHandler = {
    val printer = Printer.spaces2.copy(dropNullValues = false)
    handle { connection =>
      val jsonOption: Option[Json] = connection.request.method match {
        case HttpMethod.Get => {
          Some(Json.obj(connection.request.url.parameters.entries.map {
            case (key, param) => key -> Json.fromString(param.value)
          }: _*))
        }
        case _ => connection.request.content match {
          case Some(content) => content match {
            case StringContent(jsonString, _, _) => parse(jsonString) match {
              case Left(failure) => {
                scribe.warn(failure)
                None
              }
              case Right(json) => Some(json)
            }
            case _ => {
              scribe.error(s"Unsupported content for restful end-point: $content.")
              None
            }
          }
          case None => None     // Ignore calls to this end-point that have no content
        }
      }
      jsonOption.map { json =>
        json.as[Request] match {
          case Left(error) => throw new RuntimeException(s"Error parsing $json", error)
          case Right(request) => Future.successful {
            val response = handler(request)
            val responseJson = response.asJson
            val responseJsonString = printer.print(responseJson)
            connection.modify { httpResponse =>
              httpResponse.withContent(Content.string(responseJsonString, ContentType.`application/json`))
            }
          }
        }
      }.getOrElse(Future.successful(connection))
    }
  }

  def zip(entries: ZipFileEntry*): HttpHandler = content(new StreamZipContent(entries.toList))

  def wrap(handler: HttpHandler): HttpHandler = {
    val p = if (priority == Priority.Normal) handler.priority else priority
    val wrapper = new HttpHandler {
      override def priority: Priority = p

      override def handle(connection: HttpConnection): Future[HttpConnection] = {
        if (urlMatcher.forall(_.matches(connection.request.url)) && requestMatchers.forall(_(connection.request))) {
          ValidatorHttpHandler.validate(connection, validators).flatMap {
            case ValidationResult.Continue(c) => handler.handle(c)
            case vr => Future.successful(vr.connection) // Validation failed, handled by ValidatorHttpHandler
          }
        } else {
          Future.successful(connection)
        }
      }
    }
    server.handlers += wrapper
    handler
  }

  def apply(handler: HttpHandler): HttpHandler = wrap(handler)
}