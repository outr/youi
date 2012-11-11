package org.hyperscala.web.resource.handler

import org.hyperscala.web.resource.{WebResourceInterceptable, WebResource}
import org.hyperscala.web.session.Session
import org.hyperscala.html.attributes.Method
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait WebResourceManager extends WebResourceHandler with WebResourceInterceptable {
  def apply(method: Method, request: HttpServletRequest, response: HttpServletResponse) = {
    val uri = request.getRequestURI
    if (isMatch(uri)) {
      processInterceptors(webResourceInterceptors, method, request, response)(() => {
        val resource = sessionOption match {
          case Some(session) => {
            val key = sessionKey(uri)
            session.get[WebResource](key) match {
              case Some(r) if (!r.disposed) => r
              case _ => {
                val r = createOverride(uri)
                session(key) = r
                r
              }
            }
          }
          case None => create(uri)
        }
        resource.service(method, request, response)
      })
    } else {
      false
    }
  }

  /**
   * Allows a mix-in to circumvent the create method call.
   */
  protected def createOverride(uri: String) = create(uri)

  /**
   * Checks the supplied URI to determine if a match is found in this manager.
   *
   * @param uri URI being requested
   * @return true if this manager can handle the URI
   */
  def isMatch(uri: String): Boolean

  /**
   * Creates a WebResource based on the supplied uri. This will only be invoked after a successful response from the
   * isMatch method.
   *
   * @param uri the uri being requested
   * @return the WebResource to access for the supplied uri
   */
  def create(uri: String): WebResource

  /**
   * The session object to use for storage of the WebResource for later retrieval.
   *
   * Defaults to None
   */
  def sessionOption: Option[Session] = None

  /**
   * The session key to use when storing within the session. This is only leveraged if sessionOption does not return
   * None.
   *
   * Defaults to uri
   */
  def sessionKey(uri: String) = uri
}
