package io.youi.server.session

import akka.actor.ActorSystem
import com.outr.props.Var
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

  val cookieName: Var[String] = Var("JSESSIONID")
  val cookieMaxAge: Var[Option[Long]] = Var(None)
  val cookieDomain: Var[Option[String]] = Var(None)
  val cookieSecure: Var[Boolean] = Var(false)

  private val monitor = scheduler.schedule(1.minute, 1.minute) {
    invalidateExpired()
  }

  def apply(connection: HttpConnection, timeout: FiniteDuration): SessionStore = synchronized {
    val sessionId = connection.request.cookies.find(_.name == cookieName()).map(_.value) match {
      case Some(id) => id     // Found cookie in request
      case None => connection.response.cookies.find(_.name == cookieName()).map(_.value) match {
        case Some(id) => id   // Found cookie in response
        case None => {        // No cookie found in request or response
          val id = Unique()
          connection.update { response =>
            val cookie = ResponseCookie(name = cookieName, value = id, maxAge = cookieMaxAge, domain = cookieDomain, secure = cookieSecure)
            response.withHeader(Headers.Response.`Set-Cookie`(cookie))
          }
          id
        }
      }
    }
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