package io.youi.stream.delta

import io.youi.stream.{HTMLStream, Selector, Tag}

class ReplaceAttribute(val selector: Selector, attributeName: String, val content: () => String) extends Delta {
  private val AttributeRegex = s"""$attributeName="(.*?)"""".r

  override def apply(streamer: HTMLStream, tag: Tag.Open): Unit = {
    val attribute = s"""$attributeName="${content()}""""
    streamer.process(tag.start, tag.end, (block: String) => {
      AttributeRegex.replaceAllIn(block, replacer => {
        attribute
      }) match {
        case s if s == block => {   // Not found, we need to add it
          val selfClosing = block.endsWith("/>")
          val left = block.substring(0, block.length - (if (selfClosing) 2 else 1))
          val right = if (selfClosing) "/>" else ">"
          s"$left $attribute$right"
        }
        case s => s                 // Replaced, just return it
      }
    })
  }
}
