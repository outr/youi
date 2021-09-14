package io.youi

import java.io.{ByteArrayInputStream, File, FileInputStream, FileOutputStream, IOException, InputStream, OutputStream}
import java.net.{HttpURLConnection, URL}
import java.nio.file.Path
import scala.annotation.tailrec
import scala.language.implicitConversions

package object stream {
  implicit class InputStreamReader(input: InputStream) extends Reader {
    override def length: Option[Long] = None

    override def read(buffer: Array[Byte]): Int = input.read(buffer)

    override def close(): Unit = input.close()
  }

  implicit def javaReader2Reader(reader: java.io.Reader): Reader = new Reader {
    override def length: Option[Long] = None

    override def read(buffer: Array[Byte]): Int = {
      val b = new Array[Char](buffer.length)
      val len = reader.read(b)
      b.zipWithIndex.foreach {
        case (c, index) => buffer(index) = c.toByte
      }
      len
    }

    override def close(): Unit = reader.close()
  }

  implicit def array2Reader(array: Array[Byte]): InputStreamReader = new InputStreamReader(new ByteArrayInputStream(array))

  implicit def file2Reader(file: File): InputStreamReader = new InputStreamReader(new FileInputStream(file)) {
    override def length: Option[Long] = Some(file.length())
  }

  implicit def path2Reader(path: Path): InputStreamReader = file2Reader(path.toFile)

  implicit def url2Reader(url: URL): InputStreamReader = urlInputStream(url, Set.empty)

  @tailrec
  private def urlInputStream(url: URL, redirects: Set[String]): InputStreamReader = {
    val connection: HttpURLConnection = url.openConnection().asInstanceOf[HttpURLConnection]
    connection.getResponseCode match {
      case code if !Set(HttpURLConnection.HTTP_MOVED_TEMP, HttpURLConnection.HTTP_MOVED_PERM, HttpURLConnection.HTTP_SEE_OTHER).contains(code) =>
        val len = connection.getContentLengthLong
        new InputStreamReader(connection.getInputStream) {
          override def length: Option[Long] = len match {
            case _ if len < 0 => None
            case _ => Some(len)
          }
        }
      case _ =>
        val redirectURL = connection.getHeaderField("Location")
        if (redirects.contains(redirectURL)) {
          throw new IOException(s"Redirect loop detected: ${redirects.mkString(", ")}")
        }
        scribe.warn(s"Download URL redirecting from $url to $redirectURL")
        urlInputStream(new URL(redirectURL), redirects + url.toString)
    }
  }

  implicit def youiURL2Reader(url: net.URL): InputStreamReader = url2Reader(new URL(url.toString))

  implicit def string2Reader(s: String): InputStreamReader = new InputStreamReader(new ByteArrayInputStream(s.getBytes)) {
    override def length: Option[Long] = Some(s.length)
  }

  implicit class OutputStreamWriter(output: OutputStream) extends Writer {
    override def write(buffer: Array[Byte], offset: Int, length: Int): Unit = output.write(buffer, offset, length)

    override def flush(): Unit = output.flush()

    override def close(): Unit = output.close()

    override def complete(): Unit = {}
  }

  implicit def file2Writer(file: File): OutputStreamWriter = {
    val temp = new File(file.getParentFile, s".${file.getName}.temp")
    new OutputStreamWriter(new FileOutputStream(temp)) {
      override def complete(): Unit = {
        super.complete()

        file.delete()         // Make sure the original file doesn't exist
        temp.renameTo(file)   // Two-stage write to avoid partials
      }
    }
  }

  implicit def path2Writer(path: Path): OutputStreamWriter = file2Writer(path.toFile)

  implicit class StringBuilderWriter(sb: StringBuilder) extends Writer {
    override def write(buffer: Array[Byte], offset: Int, length: Int): Unit = {
      sb.append(new String(buffer, offset, length))
    }

    override def flush(): Unit = {}

    override def close(): Unit = {}

    override def complete(): Unit = {}

    override def toString: String = sb.toString()
  }
}