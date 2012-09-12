package org.hyperscala.web.resource

import org.hyperscala.html.attributes.Method
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}
import java.net.URL
import annotation.tailrec
import java.io.{OutputStream, InputStream}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class URLWebResource(url: URL) extends WebResource {
  def apply(method: Method, request: HttpServletRequest, response: HttpServletResponse) = {
    val connection = url.openConnection()
    val extension = url.toString.substring(url.toString.lastIndexOf('.') + 1)
    val contentType = URLWebResource.contentType(extension, connection.getContentType)
    response.setContentType(contentType)
    response.setContentLength(connection.getContentLength)
    val input = connection.getInputStream
    try {
      val output = response.getOutputStream
      try {
        stream(input, output)
      } finally {
        output.flush()
        output.close()
      }
    } finally {
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

object URLWebResource {
  def contentType(extension: String, default: String) = extension.toLowerCase match {
    case "css" => "text/css"
    case "js" => "application/javascript"
    case _ => default match {
      case "content/unknown" => throw new RuntimeException("Unsupported content extension: %s for %s".format(extension, default))
      case _ => default
    }
  }
}