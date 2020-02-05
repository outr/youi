package io.youi.stream

sealed trait Delta {
  def selector: Selector
  def apply(streamer: HTMLStream, tag: Tag.Open): Unit
}

class Replace(val selector: Selector, val content: () => String) extends Delta {
  override def apply(streamer: HTMLStream, tag: Tag.Open): Unit = {
    streamer.replace(tag.start, tag.close.map(_.end).getOrElse(tag.end), content())
  }
}
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
class InsertBefore(val selector: Selector, val content: () => String) extends Delta {
  override def apply(streamer: HTMLStream, tag: Tag.Open): Unit = {
    streamer.insert(tag.start, content())
  }
}
class InsertFirstChild(val selector: Selector, val content: () => String) extends Delta {
  override def apply(streamer: HTMLStream, tag: Tag.Open): Unit = {
    streamer.insert(tag.end, content())
  }
}
class ReplaceContent(val selector: Selector, val content: () => String) extends Delta {
  override def apply(streamer: HTMLStream, tag: Tag.Open): Unit = {
    streamer.replace(tag.end, tag.close.get.start, content())
  }
}
class InsertLastChild(val selector: Selector, val content: () => String) extends Delta {
  override def apply(streamer: HTMLStream, tag: Tag.Open): Unit = {
    streamer.insert(tag.close.get.start, content())
  }
}
class InsertAfter(val selector: Selector, val content: () => String) extends Delta {
  override def apply(streamer: HTMLStream, tag: Tag.Open): Unit = {
    streamer.insert(tag.close.map(_.end).getOrElse(tag.end), content())
  }
}
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
class Template(val selector: Selector, deltas: List[Delta]) extends Delta {
  override def apply(streamer: HTMLStream, tag: Tag.Open): Unit = {
    streamer.insert(tag.start, streamer.streamable.stream(deltas, Some(selector)))
  }
}
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

object Delta {
  def Replace(selector: Selector, content: => String): Replace = new Replace(selector, () => content)
  def Process(selector: Selector,
              replace: Boolean,
              onlyOpenTag: Boolean,
              processor: (Tag.Open, String) => String,
              closeTagProcessor: Option[(Tag.Open, Tag.Close, String) => String] = None): Processor = new Processor(selector, replace, onlyOpenTag, processor, closeTagProcessor)
  def InsertBefore(selector: Selector, content: => String): InsertBefore = new InsertBefore(selector, () => content)
  def InsertFirstChild(selector: Selector, content: => String): InsertFirstChild = new InsertFirstChild(selector, () => content)
  def ReplaceContent(selector: Selector, content: => String): ReplaceContent = new ReplaceContent(selector, () => content)
  def ReplaceAttribute(selector: Selector, attributeName: String, content: => String): ReplaceAttribute = new ReplaceAttribute(selector, attributeName, () => content)
  def InsertLastChild(selector: Selector, content: => String): InsertLastChild = new InsertLastChild(selector, () => content)
  def InsertAfter(selector: Selector, content: => String): InsertAfter = new InsertAfter(selector, () => content)
  def Repeat[Data](selector: Selector, data: List[Data], changes: Data => List[Delta]): Repeat[Data] = new Repeat(selector, data, changes)
  def Template(selector: Selector, deltas: List[Delta]): Template = new Template(selector, deltas)
  def Grouped(selector: Selector, deltas: Delta*): Grouped = new Grouped(selector, deltas.toList)
}