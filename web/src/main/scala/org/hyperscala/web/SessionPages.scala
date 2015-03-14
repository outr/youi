package org.hyperscala.web

import com.outr.net.http.session.Session
import org.powerscala.log.Logging

/**
 * @author Matt Hicks <matt@outr.com>
 */
class SessionPages private[web](website: Website) extends Iterable[Webpage] with Logging {
  val sessionId = website.session.id
  private var pageIdMap = Map.empty[String, Webpage]
  private var handlerIdPageIdMap = Map.empty[String, String]
  private var pageIdHandlerIdMap = Map.empty[String, String]

  override def iterator = pageIdMap.values.iterator

  def byHandlerId(handlerId: String) = handlerIdPageIdMap.get(handlerId).map(pageId => pageIdMap(pageId))

  private[web] def add(page: Webpage, scope: Scope, handlerId: Option[String]) = synchronized {
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

  private[web] def remove(page: Webpage) = synchronized {
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