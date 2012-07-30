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
   * Defines an instance per browser window. This works similarly to Session except allows multiple tabs or windows
   * within the same session to have multiple instances.
   */
  val Instance = new Scope
  /**
   * A new instance is created for every request. No caching.
   */
  val Request = new Scope
}