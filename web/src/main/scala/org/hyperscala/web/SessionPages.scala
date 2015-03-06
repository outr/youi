package org.hyperscala.web

import com.outr.net.http.session.Session
import org.powerscala.log.Logging

/**
 * @author Matt Hicks <matt@outr.com>
 */
class SessionPages[S <: Session] private[web](website: Website[S]) extends Iterable[Webpage[S]] with Logging {
  val sessionId = website.session.id
  private var pageIdMap = Map.empty[String, Webpage[S]]
  private var handlerIdPageIdMap = Map.empty[String, String]
  private var pageIdHandlerIdMap = Map.empty[String, String]

  override def iterator = pageIdMap.values.iterator

  def byHandlerId(handlerId: String) = handlerIdPageIdMap.get(handlerId).map(pageId => pageIdMap(pageId))

  private[web] def add(page: Webpage[S], scope: Scope, handlerId: Option[String]) = synchronized {
    if (scope != Scope.Application) {
      pageIdMap += page.pageId -> page
      if (scope == Scope.Session) {               // Store handler reference if it's Session scoped
        handlerId match {
          case Some(id) => {
            handlerIdPageIdMap += id -> page.pageId
            pageIdHandlerIdMap += page.pageId -> id
          }
          case None => // No handler supplied
        }
      }
    }
  }

  private[web] def remove(page: Webpage[S]) = synchronized {
    pageIdMap -= page.pageId
    pageIdHandlerIdMap.get(page.pageId) match {
      case Some(handlerId) => {
        handlerIdPageIdMap -= handlerId
        pageIdHandlerIdMap -= page.pageId
      }
      case None => // No handlerId stored for this page
    }
  }
}