package org.hyperscala.module

import java.io.{ByteArrayOutputStream, InputStream, FileInputStream, File}
import java.net.{URL => JavaURL}

import com.outr.net.URL
import com.outr.net.http.mime.MimeType
import com.outr.net.http.session.Session
import org.apache.commons.codec.binary.Base64
import org.hyperscala.web.{Website, Webpage}
import org.powerscala.{IO, Version}
import org.hyperscala.html._
import org.powerscala.log.Logging

/**
 * EncodedImages module can be included in a webpage to automatically convert all image references to Base64 encoded
 * Strings before rendering the page.
 *
 * @author Matt Hicks <matt@outr.com>
 */
object EncodedImages extends Module with Logging {
  override val name = "encoded-images"
  override def version = Version(1)

  override def init[S <: Session](website: Website[S]) = {}

  override def load[S <: Session](webpage: Webpage[S]) = {
    encode(webpage)
  }

  def encode[S <: Session](webpage: Webpage[S]): Unit = {
    webpage.byTag[tag.Img].foreach {
      case image if image.src() != null && !image.src().trim.isEmpty => {
        val relative = !image.src().contains("://")

        val url = if (relative) {
          // TODO: support HttpRequest for resource instead of a connection
          val baseURL = if (image.src().startsWith("/")) {
            webpage.website.request.url.base
          } else {
            webpage.website.request.url.baseAndPath.substring(0, webpage.website.request.url.baseAndPath.lastIndexOf('/') + 1)
          }
          URL(s"$baseURL${image.src()}").javaURL
        } else {
          new JavaURL(image.src())
        }

        image.src := encode(url)
      }
    }
  }

  def encode(file: File): String = {
    val input = new FileInputStream(file)
    encode(input, MimeType.fromFile(file).getOrElse(throw new NullPointerException(s"Unable to determine mime-type for: ${file.getName}.")))
  }

  def encode(url: JavaURL): String = {
    val c = url.openConnection()
    encode(c.getInputStream, MimeType.fromFilename(url.getFile, null).getOrElse(throw new NullPointerException(s"Unable to determine mime-type for: ${url.getFile}.")))
  }

  def encode(input: InputStream, mimeType: String): String = try {
    val baos = new ByteArrayOutputStream()
    try {
      IO.stream(input, baos, closeOnComplete = false)
      encode(baos.toByteArray, mimeType)
    } finally {
      baos.close()
    }
  } finally {
    input.close()
  }

  def encode(bytes: Array[Byte], mimeType: String): String = {
    s"data:$mimeType;base64,${Base64.encodeBase64String(bytes)}"
  }
}