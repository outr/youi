package youi.example.ui

import rapid._
import rapid.Task
import youi.component.DrawableView
import youi.example.screen.UIExampleScreen
import youi.image.{AnimatedImage, Image}
import org.scalajs.dom
import spice.net._
import reactify.*

class AnimatedImageExample extends UIExampleScreen {
  override def title: String = "Animated Image Example"
  override def path: URLPath = path"/examples/animated-image.html"

  private var animating = false
  private var view: Option[DrawableView] = None

  override def createUI(): Task[Unit] = (0 until 25).toVector
    .map(i => Image(s"/images/scumbag_panda_$i.png"))
    .tasks
    .map { frames =>
      val animated = new AnimatedImage(frames, 0.1)
      val dv = new DrawableView {
        width @= animated.width
        height @= animated.height
        position.center := container.size.center
        position.middle := container.size.middle
        drawable @= animated
      }
      view = Some(dv)
      container.children += dv
    }

  override protected def activate(): Task[Unit] = super.activate().map { _ =>
    animating = true
    def loop(timestamp: Double): Unit = if (animating) {
      view.foreach(_.render())
      dom.window.requestAnimationFrame(ts => loop(ts))
    }
    dom.window.requestAnimationFrame(ts => loop(ts))
  }

  override protected def deactivate(): Task[Unit] = {
    animating = false
    super.deactivate()
  }
}
