package io.youi.stream

sealed trait StreamAction {
  def position: Int
  def priority: Int
}

object StreamAction {
  case class Insert(position: Int, content: String, priority: Int) extends StreamAction

  case class Skip(position: Int, end: Int, priority: Int) extends StreamAction

  case class Reposition(position: Int, priority: Int) extends StreamAction

  case class Group(actions: List[StreamAction], priority: Int) extends StreamAction {
    lazy val position: Int = actions.foldLeft(Int.MaxValue)((min, action) => math.min(min, action.position))
  }

  case class Process(position: Int, end: Int, processor: String => String, priority: Int) extends StreamAction
}