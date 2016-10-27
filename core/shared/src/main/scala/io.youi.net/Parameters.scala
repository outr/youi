package io.youi.net

import io.youi.util.URLUtil

class Parameters(map: Map[String, Param]) {
  def isEmpty: Boolean = map.isEmpty
  def nonEmpty: Boolean = map.nonEmpty

  def value(key: String): Option[String] = map.get(key).map(_.value)
  def values(key: String): List[String] = map.get(key).map(_.values).getOrElse(Nil)

  def withParam(key: String, value: String, append: Boolean = true): Parameters = {
    val param = map.getOrElse(key, Param()).withValue(value, append)
    new Parameters(map + (key -> param))
  }

  lazy val encoded: String = {
    if (nonEmpty) {
      val b = new StringBuilder
      b.append('?')
      val params = map.flatMap {
        case (key, param) => {
          val keyEncoded = URLUtil.encode(key)
          param.values.map { value =>
            val valueEncoded = URLUtil.encode(value)
            s"$keyEncoded=$valueEncoded"
          }
        }
      }.mkString("&")
      b.append(params)
      b.toString()
    } else {
      ""
    }
  }

  lazy val decoded: String = {
    if (nonEmpty) {
      val b = new StringBuilder
      b.append('?')
      val params = map.flatMap {
        case (key, param) => {
          val keyEncoded = key
          param.values.map { value =>
            val valueEncoded = value
            s"$keyEncoded=$valueEncoded"
          }
        }
      }.mkString("&")
      b.append(params)
      b.toString()
    } else {
      ""
    }
  }

  override def toString: String = encoded
}

object Parameters {
  val empty = new Parameters(Map.empty)
}