package io.youi.communication

import reactify._

import scala.concurrent.Future

case class Upload(fileName: String, bytes: Long, future: Future[String]) {
  val progress: Val[Long] = Var(0L)
  val percentage: Val[Int] = Val(math.floor((progress.toDouble / bytes.toDouble) * 100.0).toInt)
  val remaining: Val[Long] = Val(bytes - progress)
}

object Upload {
  def progress(upload: Upload, progress: Long): Unit = upload.progress.asInstanceOf[Var[Long]] @= progress
}