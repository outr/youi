package io.youi.net

case class Parameters(entries: List[(String, Param)]) {
  lazy val map: Map[String, Param] = entries.toMap

  def isEmpty: Boolean = map.isEmpty
  def nonEmpty: Boolean = map.nonEmpty

  def value(key: String): Option[String] = map.get(key).map(_.value)
  def values(key: String): List[String] = map.get(key).map(_.values).getOrElse(Nil)

  def withParam(key: String, value: String, append: Boolean = true): Parameters = if (value.isEmpty) {
    if (append) {           // Nothing to do
      this
    } else {                // Remove the param if not appending and empty
      removeParam(key)
    }
  } else if (append) {      // Add to the param for this key
    appendParam(key, value)
  } else {                  // Replace the value
    replaceParam(key, List(value))
  }

  def appendParam(key: String, value: String): Parameters = {
    val values = this.values(key)
    replaceParam(key, value :: values)
  }

  def replaceParam(key: String, values: List[String]): Parameters = {
    val p = removeParam(key)
    p.copy(entries = (key -> Param(values)) :: p.entries)
  }

  def removeParam(key: String): Parameters = copy(entries = entries.filterNot(p => p._1 == key))

  lazy val encoded: String = {
    if (nonEmpty) {
      val b = new StringBuilder
      b.append('?')
      val params = map.flatMap {
        case (key, param) => {
          val keyEncoded = URL.encode(key)
          param.values.map { value =>
            val valueEncoded = URL.encode(value)
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
  val empty = Parameters(Nil)
}