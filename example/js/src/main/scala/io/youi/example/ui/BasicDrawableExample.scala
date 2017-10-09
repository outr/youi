package io.youi.example.ui

import io.youi._
import io.youi.app.screen.UIScreen
import io.youi.drawable._
import io.youi.paint.Stroke

import scala.concurrent.Future

object BasicDrawableExample extends UIExampleScreen with UIScreen {
  override def name: String = "Basic Drawable"

  var message: String = "Testing"

  private val rectangle = new Drawable {
    override def draw(context: Context, x: Double, y: Double): Unit = {
      context.rect(100.0, 100.0, 250.0, 250.0)
      context.fill(Color.Red, apply = true)
    }
  }

  private val basicText = new Drawable {
    override def draw(context: Context, x: Double, y: Double): Unit = {
      context.fill(Color.Blue, apply = false)
      context.setFont("Arial", 96.0, "normal", "normal", "normal")
      context.fillText(message, 200.0, 200.0)
    }
  }

//  override protected val drawable: Future[Group] = Future.successful(Group(rectangle, text))
//  override protected val drawable = OpenTypeFont.fromURL(GoogleFont.`Open Sans`.`regular`).map { font =>
//    val text = font("Testing", 96.0)
//    val text2 = new Drawable {
//      override def draw(context: Context, x: Double, y: Double): Unit = {
//        text.draw(context, x + 200.0, y + 300.0)
//        context.fill(Color.Green, apply = true)
//        context.stroke(Stroke(Color.Black, 2.0), apply = true)
//      }
//    }
//    Group(rectangle, basicText, text2)
//  }

//  override protected val drawable = Image("/images/cuteness.jpg").map { image =>
//    val d = new Drawable {
//      override def draw(context: Context, x: Double, y: Double): Unit = {
//        image.draw(context, x + 200.0, y + 300.0)
//      }
//    }
//    Group(rectangle, basicText, d)
//  }

//  override protected val drawable = Video(History.url().withPath("/sample.mp4")).map { video =>
//    val d = new Drawable {
//      modified := video.modified
//      video.play()
//      video.position.attach { d =>
//        message = s"Volume: ${video.volume()}, Duration: ${video.duration}, Width: ${video.width}, Height: ${video.height}, Position: $d"
//      }
//
//      override def draw(context: Context, x: Double, y: Double): Unit = {
//        video.draw(context, x + 200.0, y + 300.0)
//      }
//    }
//    Group(rectangle, basicText, d)
//  }

  override protected val drawable = Future.successful {
    val d = new Cacheable {
      updateCache(300.0, 300.0) { ctx =>
        ctx.rect(1.0, 1.0, 298.0, 298.0)
        ctx.fill(Color.LightBlue, apply = true)
        ctx.stroke(Stroke(Color.Black, 2.0), apply = true)
        Future.successful(())
      }
    }
    Group(rectangle, basicText, Transformation(200.0, 300.0)(d))
  }

  override def path: String = "/examples/basic-drawable.html"
}