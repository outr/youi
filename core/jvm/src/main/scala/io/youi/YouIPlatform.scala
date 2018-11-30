package io.youi

import java.util.{Timer, TimerTask}

import scala.concurrent.{Future, Promise}

object YouIPlatform {
  private lazy val timer = new Timer("io.youi.Time", true)

  def delay(millis: Long): Future[Unit] = {
    val promise = Promise[Unit]
    timer.schedule(new TimerTask {
      override def run(): Unit = promise.success(())
    }, millis)
    promise.future
  }
}
