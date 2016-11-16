package io.youi.net

case class Param(values: List[String] = Nil) {
  lazy val value = values.mkString(", ")

  def withValue(value: String, append: Boolean = true): Param = {
    val updated = if (append) values ::: List(value) else List(value)
    Param(updated)
  }
}
