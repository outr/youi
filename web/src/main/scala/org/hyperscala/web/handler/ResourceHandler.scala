package org.hyperscala.web.handler

import java.net.URL
import org.hyperscala.html.attributes.Method
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}
import annotation.tailrec
import java.io.{OutputStream, InputStream}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait ResourceHandler extends ContentHandler {
  lazy val classLoader = getClass.getClassLoader

  // TODO: add caching support
  // TODO: add support for 304 Not Modified - override getLastModified in RenderServlet

  def matches(uri: String) = lookup(uri) != null

  def lookup(uri: String): URL

  def apply(method: Method, request: HttpServletRequest, response: HttpServletResponse) = {
    val url = lookup(request.getRequestURI)
    val connection = url.openConnection()
    val contentType = connection.getContentType match {
      case "content/unknown" => url.toString.substring(url.toString.lastIndexOf('.') + 1).toLowerCase match {
        case "css" => "text/css"
        case "js" => "application/javascript"
        case extension => throw new RuntimeException("Unsupported content extension: %s for %s".format(extension, url))
      }
      case s => s
    }
    response.setContentType(contentType)
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
}
