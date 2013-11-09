package org.hyperscala.web

import com.outr.net.http.request.HttpRequest
import com.outr.net.http.response.HttpResponse
import com.outr.net.http.handler.HandlerListener
import org.powerscala.Priority

/**
 * @author Matt Hicks <matt@outr.com>
 */
object ErrorHandler extends HandlerListener {
  def priority = Priority.Lowest

  def onReceive(request: HttpRequest, response: HttpResponse) = if (response.content == null && response.status.isError) {
    val site = Website()
    site.errorPage(request, response, response.status)
  } else {
    response
  }
}
