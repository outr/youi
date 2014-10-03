package org.hyperscala.examples.ui

import org.hyperscala.css.SelectorStyleSheet
import org.hyperscala.css.attributes.Display
import org.hyperscala.html._
import org.hyperscala.realtime._
import org.hyperscala.examples.Example
import org.hyperscala.selector.Selector
import org.hyperscala.ui.VideoJS

/**
 * @author Matt Hicks <matt@outr.com>
 */
class VideoJSExample extends Example {
  new SelectorStyleSheet(".vjs-default-skin .vjs-seek-handle, .vjs-default-skin .vjs-volume-handle")(this) {
    display := Display.None
  }

  val video = new VideoJS {
    id := "video_example"
    controls := true
    preLoad := "auto"
    playCentered := true
    width := 640
    height := 266
    poster := "http://video-js.zencoder.com/oceans-clip.png"

    source("http://video-js.zencoder.com/oceans-clip.webm", "video/webm")
    source("http://video-js.zencoder.com/oceans-clip.mp4", "video/mp4")
    source("http://video-js.zencoder.com/oceans-clip.ogv", "video/ogg")
  }

  contents += video

  contents += new tag.Button {
    contents += "Change Video"
    clickEvent.onRealtime {
      case evt => {
        video.src := "http://mirror.cessen.com/blender.org/peach/trailer/trailer_480p.mov"
        video.poster := "http://peach.blender.org/wp-content/uploads/watchtrailer.gif"

        removeFromParent()
      }
    }
  }
}
