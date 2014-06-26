package org.hyperscala.web

import com.outr.net.http.session.Session
import java.io.File
import org.powerscala.log.formatter.FormatterBuilder
import org.powerscala.log.writer.FileWriter
import org.powerscala.log.{Logging, Level}
import org.hyperscala.Page
import com.outr.net.http.request.HttpRequest
import com.outr.net.http.response.HttpResponse
import org.powerscala.concurrent.Time

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait LoggedWebpage[S <: Session] extends Webpage[S] {
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
    val sessionInformation = s"Session Lifetime: ${session.lifetime}, Cached Pages: ${session.values.count(p => p.isInstanceOf[Page])}, Session Values: ${session.values.size}"
    val remote = s"${site.request.remoteAddress}:${site.request.remotePort}"
    val url = request.url
    LoggedWebpage.info(s"$remote, Page: ${getClass.getSimpleName}, URL: $url, Time: $time (seconds), $sessionInformation ${additionalInformation().getOrElse("")}")
  }

  protected def additionalInformation(): Option[String] = None
}

object LoggedWebpage extends Logging {
  logger.parent = None
  logger.addHandler(new FileWriter(new File("logs"), FileWriter.Daily("access"), append = true), Level.Info, FormatterBuilder.parse("${date} - ${message}${newLine}"))
}