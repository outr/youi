package io.youi.upload

import cats.effect.IO
import org.scalajs.dom.File
import reactify.Val

case class Uploading(file: File, progress: Val[Long], percentage: Val[Double], io: IO[String])