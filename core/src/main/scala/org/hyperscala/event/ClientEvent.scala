package org.hyperscala.event

import org.hyperscala.IdentifiableTag

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait ClientEvent

trait TagClientEvent extends ClientEvent {
  def tag: IdentifiableTag
  def parent: Option[IdentifiableTag]
}