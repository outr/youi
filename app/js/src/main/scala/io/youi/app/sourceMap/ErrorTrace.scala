package io.youi.app.sourceMap

import io.youi.app.ClientApplication
import io.youi.{History, _}
import io.youi.net.URL
import io.youi.stream.StreamURL
import scribe.{Level, LogHandler, LogRecord}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs._
import scala.scalajs.runtime.StackTrace.Implicits._

object ErrorTrace extends LogHandler {
  private var sourceMaps = Map.empty[String, SourceMapConsumer]

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

  override def level: Level = Level.Error

  override protected def publish(record: LogRecord): Unit = record.messageObject match {
    case t: Throwable => ClientApplication.sendError(t)
    case _ => // Ignore others
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
      case None => StreamURL.stream(URL(s"$fileName.map")).map { jsonString =>
        try {
          val json = js.JSON.parse(jsonString).asInstanceOf[js.Object]
          val sourceMapConsumer = new SourceMapConsumer(json)
          sourceMaps += fileName -> sourceMapConsumer
          Some(sourceMapConsumer)
        } catch {
          case t: Throwable => {
            scribe.error(t)
            None
          }
        }
      }
    }
  } else {
    Future.successful(None)
  }

  private def map(sourceMapConsumer: SourceMapConsumer, line: Int, column: Int): SourcePosition = {
    val position = js.JSON.parse(upickle.default.write(JavaScriptPosition(line, column))).asInstanceOf[js.Object]
    sourceMapConsumer.originalPositionFor(position)
  }

  private def toErrorInternal(consumerOption: Option[SourceMapConsumer], message: String, source: String, line: Int, column: Int, error: Option[Throwable]): JavaScriptError = {
    val (fileName, sourcePosition) = consumerOption.map { consumer =>
      val sourcePosition = map(consumer, line, column)
      sourcePosition.source -> JavaScriptPosition(sourcePosition.line, sourcePosition.column)
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
          source = tracePosition.source,
          jsPosition = JavaScriptPosition(element.getLineNumber, element.getColumnNumber()),
          position = JavaScriptPosition(tracePosition.line, tracePosition.column)
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