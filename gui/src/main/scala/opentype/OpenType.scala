package opentype

import rapid.Task
import rapid.task.Completable

object OpenType {
  def load(url: String): Task[Either[Throwable, Font]] = {
    val c: Completable[Either[Throwable, Font]] = Task.completable[Either[Throwable, Font]]
    TopLevel.load(url, (err: String, font: Font) => {
      Option(err) match {
        case Some(message) => c.success(Left(new OpenTypeException(message)))
        case None => c.success(Right(font))
      }
    })
    c
  }
}
