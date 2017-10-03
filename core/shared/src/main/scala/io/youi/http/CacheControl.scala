package io.youi.http

object CacheControl extends MultiTypedHeaderKey[CacheControlEntry] {
  private val MaxAgeRegex = """max-age=(\d+)""".r

  override def key: String = "Cache-Control"
  override protected def commaSeparated: Boolean = false

  override def value(headers: Headers): List[CacheControlEntry] = headers.get(this).mkString(", ").split(',').map(_.toLowerCase.trim).map {
    case "public" => Public
    case "private" => Private
    case "no-cache" => NoCache
    case "must-revalidate" => MustRevalidate
    case "no-store" => NoStore
    case MaxAgeRegex(seconds) => MaxAge(seconds.toLong)
  }.toList

  override def apply(values: CacheControlEntry*): Header = Header(this, values.map(_.value).mkString(", "))

  case object Public extends CacheControlEntry {
    override def value: String = "public"
  }
  case object Private extends CacheControlEntry {
    override def value: String = "private"
  }
  case object NoCache extends CacheControlEntry {
    override def value: String = "no-cache"
  }
  case object MustRevalidate extends CacheControlEntry {
    override def value: String = "must-revalidate"
  }
  case object NoStore extends CacheControlEntry {
    override def value: String = "no-store"
  }
  case class MaxAge(seconds: Long) extends CacheControlEntry {
    override def value: String = s"max-age=$seconds"
  }
}

sealed abstract class CacheControlEntry {
  def value: String
}