package io.youi.stream

trait Monitor {
  def open(length: Option[Long]): Unit
  def written(length: Long, total: Long, percent: Option[Double]): Unit
  def failure(t: Throwable): Unit
  def closed(): Unit
  def completed(): Unit
}

object Monitor {
  object Ignore extends Monitor {
    override def open(length: Option[Long]): Unit = {}

    override def written(length: Long, total: Long, percent: Option[Double]): Unit = {}

    override def failure(t: Throwable): Unit = {}

    override def completed(): Unit = {}

    override def closed(): Unit = {}
  }
}