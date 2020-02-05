package io.youi.stream.delta

import io.youi.stream.{HTMLStream, Selector, Tag}

class Replace(val selector: Selector, val content: () => String) extends Delta {
  override def apply(streamer: HTMLStream, tag: Tag.Open): Unit = {
    streamer.replace(tag.start, tag.close.map(_.end).getOrElse(tag.end), content())
  }
}
