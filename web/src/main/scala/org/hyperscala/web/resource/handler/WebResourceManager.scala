package org.hyperscala.web.resource.handler

import org.hyperscala.web.resource.WebResource
import org.hyperscala.web.session.Session

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait WebResourceManager extends WebResourceHandler {
  def apply(uri: String) = {
    if (isMatch(uri)) {
      sessionOption match {
        case Some(session) => {
          val key = sessionKey(uri)
          session.get[WebResource](key) match {
            case s: Some[WebResource] => s
            case None => {
              val resource = create(uri)
              session(key) = resource
              Some(resource)
            }
          }
        }
        case None => Some(create(uri))
      }
    } else {
      None
    }
  }

  def isMatch(uri: String): Boolean

  def create(uri: String): WebResource

  def sessionOption: Option[Session] = None

  def sessionKey(uri: String) = uri
}
