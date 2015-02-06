package org.hyperscala.realtime.event.server

/**
 * @author Matt Hicks <matt@outr.com>
 */
case class SetSelectorStyle(selector: String, key: String, value: String, important: Boolean, styleSheet: Boolean)