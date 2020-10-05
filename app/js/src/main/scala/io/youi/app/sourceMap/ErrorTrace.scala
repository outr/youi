package io.youi.app.sourceMap

import io.youi.app.ClientApplication
import io.youi.http.HttpMethod
import io.youi.net._
import io.youi.stream.StreamURL
import io.youi.{History, _}
import org.scalajs.dom.{ErrorEvent, Event}
import scribe.output.LogOutput
import scribe.writer.Writer
import scribe.{Level, LogRecord}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.language.{implicitConversions, reflectiveCalls}
import scala.scalajs._

// TODO: source-map.js appears to be newer than source-map.min.js, but having trouble getting it to work
object ErrorTrace extends Writer {
  type StackTraceElementWithColumnNumber = StackTraceElement {
    def getColumnNumber(): Int
  }
  implicit def withColumnNumber(ste: StackTraceElement): StackTraceElementWithColumnNumber = ste.asInstanceOf[StackTraceElementWithColumnNumber]

  private var sourceMaps = Map.empty[String, SourceMapConsumer]

  def toError(event: ErrorEvent): Future[JavaScriptError] = {
    toError(event.message, event.filename, event.lineno, event.colno, None)
  }

  def toError(message: String, source: String, line: Int, column: Int, error: Option[Throwable]): Future[JavaScriptError] = {
    val future = sourceMapConsumerFor(source).map { consumerOption =>
      toErrorInternal(consumerOption, message, source, line, column, error)
    }
    future.failed.foreach { t =>
      t.printStackTrace()
    }
    future
  }

  def toError(throwable: Throwable): Future[JavaScriptError] = {
    val message = throwable.getMessage
    val first = throwable.getStackTrace.head
    val source = first.getFileName
    val line = first.getLineNumber
    val column = first.getColumnNumber()
    toError(message, source, line, column, Some(throwable))
  }

  override def write[M](record: LogRecord[M], output: LogOutput): Unit = if (record.level.value >= Level.Error.value) {
    val value = record.message.value
    value match {
      case evt: Event if evt.`type` == "error" => ClientApplication.sendError(evt.asInstanceOf[ErrorEvent])
      case t: Throwable => ClientApplication.sendError(t)
      case _ => // Ignore others
    }
    record.throwable.foreach(ClientApplication.sendError)
  }

  /**
    * Uses cached copy if one is available or asynchronously loads a consumer from the js.map file.
    *
    * @param fileName the JavaScript file to load the map for
    * @return source map consumer
    */
  private def sourceMapConsumerFor(fileName: String): Future[Option[SourceMapConsumer]] = if (fileName != null) {
    sourceMaps.get(fileName) match {
      case Some(sourceMapConsumer) => Future.successful(Some(sourceMapConsumer))
      case None => {
        val jsURL = URL(fileName)
        val url = jsURL.withPath(s"${jsURL.path.toString}.map")
        if (url.path == path"/.map") {
          Future.successful(None)
        } else {
          StreamURL.stream(url, method = HttpMethod.Get).map { jsonString =>
            try {
              val json = js.JSON.parse(jsonString).asInstanceOf[js.Object]
              val sourceMapConsumer = new SourceMapConsumer(json)
              sourceMaps += fileName -> sourceMapConsumer
              Some(sourceMapConsumer)
            } catch {
              case t: Throwable => {
                scribe.error(s"Failed to parse source-map: $url [${jsonString.take(20)}]", t)
                None
              }
            }
          }
        }
      }
    }
  } else {
    Future.successful(None)
  }

  private def map(sourceMapConsumer: SourceMapConsumer, line: Int, column: Int): Option[SourcePosition] = try {
    val position = js.JSON.parse(profig.JsonUtil.toJsonString(JavaScriptPosition(line, column))).asInstanceOf[js.Object]
    Some(sourceMapConsumer.originalPositionFor(position))
  } catch {
    case t: Throwable => {
      scribe.warn(s"Unable to determine original position for, line: $line, column: $column: ${t.getMessage}")
      None
    }
  }

  private def toErrorInternal(consumerOption: Option[SourceMapConsumer], message: String, source: String, line: Int, column: Int, error: Option[Throwable]): JavaScriptError = {
    val (fileName, sourcePosition) = consumerOption.map { consumer =>
      val sourcePosition = map(consumer, line, column)
      sourcePosition.map(_.source).getOrElse("Unknown Source") -> JavaScriptPosition(sourcePosition.map(_.line).getOrElse(-1), sourcePosition.map(_.column).getOrElse(-1))
    }.getOrElse(source -> JavaScriptPosition(-1, -1))
    val cause = error.map(toCause(consumerOption, _))
    JavaScriptError(
      message = message,
      source = source,
      fileName = fileName,
      jsPosition = JavaScriptPosition(line, column),
      position = sourcePosition,
      url = History.url().toString,
      cause = cause
    )
  }

  private def toCause(consumerOption: Option[SourceMapConsumer], throwable: Throwable): JavaScriptCause = {
    consumerOption.map { consumer =>
      val trace = throwable.getStackTrace.toList.map { element =>
        val tracePosition = map(consumer, element.getLineNumber, element.getColumnNumber())
        JavaScriptTrace(
          className = element.getClassName,
          methodName = element.getMethodName,
          fileName = element.getFileName,
          source = tracePosition.map(_.source).getOrElse("Unknown Source"),
          jsPosition = JavaScriptPosition(element.getLineNumber, element.getColumnNumber()),
          position = JavaScriptPosition(tracePosition.map(_.line).getOrElse(-1), tracePosition.map(_.column).getOrElse(-1))
        )
      }.collect {
        case t if !t.source.endsWith("scala/scalajs/runtime/StackTrace.scala") && !t.source.endsWith("java/lang/Throwables.scala") => t
      }

      JavaScriptCause(
        message = throwable.getLocalizedMessage,
        trace = trace,
        cause = Option(throwable.getCause).map(t => toCause(consumerOption, t))
      )
    }.getOrElse {
      val trace = throwable.getStackTrace.toList.map { element =>
        JavaScriptTrace(
          className = element.getClassName,
          methodName = element.getMethodName,
          fileName = element.getFileName,
          source = "",
          jsPosition = JavaScriptPosition(element.getLineNumber, element.getColumnNumber()),
          position = JavaScriptPosition(-1, -1)
        )
      }
      JavaScriptCause(throwable.getLocalizedMessage, trace, None)
    }
  }
}