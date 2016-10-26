package io.youi.http

sealed abstract class Method private(val value: String) {
  Method.map += value -> this
}

object Method {
  private var map = Map.empty[String, Method]

  val Get = new Method("GET") {}
  val Put = new Method("PUT") {}
  val Trace = new Method("TRACE") {}
  val Connect = new Method("CONNECT") {}
  val Head = new Method("HEAD") {}
  val Delete = new Method("DELETE") {}
  val Patch = new Method("PATCH") {}
  val Post = new Method("POST") {}
  val Options = new Method("OPTIONS") {}

  def get(value: String): Option[Method] = map.get(value.toUpperCase)
  def apply(value: String): Method = get(value).getOrElse(throw new RuntimeException(s"$value is an invalid Method."))
}
