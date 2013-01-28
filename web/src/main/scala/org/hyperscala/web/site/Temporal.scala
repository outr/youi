package org.hyperscala.web.site

import org.powerscala.Updatable
import org.powerscala.concurrent.Time._
import org.powerscala.log.Logging

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait Temporal extends Updatable with Logging with Disposable {
  val created = System.currentTimeMillis()

  private var lastCheckIn = created
  private var _disposed = false

  final def lifetime = fromMillis(System.currentTimeMillis() - created)
  final def stale = fromMillis(System.currentTimeMillis() - lastCheckIn)
  final def disposed = _disposed

  def timeout: Double

  protected[web] def checkIn() = lastCheckIn = System.currentTimeMillis()

  override def update(delta: Double) {
    val elapsed = stale
    if (elapsed > timeout) {    // Temporal has timed out
      info("%s (%s) has timed out; Elapsed: %s, Timeout: %s".format(getClass.getSimpleName, this, elapsed, timeout))
      _disposed = true
      dispose()
    } else if (disposed) {
      throw new RuntimeException("%s (%s) has already been disposed, it cannot be updated anymore!".format(getClass.getSimpleName, this))
    } else {
      super.update(delta)
    }
  }
}
