package io.youi.upload

import org.scalajs.dom.File
import reactify.Val

import scala.concurrent.Future

case class Uploading(file: File, progress: Val[Long], percentage: Val[Double], future: Future[String])