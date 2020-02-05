package io.youi.stream.delta

import io.youi.stream.{HTMLStream, Selector, Tag}

class Processor(val selector: Selector,
                replace: Boolean,
                onlyOpenTag: Boolean,
                processor: (Tag.Open, String) => String,
                closeTagProcessor: Option[(Tag.Open, Tag.Close, String) => String]) extends Delta {
  override def apply(streamer: HTMLStream, tag: Tag.Open): Unit = {
    val end = if (onlyOpenTag) {
      tag.end
    } else {
      tag.close.map(_.end).getOrElse(tag.end)
    }
    streamer.process(tag.start, end, processor.curried(tag), replace = replace)
    closeTagProcessor.foreach { processor =>
      tag.close.foreach { closeTag =>
        streamer.process(closeTag.start, closeTag.end, processor.curried(tag)(closeTag))
      }
    }
  }
}
