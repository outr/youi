package org.hyperscala.service

/**
 * ServiceException is a simple wrapper around RuntimeException, but when thrown by a Service method the message will be
 * sent back in the response JSON. If any other exception is fired the details of the message will be hidden from the
 * client.  Only use this if you want the client to be able to read an explicit error message.
 *
 * @param message represents the message that will be sent back to the client
 * @param code represents the error code that will be sent back to the client (defaults to -1)
 *
 * @author Matt Hicks <matt@outr.com>
 */
case class ServiceException(message: String, code: Int = -1) extends RuntimeException(message)

object ServiceException {
  val InternalError = 500
  val MissingValues = 501
}