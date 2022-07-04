package io.youi

import cats.effect.IO
import cats.effect.unsafe.implicits.global
import reactify.Var

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

/**
  * ThrottledVar provides throttling of setting the value dropping multiple values sent between throttle times always
  * ending up with the latest value.
  *
  * @param initialValue the initial value of this Var
  * @param throttle the minimum number of time between updates (additional updates between get lost only keeping the newest)
  * @param ec the execution context
  * @tparam T the type
  */
class ThrottledVar[T](initialValue: => T, throttle: FiniteDuration)(implicit ec: ExecutionContext) extends Var[T] {
  private val throttleMillis = throttle.toMillis
  private var lastModification = 0L
  private var pending: Option[() => Unit] = None

  super.set(initialValue)

  override def set(value: => T): Unit = {
    if (lastModification > 0L && pending.nonEmpty) {
      pending = Some(() => super.set(value))
    } else {
      val now = System.currentTimeMillis()
      val delay = (lastModification + throttleMillis) - now
      if (delay <= 0L) {
        super.set(value)
        lastModification = now
      } else {
        pending = Some(() => super.set(value))
        IO.sleep(delay.milli).map { _ =>
          pending.foreach(f => f())
          pending = None
          lastModification = System.currentTimeMillis()
        }.unsafeRunAndForget()
      }
    }
  }
}

object ThrottledVar {
  def apply[T](value: => T, throttle: FiniteDuration)(implicit ec: ExecutionContext): ThrottledVar[T] = {
    val v = new ThrottledVar[T](value, throttle)
    v := value
    v
  }
}