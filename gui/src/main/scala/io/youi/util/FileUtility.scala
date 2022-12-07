package io.youi.util

import cats.effect.unsafe.implicits.global
import cats.effect.{Deferred, IO}
import org.scalajs.dom.{Event, File, FileReader, URL}

object FileUtility {
  def loadDataURL(file: File, useFileReader: Boolean = false): IO[String] = if (useFileReader) {
    val reader = new FileReader
    val d = Deferred[IO, String]
    reader.addEventListener("load", (_: Event) => {
      d.flatMap { d =>
        d.complete(reader.result.asInstanceOf[String])
      }.handleError { t =>
        scribe.error(t)
      }.unsafeRunAndForget()
    })
    reader.readAsDataURL(file)
    d.flatMap(_.get)
  } else {
    IO.pure(URL.createObjectURL(file))
  }

  def loadText(file: File): IO[String] = {
    val reader = new FileReader
    val d = Deferred[IO, String]
    reader.addEventListener("load", (_: Event) => {
      d.flatMap { d =>
        d.complete(reader.result.asInstanceOf[String])
      }.handleError { t =>
        scribe.error(t)
      }.unsafeRunAndForget()
    })
    reader.readAsText(file)
    d.flatMap(_.get)
  }
}