package io.youi.server.handler

import cats.effect.IO
import fabric._
import fabric.parse.JsonParser

import java.io.File
import io.youi.http._
import io.youi.http.content.{Content, StringContent}
import io.youi.net.{ContentType, Path, URL, URLMatcher}
import io.youi.server.Server
import io.youi.server.validation.{ValidationResult, Validator}
import io.youi.stream.delta.Delta
import io.youi.stream.{HTMLParser, Selector}
import scribe.Priority
import fabric.rw._

import scala.util.Try

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
      }.getOrElse(IO.pure(connection))
    }
  }

  def file(directory: File, pathTransform: String => String = (s: String) => s): HttpHandler = {
    handle { connection =>
      val path = pathTransform(connection.request.url.path.decoded)
      val file = new File(directory, path)
      if (file.isFile) {
        SenderHandler(Content.file(file), caching = cachingManager).handle(connection)
      } else {
        IO.pure(connection)
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
          IO.pure(connection)
        }
      }.getOrElse(IO.pure(connection))
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
        IO.pure(connection)
      }
    } else {
      IO.pure(connection)
    }
  }

  def handle(f: HttpConnection => IO[HttpConnection]): HttpHandler = wrap(new HttpHandler {
    override def handle(connection: HttpConnection): IO[HttpConnection] = f(connection)
  })

  def validation(validator: HttpConnection => IO[ValidationResult]): HttpHandler = validation(new Validator {
    override def validate(connection: HttpConnection): IO[ValidationResult] = validator(connection)
  })

  def validation(validators: Validator*): HttpHandler = wrap(new ValidatorHttpHandler(validators.toList))

  def redirect(path: Path): HttpHandler = handle { connection =>
    IO.pure(HttpHandler.redirect(connection, path.encoded))
  }

  def content(content: => Content): HttpHandler = handle { connection =>
    IO {
      connection.modify { response =>
        response.withContent(content)
      }
    }
  }

  def restful[Request, Response](handler: Request => Response)
                                (implicit writer: Writer[Request], reader: Reader[Response]): HttpHandler = {
    handle { connection =>
      val jsonOption: Option[Value] = connection.request.method match {
        case HttpMethod.Get => {
          Some(obj(connection.request.url.parameters.entries.map {
            case (key, param) => key -> str(param.value)
          }: _*))
        }
        case _ => connection.request.content match {
          case Some(content) => content match {
            case StringContent(jsonString, _, _) => Try(JsonParser.parse(jsonString)).toOption
            case _ => {
              scribe.error(s"Unsupported content for restful end-point: $content.")
              None
            }
          }
          case None => None     // Ignore calls to this end-point that have no content
        }
      }
      IO {
        jsonOption.map { json =>
          val request: Request = json.as[Request]
          val response: Response = handler(request)
          val responseJsonString = JsonParser.format(response.json)
          connection.modify { httpResponse =>
            httpResponse.withContent(Content.string(responseJsonString, ContentType.`application/json`))
          }
        }.getOrElse(connection)
      }
    }
  }

  def zip(entries: ZipFileEntry*): HttpHandler = content(new StreamZipContent(entries.toList))

  def wrap(handler: HttpHandler): HttpHandler = {
    val p = if (priority == Priority.Normal) handler.priority else priority
    val wrapper = new HttpHandler {
      override def priority: Priority = p

      override def handle(connection: HttpConnection): IO[HttpConnection] = {
        if (urlMatcher.forall(_.matches(connection.request.url)) && requestMatchers.forall(_(connection.request))) {
          ValidatorHttpHandler.validate(connection, validators).flatMap {
            case ValidationResult.Continue(c) => handler.handle(c)
            case vr => IO.pure(vr.connection) // Validation failed, handled by ValidatorHttpHandler
          }
        } else {
          IO.pure(connection)
        }
      }
    }
    server.handlers += wrapper
    handler
  }

  def apply(handler: HttpHandler): HttpHandler = wrap(handler)
}