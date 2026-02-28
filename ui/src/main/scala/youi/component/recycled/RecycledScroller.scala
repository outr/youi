package youi.component.recycled

import youi.component.{Component, Container}
import youi.component.support.OverflowSupport
import youi.component.types.Overflow
import youi.event.EventSupport
import reactify.Var

import scala.collection.immutable.Queue

class RecycledScroller[T, C <: Component](renderer: RecycledRenderer[T, C],
                                          cacheSize: Int = 1000,
                                          pageSize: Int = 100) extends Container with OverflowSupport with EventSupport {
  private var pool = Queue.empty[C]
  private var components = List.empty[C]
  private var cache = Map.empty[Int, T]
  private var verticalOffset = 0.0

  private var updatingDisplay = false

  object batch {
    val data: Var[BatchedData[T]] = Var(BatchedData.empty[T])
    val position: Var[Int] = Var(0)
  }

  overflow @= Overflow.Hidden

  batch.data.attachAndFire { data =>
    cache = Map.empty
    batch.position @= 0
    if (data.nonEmpty) {
      loadData(0, pageSize)
    } else {
      components.foreach(release)
      components = Nil
    }
  }

  batch.position.attach { p =>
    if (!updatingDisplay) {
      if (p < 0) {
        batch.position @= 0
      } else if (p >= batch.data().total) {
        batch.position.static(batch.data().total - 1)
      } else {
        val reuse = components
        components = Nil
        updateDisplay(p, size.height() + verticalOffset, reuse)
        val notLoaded = (p until math.min(batch.data().total, p + pageSize)).toList.filterNot(cache.contains)
        if (notLoaded.nonEmpty) {
          val start = notLoaded.min
          val end = notLoaded.max
          loadData(start, end)
        }
      }
    }
  }

  event.pointer.wheel.attach { evt =>
    verticalOffset -= evt.delta.y

    // Scroll all components down
    val remove = components.filter(c => c.position.top() - evt.delta.y > size.height())
    if (remove.nonEmpty) {
      verticalOffset = remove.map(_.position.top()).max - evt.delta.y

      updatingDisplay = true
      try {
        batch.position.static(batch.position() + remove.length)
      } finally {
        updatingDisplay = false
      }
    }
    remove.foreach(release)

    val reuse = components.diff(remove)
    components = Nil
    updateDisplay(batch.position(), size.height() + verticalOffset, reuse)
  }

  private def loadData(start: Int, end: Int): Unit = {
    batch.data().get(start, end).map { vector =>
      applyData(start, vector)
      val reuse = components
      components = Nil
      updateDisplay(batch.position(), size.height() + verticalOffset, reuse)
    }.startUnit()
  }

  private def applyData(offset: Int, data: Vector[T]): Unit = data.zipWithIndex.foreach {
    case (t, index) => cache += offset + index -> t
  }

  private def updateDisplay(position: Int, verticalOffset: Double, reuse: List[C]): Unit = {
    if (position < batch.data().total) {
      val c = reuse.headOption.getOrElse(retrieve)
      c.position.bottom @= verticalOffset
      val released = if (c.position.top() > size.height()) {
        release(c)
        true
      } else {
        cache.get(position) match {
          case Some(t) => renderer.setData(t, c)
          case None => renderer.loading(c)
        }
        renderer.show(c)
        components = c :: components
        false
      }
      val reuseTail = if (reuse.nonEmpty) reuse.tail else Nil
      if (c.position.top() > 0.0) {
        updateDisplay(position + 1, c.position.top(), reuseTail)
      } else {
        components = components.reverse
        reuseTail.foreach(release)
      }
    } else {
      components = components.reverse
      reuse.foreach(release)
    }
  }

  private def retrieve: C = {
    val c = pool.dequeueOption match {
      case Some((component, updated)) =>
        pool = updated
        component
      case None =>
        val component = renderer.createComponent
        renderer.hide(component)
        children += component
        component
    }
    renderer.loading(c)
    c
  }

  private def release(component: C): Unit = {
    renderer.hide(component)
    pool = pool.enqueue(component)
  }
}
