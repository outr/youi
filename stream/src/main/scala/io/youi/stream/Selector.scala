package io.youi.stream

sealed trait Selector {
  def lookup(streamable: StreamableHTML): Set[Tag.Open]
}

object Selector {
  case class ById(id: String) extends Selector {
    override def lookup(streamable: StreamableHTML): Set[Tag.Open] = streamable.byId.get(id).toSet
  }

  case class ByClass(className: String) extends Selector {
    override def lookup(streamable: StreamableHTML): Set[Tag.Open] = streamable.byClass.getOrElse(className, Set.empty)
  }

  case class ByTag(tagName: String) extends Selector {
    override def lookup(streamable: StreamableHTML): Set[Tag.Open] = streamable.byTag.getOrElse(tagName, Set.empty)
  }

  case class HasAtribute(attributeName: String) extends Selector {
    override def lookup(streamable: StreamableHTML): Set[Tag.Open] = streamable.byAttribute.getOrElse(attributeName, Set.empty)
  }

  case class ByMultiple(selectors: Selector*) extends Selector {
    override def lookup(streamable: StreamableHTML): Set[Tag.Open] = selectors.flatMap(_.lookup(streamable)).toSet
  }

  def parse(value: String): Selector = if (value.startsWith("#")) {
    ById(value.substring(1))
  } else if (value.startsWith(".")) {
    ByClass(value.substring(1))
  } else {
    ByTag(value)
  }
}