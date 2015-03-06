package org.hyperscala.web

import com.outr.net.http.session.Session
import org.powerscala.log.Logging
import org.powerscala.reflect._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Pages[S <: Session] private[web](website: Website[S]) extends Iterable[Webpage[S]] with Logging {
  private var pageIdMap = Map.empty[String, Webpage[S]]
  private var pageIdScopeMap = Map.empty[String, Scope]
  private var pageIdSessionIdMap = Map.empty[String, String]
  private var sessionIdMap = Map.empty[String, SessionPages[S]]
  private var handlerIdPageIdMap = Map.empty[String, String]
  private var pageIdHandlerIdMap = Map.empty[String, String]

  /**
   * Iterator over all the currently loaded pages for this website.
   */
  def ids = pageIdMap.keysIterator

  /**
   * Gets the page by pageId for a currently loaded page for this website.
   *
   * @param pageId the pages' id
   * @tparam W the type of Webpage
   * @return Option[W]
   */
  def byPageId[W <: Webpage[S]](pageId: String) = pageIdMap.get(pageId).asInstanceOf[Option[W]]

  /**
   * Gets the page by pageId or throws a RuntimeException if the page is unable to be found.
   *
   * @param pageId the pages' id
   * @tparam W the type of Webpage
   * @return W
   */
  def getByPageId[W <: Webpage[S]](pageId: String) = byPageId[W](pageId).getOrElse(throw new RuntimeException(s"Unable to find page by id: $pageId."))

  /**
   * Gets all pages by the sessionId.
   *
   * @param sessionId the session's id to find pages for
   * @return all the pages for the session
   */
  def bySessionId(sessionId: String) = sessionIdMap.get(sessionId).map(sp => sp.iterator).getOrElse(Nil)

  /**
   * Gets the page associated with the handler id in the current session.
   *
   * @param handlerId the handler's id
   * @tparam W the type of Webpage
   * @return Option[W]
   */
  def bySessionHandlerId[W <: Webpage[S]](handlerId: String) = sessionPages.byHandlerId(handlerId).asInstanceOf[Option[W]]

  /**
   * Gets the page associated with the handler id in the application scope.
   *
   * @param handlerId the handler's id
   * @tparam W the type of Webpage
   * @return Option[W]
   */
  def byApplicationHandlerId[W <: Webpage[S]](handlerId: String) = handlerIdPageIdMap.get(handlerId).map(pageId => pageIdMap(pageId)).asInstanceOf[Option[W]]

  /**
   * Gets all the currently loaded pages by the supplied Scope.
   *
   * @param scope the Scope to filter by
   * @return Iterable[Webpage[S]]
   */
  def byScope(scope: Scope) = withScope.collect {
    case (page, pageScope) if pageScope == scope => page
  }

  /**
   * Iterator over all pages with their Scope.
   */
  def withScope = pageIdMap.values.map(page => (page, pageIdScopeMap(page.pageId)))

  /**
   * Collects all the loaded pages that match the type signature.
   *
   * @param manifest type signature manifest
   * @tparam W the type of Webpage
   * @return Iterator[W]
   */
  def byType[W <: Webpage[S]](implicit manifest: Manifest[W]) = iterator.collect {
    case w if w.getClass.hasType(manifest.runtimeClass) => w.asInstanceOf[W]
  }

  def iterator = pageIdMap.values.iterator

  private def sessionPages = synchronized {
    sessionIdMap.get(website.session.id) match {
      case Some(sp) => sp
      case None => {
        val sp = new SessionPages[S](website)
        sessionIdMap += sp.sessionId -> sp
        sp
      }
    }
  }

  private[web] def add(page: Webpage[S], scope: Scope, handlerId: Option[String]) = synchronized {
    pageIdMap += page.pageId -> page
    pageIdScopeMap += page.pageId -> scope
    if (scope != Scope.Application) {
      pageIdSessionIdMap += page.pageId -> website.session.id
    }
    sessionPages.add(page, scope, handlerId)
    if (scope == Scope.Application) {                     // Store handler reference if it's Application scoped
      handlerId match {
        case Some(id) => {
          handlerIdPageIdMap += id -> page.pageId
          pageIdHandlerIdMap += page.pageId -> id
        }
        case None => // No handler supplied
      }
    }
  }

  private[web] def remove(page: Webpage[S]) = synchronized {
    pageIdMap -= page.pageId
    pageIdScopeMap -= page.pageId
    val sessionPages = sessionIdMap(pageIdSessionIdMap(page.pageId))
    sessionPages.remove(page)
    pageIdSessionIdMap -= page.pageId
    pageIdHandlerIdMap.get(page.pageId) match {
      case Some(handlerId) => {
        handlerIdPageIdMap -= handlerId
        pageIdHandlerIdMap -= page.pageId
      }
      case None => // No handlerId stored for this page
    }
    if (sessionPages.isEmpty) {
      sessionIdMap -= sessionPages.sessionId                  // Remove SessionPages instance when the session is empty
    }
  }
}