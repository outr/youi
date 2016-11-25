package io.youi.communicate

trait Invocation {
  val id: Int = Invocation.nextId(this)
}

object Invocation {
  private var map = Map.empty[Int, Invocation]
  private var increment = 0

  private def nextId(invocation: Invocation): Int = synchronized {
    increment += 1
    map += increment -> invocation
    increment
  }

  def byId[I <: Invocation](id: Int): I = map(id).asInstanceOf[I]
}