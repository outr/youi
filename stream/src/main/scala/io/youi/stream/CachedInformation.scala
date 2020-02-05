package io.youi.stream

case class CachedInformation(byId: Map[String, Tag.Open],
                             byClass: Map[String, Set[Tag.Open]],
                             byTag: Map[String, Set[Tag.Open]])
