package io.youi.stream

sealed trait Tag

object Tag {
  case class Open(tagName: String, attributes: Map[String, String], start: Int, end: Int, close: Option[Close]) extends Tag

  case class Close(tagName: String, start: Int, end: Int) extends Tag
}