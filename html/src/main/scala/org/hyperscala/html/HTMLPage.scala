package org.hyperscala.html

import org.hyperscala.Page
import org.powerscala.{Disposable, Updatable}

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait HTMLPage extends Page {
  def html: tag.HTML
  def head: tag.Head
  def body: tag.Body

  override def update(delta: Double) = {
    super.update(delta)

    html.byType[Updatable].foreach {
      case u => u.update(delta)
    }
  }

  override def dispose() = {
    html.byType[Disposable].foreach {
      case d => d.dispose()
    }
  }
}