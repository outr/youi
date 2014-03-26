package org.hyperscala.web

import com.outr.net.http.request.HttpRequest
import com.outr.net.http.response.HttpResponse
import com.outr.net.http.handler.HandlerListener
import org.powerscala.Priority
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ErrorHandler[S <: Session](website: Website[S]) extends HandlerListener {
  def priority = Priority.Lowest

  def onReceive(request: HttpRequest, response: HttpResponse) = if (response.content == null && response.status.isError) {
    website.errorPage(request, response, response.status)
  } else {
    response
  }
}
