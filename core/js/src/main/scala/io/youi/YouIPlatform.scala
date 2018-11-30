package io.youi

import scala.concurrent.{Future, Promise}
import org.scalajs.dom._

object YouIPlatform {
  def delay(millis: Long): Future[Unit] = {
    val promise = Promise[Unit]
    window.setTimeout(() => {
      promise.success(())
    }, millis)
    promise.future
  }
}