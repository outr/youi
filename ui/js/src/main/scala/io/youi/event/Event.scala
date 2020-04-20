package io.youi.event

trait Event {
  def target: Component

  /**
    * True if this event will continue to propagate up the parent hierarchy.
    */
  def propagate: Boolean = Event.propagate(this)

  /**
    * Stops this event from propagating up the parent hierarchy.
    */
  def stopPropagation(): Unit = Event.stopPropagation(this)
}

object Event {
  private val threadLocal = new ThreadLocal[Map[Event, EventStatus]] {
    override def initialValue() = Map.empty
  }

  private def propagate(event: Event): Boolean = threadLocal.get().get(event).forall(_.propagate)
  private def stopPropagation(event: Event): Unit = {
    val map = threadLocal.get()
    val eventStatus = map.getOrElse(event, EventStatus())
    threadLocal.set(map + (event -> eventStatus))
  }
}

case class EventStatus(propagate: Boolean = true)