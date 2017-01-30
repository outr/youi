package io.youi.stream

sealed trait Selector {
  def lookup(streamable: StreamableHTML): Set[OpenTag]
}

case class ById(id: String) extends Selector {
  override def lookup(streamable: StreamableHTML): Set[OpenTag] = streamable.byId.get(id).toSet
}

case class ByClass(className: String) extends Selector {
  override def lookup(streamable: StreamableHTML): Set[OpenTag] = streamable.byClass.getOrElse(className, Set.empty)
}

case class ByTag(tagName: String) extends Selector {
  override def lookup(streamable: StreamableHTML): Set[OpenTag] = streamable.byTag.getOrElse(tagName, Set.empty)
}