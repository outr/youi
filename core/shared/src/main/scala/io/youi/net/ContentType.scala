package io.youi.net

case class ContentType(mimeType: String, charSet: Option[String] = None, boundary: Option[String] = None) {
  lazy val outputString = {
    val b = new StringBuilder(mimeType)
    charSet.foreach { s =>
      b.append(s"; charset=$s")
    }
    boundary.foreach { s =>
      b.append(s"; boundary=$s")
    }
    b.toString()
  }

  def is(contentType: ContentType) = contentType.mimeType == mimeType

  override def toString = outputString
}

object ContentType {
  lazy val Text = new ContentType("text/plain")
  lazy val HTML = new ContentType("text/html")
  lazy val XML = new ContentType("text/xml")
  lazy val JSON = new ContentType("application/json")
  lazy val CSV = new ContentType("text/csv")
  lazy val PDF = new ContentType("application/pdf")
  lazy val FormURLEncoded = new ContentType("application/x-www-form-urlencoded")
  lazy val MultiPartFormData = new ContentType("multipart/form-data")

  def parse(contentTypeString: String): ContentType = {
    val parts = contentTypeString.split(';')
    var contentType = ContentType(parts(0).trim)
    parts.tail.foreach { s =>
      val block = s.trim
      val divider = block.indexOf('=')
      if (divider == -1) {
        throw new RuntimeException(s"Unable to parse content type: [$contentTypeString]")
      }
      val name = block.substring(0, divider)
      val value = block.substring(divider + 1)
      name match {
        case "boundary" => contentType = contentType.copy(boundary = Some(value))
        case "charset" => contentType = contentType.copy(charSet = Some(value))
        case _ => throw new RuntimeException(s"Unable to parse content type: [$contentTypeString]")
      }
    }
    contentType
  }
}