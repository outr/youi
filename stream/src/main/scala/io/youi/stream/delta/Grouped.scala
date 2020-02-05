package io.youi.stream.delta

import io.youi.stream.{HTMLStream, Selector, Tag}

class Grouped(val selector: Selector, deltas: List[Delta]) extends Delta {
  override def apply(streamer: HTMLStream, tag: Tag.Open): Unit = {
    deltas.zipWithIndex.foreach {
      case (d, index) => {
        streamer.grouped(index) {
          d(streamer, tag)
        }
      }
    }
  }
}
