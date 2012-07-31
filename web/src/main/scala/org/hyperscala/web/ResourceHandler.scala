package org.hyperscala.web

import javax.servlet.http.{HttpServletResponse, HttpServletRequest}
import org.powerscala.Priority
import java.io.{OutputStream, InputStream}
import annotation.tailrec

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object ResourceHandler extends ContentHandler {
  lazy val classLoader = getClass.getClassLoader

  // TODO: add caching support

  def matches(uri: String) = toURL(uri) match {
    case null => false
    case _ => true
  }

  def apply(method: Method, request: HttpServletRequest, response: HttpServletResponse) = {
    val url = toURL(request.getRequestURI)
    val connection = url.openConnection()
    response.setContentType(connection.getContentType)
    response.setContentLength(connection.getContentLength)
    val input = connection.getInputStream
    val output = response.getOutputStream
    try {
      stream(input, output)
    } finally {
      output.flush()
      output.close()
      input.close()
    }
  }

  @tailrec
  private def stream(input: InputStream, output: OutputStream, b: Array[Byte] = new Array[Byte](512)): Unit = {
    val len = input.read(b)
    if (len != -1) {
      output.write(b, 0, len)
      stream(input, output, b)
    }
  }

  def toURL(uri: String) = classLoader.getResource(uri.substring(1))

  override def priority = Priority.Low
}
