package io.youi.http

trait HeaderKey {
  def key: String
  protected def commaSeparated: Boolean

  def get(headers: Headers): Option[String] = all(headers).headOption
  def all(headers: Headers): List[String] = if (commaSeparated) {
    headers.get(this).flatMap(_.split(',').map(_.trim))
  } else {
    headers.get(this)
  }
}

object HeaderKey {
  def apply(key: String): StringHeaderKey = new StringHeaderKey(key)
}