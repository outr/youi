package org.hyperscala.web.resource

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait DisposableWebResource extends WebResource {
  private var _disposed = false

  def dispose() = {
    _disposed = true
  }

  override def disposed = _disposed
}
