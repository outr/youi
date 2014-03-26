package org.hyperscala.web

import com.outr.net.http.session.MapSession
import com.outr.net.http.request.HttpRequest

/**
 * BasicWebsite simply provides a default MapSession implementation if your site doesn't need special handling for the
 * session.
 *
 * @author Matt Hicks <matt@outr.com>
 */
trait BasicWebsite extends Website[MapSession] {
  protected def createSession(request: HttpRequest, id: String) = new MapSession(id, this)
}
