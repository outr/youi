package org.hyperscala.realtime.event.browser

/**
 * InitBrowserConnection is fired from the browser upon initial connection being established. The purpose of this
 * connection is to be handled by Realtime to properly route communications to and from the Webpage.
 *
 * @author Matt Hicks <matt@outr.com>
 */
case class InitBrowserConnection(siteId: String, pageId: String)