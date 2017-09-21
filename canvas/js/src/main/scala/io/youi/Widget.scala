package io.youi

import reactify._

import scala.concurrent.Future

trait Widget {
  protected[youi] lazy val parentWidget: Var[Option[WidgetContainer]] = Var(None)

  def position: WidgetPosition
  def size: WidgetSize

  protected def invalidate(): Future[Unit] = Future.successful(())

  protected def init(): Unit = {}
}

trait WidgetPosition {
  def x: Var[Double]
  def y: Var[Double]

  def left: Var[Double]
  def center: Dep[Double, Double]
  def right: Dep[Double, Double]

  def top: Var[Double]
  def middle: Dep[Double, Double]
  def bottom: Dep[Double, Double]
}

trait WidgetSize {
  def width: Var[Double]
  def height: Var[Double]
}