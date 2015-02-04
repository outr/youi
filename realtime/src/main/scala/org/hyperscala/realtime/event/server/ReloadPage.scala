package org.hyperscala.realtime.event.server

/**
 * Event sent to the browser to tell it to reload the page.
 *
 * @author Matt Hicks <matt@outr.com>
 */
case class ReloadPage(forcedReload: Boolean)