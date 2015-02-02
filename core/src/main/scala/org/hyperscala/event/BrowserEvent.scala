package org.hyperscala.event

import org.hyperscala.IdentifiableTag

/**
 * BrowserEvent is the base trait for all events received from the browser.
 *
 * @author Matt Hicks <matt@outr.com>
 */
trait BrowserEvent {
  def tag: IdentifiableTag
}