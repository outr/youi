package org.hyperscala.screen

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait Screen {
  def activate(): Unit

  def deactivate(): Unit

  def dispose(): Unit
}