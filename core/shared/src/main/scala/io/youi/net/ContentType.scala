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
  lazy val `text/plain` = ContentType("text/plain")
  lazy val `text/html` = ContentType("text/html")
  lazy val `text/xml` = ContentType("text/xml")
  lazy val `text/csv` = ContentType("text/csv")
  lazy val `application/json` = ContentType("application/json")
  lazy val `application/pdf` = ContentType("application/pdf")
  lazy val `application/x-www-form-urlencoded` = ContentType("application/x-www-form-urlencoded")
  lazy val `multipart/form-data` = ContentType("multipart/form-data")

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