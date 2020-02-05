package io.youi.stream.delta

import io.youi.stream.{HTMLStream, Selector, Tag}

class Template(val selector: Selector, deltas: List[Delta]) extends Delta {
  override def apply(streamer: HTMLStream, tag: Tag.Open): Unit = {
    streamer.insert(tag.start, streamer.streamable.stream(deltas, Some(selector)))
  }
}
