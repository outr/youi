package io.youi.stream

import java.io.File
import java.nio.channels.FileChannel
import java.nio.file.StandardOpenOption

class StreamableHTML(file: File, cacheBuilder: CacheBuilder) {
  private var cache = cacheBuilder.buildCache()

  def byId: Map[String, Tag.Open] = cache.byId
  def byClass: Map[String, Set[Tag.Open]] = cache.byClass
  def byTag: Map[String, Set[Tag.Open]] = cache.byTag

  def stream(deltas: List[Delta],
             selector: Option[Selector] = None,
             includeTag: Boolean = true,
             includeAllMatches: Boolean = false): String = {
    synchronized {
      if (cacheBuilder.isStale) {
        cache = cacheBuilder.buildCache()
      }
    }
    val channel = FileChannel.open(file.toPath, StandardOpenOption.READ)
    try {
      val streamer = new HTMLStream(this)

      selector match {
        case Some(sel) => {
          val tags = sel.lookup(this) match {
            case i if includeAllMatches => i.toList
            case i => i.headOption.toList
          }
          tags.map { tag =>
            val (start, end) = if (includeTag) {
              tag.start -> tag.close.map(_.end).getOrElse(tag.end)
            } else {
              tag.end -> tag.close.map(_.start).getOrElse(tag.end)
            }
            deltas.foreach { delta =>
              val tags = delta.selector.lookup(this)
              tags.foreach { tag =>
                if (tag.start >= start && tag.close.map(_.end).getOrElse(tag.end) <= end) {
                  try {
                    delta(streamer, tag)
                  } catch {
                    case t: Throwable => throw new RuntimeException(s"Error processing Delta: $delta for tag: $tag.", t)
                  }
                }
              }
            }
            streamer.stream(channel, end, Some(start))
          }.mkString("\n")
        }
        case None => {
          val end = file.length().toInt
          deltas.foreach { delta =>
            val tags = delta.selector.lookup(this)
            tags.foreach { tag =>
              if (tag.start >= 0 && tag.close.map(_.end).getOrElse(tag.end) <= end) {
                try {
                  delta(streamer, tag)
                } catch {
                  case t: Throwable => throw new RuntimeException(s"Error processing Delta: $delta for tag: $tag.", t)
                }
              }
            }
          }
          streamer.stream(channel, end, None)
        }
      }
    } finally {
      channel.close()
    }
  }
}