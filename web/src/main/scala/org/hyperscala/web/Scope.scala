package org.hyperscala.web

import org.powerscala.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
sealed class Scope extends EnumEntry[Scope]

object Scope extends Enumerated[Scope] {
  /**
   * Defines a single instance that is shared across all sessions, users, and browsers.
   */
  val Application = new Scope
  /**
   * Defines an instance per browser session.
   */
  val Session = new Scope
  /**
   * A new instance is created for every request. No caching.
   */
  val Request = new Scope
  /**
   * Works like Request in that a new instance is created for every request. However, the page is cached temporarily to
   * allow real-time communication with it.
   */
  val Page = new Scope
}