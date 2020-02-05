package io.youi.stream.delta

import io.youi.stream.{HTMLStream, Selector, Tag}

class Repeat[Data](val selector: Selector, data: List[Data], deltas: Data => List[Delta]) extends Delta {
  override def apply(streamer: HTMLStream, tag: Tag.Open): Unit = {
    data.zipWithIndex.foreach {
      case (d, index) => {
        streamer.grouped(index) {
          streamer.reposition(tag.start)
          deltas(d).foreach { delta =>
            val tags = delta.selector.lookup(streamer.streamable)
            tags.foreach { tag =>
              delta(streamer, tag)
            }
          }
          streamer.insert(tag.close.map(_.end).getOrElse(tag.end), "")
        }
      }
    }
  }
}
