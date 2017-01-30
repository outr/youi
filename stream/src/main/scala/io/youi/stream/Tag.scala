package io.youi.stream

sealed trait Tag

case class OpenTag(tagName: String, attributes: Map[String, String], start: Int, end: Int, close: Option[CloseTag]) extends Tag

case class CloseTag(tagName: String, start: Int, end: Int) extends Tag