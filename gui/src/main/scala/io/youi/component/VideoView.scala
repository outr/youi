package io.youi.component

import io.youi.component.types.Display
import io.youi.dom
import io.youi.video.Video
import org.scalajs.dom.html
import reactify.Var

class VideoView(val video: Video) extends Component(video.element) {
  val autoPauseOnHide: Var[Boolean] = Var(true)

  display.attach {
    case Display.None if autoPauseOnHide() && !video.isPaused => video.pause()
    case _ =>
  }
}

object VideoView {
  def apply(video: Video): VideoView = new VideoView(video)
}
