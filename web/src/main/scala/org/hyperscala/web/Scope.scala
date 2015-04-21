package org.hyperscala.web

import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class Scope extends EnumEntry

object Scope extends Enumerated[Scope] {
  /**
   * Defines a single instance that is shared across all sessions, users, and browsers.
   */
  case object Application extends Scope
  /**
   * Defines an instance per browser session.
   */
  case object Session extends Scope
  /**
   * A new instance is created for every request. No caching.
   */
  case object Request extends Scope
  /**
   * Works like Request in that a new instance is created for every request. However, the page is cached temporarily to
   * allow real-time communication with it.
   */
  case object Page extends Scope

  val values = findValues.toVector
}