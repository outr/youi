package io.youi.image

import io.youi.drawable.Context

import scala.concurrent.Future

object EmptyImage extends Image {
  override val width: Double = 0.0
  override val height: Double = 0.0

  override def drawFast(context: Context, width: Double, height: Double): Boolean = false

  override def draw(context: Context, width: Double, height: Double): Future[Unit] = Future.successful(())

  override def dispose(): Unit = {}

  override def isVector: Boolean = true

//  override def toDataURL: Future[String] = throw new RuntimeException("Empty image cannot be represented as a data url.")
}