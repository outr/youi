package opentype

import cats.effect.unsafe.implicits.global
import cats.effect.{Deferred, IO}

object OpenType {
  def load(url: String): IO[Either[Throwable, Font]] = Deferred[IO, Either[Throwable, Font]].flatMap { deferred =>
    TopLevel.load(url, (err: String, font: Font) => {
      Option(err) match {
        case Some(message) => deferred.complete(Left(new OpenTypeException(message))).unsafeRunAndForget()
        case None => deferred.complete(Right(font)).unsafeRunAndForget()
      }
    })
    deferred.get
  }
}