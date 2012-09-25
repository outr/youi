package org.hyperscala.web.live

import org.hyperscala.html.HTMLTag

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class LiveTag(tag: HTMLTag) {
  // TODO: add support for other events

  def onClick(f: => Unit) = {
    tag.event.click := LiveEvent()
    tag.listeners.synchronous {
      case evt: ClickEvent => f
    }
  }
}
