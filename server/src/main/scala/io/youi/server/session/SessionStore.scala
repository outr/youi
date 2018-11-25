package io.youi.server.session

import akka.actor.ActorSystem
import reactify._
import io.youi.http.cookie.ResponseCookie
import io.youi.http.{Headers, HttpConnection}
import io.youi.{MapStore, Unique}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

class SessionStore(val timeout: FiniteDuration) extends MapStore {
  var lastUsed: Long = System.currentTimeMillis()

  def isExpired: Boolean = lastUsed + timeout.toMillis < System.currentTimeMillis()
}

object SessionStore {
  private val actorSystem = ActorSystem()
  private val scheduler = actorSystem.scheduler
  private var map = Map.empty[String, SessionStore]

  private val monitor = scheduler.schedule(1.minute, 1.minute) {
    invalidateExpired()
  }

  def getOrCreateSessionId(httpConnection: HttpConnection): String = synchronized {
    import httpConnection.server.config.session
    httpConnection.request.cookies.find(_.name == session.name()).map(_.value) match {
      case Some(id) => id     // Found cookie in request
      case None => httpConnection.response.cookies.find(_.name == session.name()).map(_.value) match {
        case Some(id) => id   // Found cookie in response
        case None => {        // No cookie found in request or response
        val id = Unique()
          httpConnection.update { response =>
            val cookie = ResponseCookie(
              name = session.name,
              value = id,
              maxAge = session.maxAge,
              domain = session.domain,
              secure = session.secure,
              httpOnly = session.httpOnly,
              sameSite = session.sameSite
            )
            response.withHeader(Headers.Response.`Set-Cookie`(cookie))
          }
          id
        }
      }
    }
  }

  def apply(connection: HttpConnection, timeout: FiniteDuration): SessionStore = synchronized {
    val sessionId = getOrCreateSessionId(connection)
    map.get(sessionId) match {
      case Some(store) => {
        store.lastUsed = System.currentTimeMillis()
        store
      }
      case None => {
        val store = new SessionStore(timeout)
        map += sessionId -> store
        store
      }
    }
  }

  private def invalidateExpired(): Unit = map.foreach {
    case (sessionId, store) if store.isExpired => synchronized {
      map -= sessionId
    }
    case _ => // Not expired
  }

  def dispose(): Unit = {
    monitor.cancel()
    actorSystem.terminate()
  }
}