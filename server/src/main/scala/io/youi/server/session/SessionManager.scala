package io.youi.server.session

import io.youi.Unique
import io.youi.communication.Connection
import io.youi.http.cookie.ResponseCookie
import io.youi.http.{Headers, HttpConnection}
import io.youi.net.Protocol
import io.youi.server.WebSocketListener
import scribe.Execution.global

import scala.concurrent.Future

/**
  * SessionManager must be implemented in order to have support for sessions
  *
  * @tparam Session the type of session
  */
trait SessionManager[Session] {
  /**
    * Set to true to apply the session id to the URL. Defaults to false.
    */
  protected def applyToURL: Boolean = false

  /**
    * Functional use of a Session via a transaction that is fully managed with the result being updated to the manager
    *
    * @param listener the WebSocketListener
    * @param f the functionality to work with and potentially modify a session instance
    * @return Future[Unit] since Connection cannot modify the state of HttpConnection
    */
  def withWebSocketListener(listener: WebSocketListener)
                          (f: SessionTransaction[Session] => Future[SessionTransaction[Session]] = t => Future.successful(t)): Future[Session] = {
    val httpConnection = listener.httpConnection
    session(httpConnection, f, requestModifiable = false).map(_.session)
  }

  def withConnection(connection: Connection)
                    (f: SessionTransaction[Session] => Future[SessionTransaction[Session]] = t => Future.successful(t)): Future[Session] = {
    val listener = connection.webSocket.getOrElse(throw new RuntimeException("No active connection")).asInstanceOf[WebSocketListener]
    withWebSocketListener(listener)(f)
  }

  /**
    * Functional use of a Session via a transaction that is fully managed with the result being updated to the manager
    *
    * @param connection the HttpConnection to work with
    * @param f the functionality to work with and potentially modify a session instance
    * @return potentially modified HttpConnection
    */
  def withHttpConnection(connection: HttpConnection)
                        (f: SessionTransaction[Session] => Future[SessionTransaction[Session]] = t => Future.successful(t)): Future[SessionTransaction[Session]] = {
    session(connection, f, requestModifiable = true).map(_.copy(sessionModifiable = false))
  }

  /**
    * Manages the entire transaction process of working with a session
    *
    * @param connection the HttpConnection to work with
    * @param f the transaction handling function to apply
    * @param requestModifiable true if the request can be modified
    * @return the final, modified HttpConnection
    */
  protected def session(connection: HttpConnection,
                        f: SessionTransaction[Session] => Future[SessionTransaction[Session]],
                        requestModifiable: Boolean): Future[SessionTransaction[Session]] = for {
    // Load or create the session id
    (modifiedConnection, sessionId) <- Future.successful(getOrCreateSessionId(connection))
    // Get the existing session if it exists
    originalTransaction <- loadBySessionId(sessionId, modifiedConnection)
    // Update the existing transaction or create a new one if none exists
    transaction <- originalTransaction match {
      case Some(t) => Future.successful(t.copy(requestModifiable = requestModifiable))
      case None => createBySessionId(sessionId, modifiedConnection).map(_.copy(requestModifiable = requestModifiable))
    }
    // Apply the transaction handler
    updatedTransaction <- f(transaction)
    // Determine if the session was modified
    modified = !originalTransaction.map(_.session).contains(updatedTransaction.session)
    // Save the transaction if the session was modified
    saved <- if (modified) {
      save(updatedTransaction)
    } else {
      Future.successful(updatedTransaction)
    }
  } yield {
    saved
  }

  /**
    * Loads a session by id if available and creates a SessionTransaction wrapper
    *
    * @param sessionId the session id to load from
    * @param connection the HttpConnection to work with
    * @return a future SessionTransaction[Session] if one is persisted for this manager
    */
  protected def loadBySessionId(sessionId: String,
                                connection: HttpConnection): Future[Option[SessionTransaction[Session]]]

  /**
    * Creates a new session by session id
    *
    * @param sessionId the session id to create from
    * @param connection the HttpConnection to work with
    * @return a future SessionTransaction[Session]
    */
  protected def createBySessionId(sessionId: String,
                                  connection: HttpConnection): Future[SessionTransaction[Session]] = {
    create(sessionId).map { session =>
      SessionTransaction[Session](sessionId, session, connection)
    }
  }

  /**
    * Simple create function used by createBySessionId. For more advanced usage, extend createBySessionId.
    *
    * @param sessionId the session id to create a Session for
    */
  protected def create(sessionId: String): Future[Session]

  /**
    * Saves a potentially modified Session to this manager
    *
    * @param transaction the transaction to persist from
    * @return a potentially modified HttpConnection
    */
  protected def save(transaction: SessionTransaction[Session]): Future[SessionTransaction[Session]]

  /**
    * Gets a session id if it already exists or creates a new one (and applies it on the HttpConnection) if it doesn't
    * already exist.
    *
    * @param connection the HttpConnection to use
    * @return a tuple with the potentially modified HttpConnection and the session id
    */
  protected def getOrCreateSessionId(connection: HttpConnection): (HttpConnection, String) = sessionId(connection) match {
    case Some(id) => connection -> id
    case None => {
      val id = generateSessionId
      applySessionId(id, connection) -> id
    }
  }

  /**
    * Generates a unique id for use when creating a new session
    */
  protected def generateSessionId: String = Unique()

  /**
    * Retrieves the session id from the request / response cookies if available and from the URL if applyToURL is true.
    *
    * @param connection the HttpConnection to look in
    * @return the session id if found
    */
  protected def sessionId(connection: HttpConnection): Option[String] = {
    connection.store.get[String]("sessionId") match {
      case Some(id) => Some(id)
      case _ if applyToURL => connection.request.url.param("sessionId")
      case None => {
        val config = connection.server.config.session
        connection.request.cookies.find(_.name == config.name()).map(_.value) match {
          case Some(id) => Some(id)
          case None => connection.response.cookies.find(_.name == config.name()).map(_.value) match {
            case Some(id) => Some(id)
            case None => None
          }
        }
      }
    }
  }

  /**
    * Applies a new session id to an HttpConnection. Creates a cookie and sets it on the HttpResponse. Also updates the
    * URL if applyToURL is true.
    *
    * @param id the session id to apply
    * @param connection the HttpConnection to use
    * @return modified HttpConnection
    */
  protected def applySessionId(id: String, connection: HttpConnection): HttpConnection = {
    val config = connection.server.config.session
    if (config.secure() && !config.forceSecure() && connection.request.url.protocol != Protocol.Https) {
      connection        // Don't set cookie if secure cookie is required and non-HTTPs
    } else {
      connection.store("sessionId") = id
      connection.modify { response =>
        val cookie = ResponseCookie(
          name = config.name(),
          value = id,
          maxAge = if (config.maxAge() == 0L) None else Some(config.maxAge()),
          domain = config.domain(),
          path = config.path(),
          secure = config.secure(),
          httpOnly = config.httpOnly(),
          sameSite = config.sameSite()
        )
        if (applyToURL) {
          response.withRedirect(connection.request.url.withParam("sessionId", id).toString)
        } else {
          response.withHeader(Headers.Response.`Set-Cookie`(cookie))
        }
      }
    }
  }
}