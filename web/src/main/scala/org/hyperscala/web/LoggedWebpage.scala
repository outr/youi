package org.hyperscala.web

import com.outr.net.http.session.Session
import java.io.File
import org.powerscala.log.formatter.FormatterBuilder
import org.powerscala.log.writer.FileWriter
import org.powerscala.log.handler.Handler
import org.powerscala.log.Level
import org.hyperscala.Page
import com.outr.net.http.request.HttpRequest
import com.outr.net.http.response.HttpResponse
import org.powerscala.concurrent.Time

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait LoggedWebpage[S <: Session] extends Webpage[S] {
  def accessLogDirectory: File

  logger.configure {    // Create a custom logger not tied to anything so it can only be explicitly logged to
    case l => {
      val formatter = FormatterBuilder.parse("${date} - ${message}${newLine}")
      val writer = new FileWriter(accessLogDirectory, FileWriter.Daily("access"), append = true)
      l.withParentName(null).withHandler(Handler(formatter, Level.Info, writer))
    }
  }

  override def onReceive(request: HttpRequest, response: HttpResponse) = {
    val (result, time) = Time.elapsedReturn {
      super.onReceive(request, response)
    }
    logAccess(time)
    result
  }

  private def logAccess(time: Double) = {
    val session = website.session
    val site = website
    val request = website.request
    val sessionInformation = "Session Lifetime: %s, Cached Pages: %s, Session Values: %s".format(session.lifetime, session.values.count(p => p.isInstanceOf[Page]), session.values.size)
    val remote = s"${site.request.remoteAddress}:${site.request.remotePort}"
    val url = request.url
    info(s"$remote, Page: ${getClass.getSimpleName}, URL: $url, Time: $time (seconds), $sessionInformation ${additionalInformation().getOrElse("")}")
  }

  protected def additionalInformation(): Option[String] = None
}
