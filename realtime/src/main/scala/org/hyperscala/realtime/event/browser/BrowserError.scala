package org.hyperscala.realtime.event.browser

/**
 * BrowserError is sent to the server when the browser fires an unhandled exception for proper server-side logging.
 *
 * @author Matt Hicks <matt@outr.com>
 */
case class BrowserError(timestamp: Long, message: String, obj: String, errorMessage: String, stackTrace: String) {
  override def toString = s"${f"$timestamp%tc"}: $message, Object: $obj\r\nError: $errorMessage @ $stackTrace"
}