//package io.youi.image
//
//import io.youi.util.ImageUtility
//import io.youi.ImageMode
//import io.youi.drawable.Context
//import org.scalajs.dom.html
//
//import scala.concurrent.ExecutionContext.Implicits.global
//import scala.concurrent.Future
//
//case class TextureImageOld(img: html.Image,
//                        width: Double,
//                        height: Double,
//                        mode: ImageMode) extends Image {
//  override def isVector: Boolean = false
//
//  override def drawFast(context: Context, x: Double, y: Double, width: Double, height: Double): Unit = {
//    context.drawImage(img)(x, y, width, height)
//  }
//
//  override def drawAsync(context: Context, x: Double, y: Double, width: Double, height: Double): Future[Unit] = {
//    if (this.width == width && this.height == height) {     // Draw directly
//      drawFast(context, x, y, width, height)
//      Future.successful(())
//    } else {
//      ImageUtility.drawToCanvas(img, context.canvas)(x, y, width, height).map(_ => ())
//    }
//  }
//
//  override def toDataURL: Future[String] = ImageUtility.toDataURL(img)
//
//  override def dispose(): Unit = {}
//}